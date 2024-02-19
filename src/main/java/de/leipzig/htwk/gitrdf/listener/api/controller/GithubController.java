package de.leipzig.htwk.gitrdf.listener.api.controller;

import de.leipzig.htwk.gitrdf.listener.api.exception.BadRequestException;
import de.leipzig.htwk.gitrdf.listener.api.model.request.AddGithubRepoFilterRequestBody;
import de.leipzig.htwk.gitrdf.listener.api.model.request.AddGithupRepoRequestBody;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GithubRepositoryOrderResponse;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GithubRepositorySavedResponse;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryFilter;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.factory.GithubRepositoryFilterFactory;
import de.leipzig.htwk.gitrdf.listener.service.GithubService;
import de.leipzig.htwk.gitrdf.listener.utils.LongUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GithubController {

    private final GithubService githubService;

    private final GithubRepositoryFilterFactory githubRepositoryFilterFactory;

    @GetMapping("/api/v1/github")
    public List<GithubRepositoryOrderResponse> getAllGithubRepositoryOrderEntries() {
        List<GithubRepositoryOrderEntity> results = githubService.findAll();
        return  GithubRepositoryOrderResponse.toList(results);
    }

    @PostMapping("/api/v1/github/queue")
    public GithubRepositorySavedResponse addGithubRepo(@RequestBody AddGithupRepoRequestBody requestBody) {

        if (StringUtils.isBlank(requestBody.getOwner())) {
            throw BadRequestException.noOwnerSpecified();
        }

        if (StringUtils.isBlank(requestBody.getRepository())) {
            throw BadRequestException.noRepositorySpecified();
        }

        long id = githubService.insertGithubRepositoryIntoQueue(
                requestBody.getOwner(),
                requestBody.getRepository(),
                GithubRepositoryFilter.ENABLED);

        return new GithubRepositorySavedResponse(id);
    }

    @PostMapping("/api/v1/github/queue/filter")
    public GithubRepositorySavedResponse addGithubRepoWithFilters(
            @RequestBody AddGithubRepoFilterRequestBody requestBody) {

        if (StringUtils.isBlank(requestBody.getOwner())) {
            throw BadRequestException.noOwnerSpecified();
        }

        if (StringUtils.isBlank(requestBody.getRepository())) {
            throw BadRequestException.noRepositorySpecified();
        }

        if (requestBody.isRepositoryFilterEmpty() || requestBody.getRepositoryFilter().areAllFilterOptionsDisabled()) {
            throw BadRequestException.cantDisableAllRepositoryFilterOptions();
        }

        GithubRepositoryFilter githubRepositoryFilter
                = githubRepositoryFilterFactory.fromRepoFilterRequestModel(requestBody.getRepositoryFilter());

        long id = githubService.insertGithubRepositoryIntoQueue(
                requestBody.getOwner(), requestBody.getRepository(), githubRepositoryFilter);

        return new GithubRepositorySavedResponse(id);
    }

    @GetMapping(path = "/api/v1/github/rdf/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource downloadRdf(@PathVariable("id") String id, HttpServletResponse httpServletResponse) throws SQLException, IOException {

        long longId = LongUtils.convertStringToLongIdOrThrowException(id);

        if (!githubService.isRdfFileAvailable(longId)) {
            throw BadRequestException.noRdfFileAvailableYet();
        }

        File tempRdfFile = githubService.getTempRdfFile(longId);

        Resource responseResource = new InputStreamResource(new BufferedInputStream(new FileInputStream(tempRdfFile)));

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"rdf.ttl\"");

        return responseResource;
    }

    @DeleteMapping(path = "/api/v1/github/rdf/completedelete/{id}")
    public ResponseEntity<Void> deleteGitAndRdf(@PathVariable("id") String id) {

        long longId = LongUtils.convertStringToLongIdOrThrowException(id);
        githubService.completeDelete(longId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
