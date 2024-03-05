package de.leipzig.htwk.gitrdf.listener.api.controller;

import de.leipzig.htwk.gitrdf.database.common.entity.GithubRepositoryFilter;
import de.leipzig.htwk.gitrdf.database.common.entity.GithubRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.api.documentation.*;
import de.leipzig.htwk.gitrdf.listener.api.exception.BadRequestException;
import de.leipzig.htwk.gitrdf.listener.api.model.request.AddGithubRepoFilterRequestBody;
import de.leipzig.htwk.gitrdf.listener.api.model.request.AddGithupRepoRequestBody;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GithubRepositoryOrderResponse;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GithubRepositorySavedResponse;
import de.leipzig.htwk.gitrdf.listener.factory.GithubRepositoryFilterFactory;
import de.leipzig.htwk.gitrdf.listener.service.GithubService;
import de.leipzig.htwk.gitrdf.listener.utils.LongUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping(path = "/listener-service/api/v1/github")
public class GithubController {

    private final GithubService githubService;

    private final GithubRepositoryFilterFactory githubRepositoryFilterFactory;

    @Operation(summary = "Get all github order entries")
    @ApiResponse(
            responseCode = "200",
            description = "All github order entries",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(implementation = GithubRepositoryOrderResponse.class))))
    @GeneralInternalServerErrorApiResponse
    @GetMapping
    public List<GithubRepositoryOrderResponse> getAllGithubRepositoryOrderEntries() {
        List<GithubRepositoryOrderEntity> results = githubService.findAll();
        return  GithubRepositoryOrderResponse.toList(results);
    }

    @Operation(summary = "Add a github entry to the queue")
    @ApiResponse(
            responseCode = "200",
            description = "Added the specified github entry to the queue",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GithubRepositorySavedResponse.class)))
    @NoOwnerSpecifiedBadRequestApiResponse
    @NoRepositorySpecifiedBadRequestApiResponse
    @GeneralInternalServerErrorApiResponse
    @PostMapping("/queue")
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

    @Operation(
            summary = "Add a github entry to the queue while also specifying filter properties",
            description = "At least one filter property has to be set. Left out filter properties will be disabled.")
    @ApiResponse(
            responseCode = "200",
            description = "Added the specified github entry with the specified filter properties to the queue",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = GithubRepositorySavedResponse.class)))
    @NoOwnerSpecifiedBadRequestApiResponse
    @NoRepositorySpecifiedBadRequestApiResponse
    @CantDisableAllFilterOptionsBadRequestApiResponse
    @GeneralInternalServerErrorApiResponse
    @PostMapping("/queue/filter")
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

    @Operation(summary = "Download the produced rdf file")
    @ApiResponse(
            responseCode = "200",
            description = "Rdf file",
            content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
    @InvalidLongIdBadRequestApiResponse
    @NoRdfFileAvailableYetBadRequestApiResponse
    @GeneralInternalServerErrorApiResponse
    @GetMapping(path = "/rdf/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
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

    @Operation(summary = "Delete specified github order entry and all connected data")
    @ApiResponse(
            responseCode = "204",
            description = "Successfully deleted github entry")
    @InvalidLongIdBadRequestApiResponse
    @GeneralInternalServerErrorApiResponse
    @DeleteMapping(path = "/rdf/completedelete/{id}")
    public ResponseEntity<Void> deleteGitAndRdf(@PathVariable("id") String id) {

        long longId = LongUtils.convertStringToLongIdOrThrowException(id);
        githubService.completeDelete(longId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
