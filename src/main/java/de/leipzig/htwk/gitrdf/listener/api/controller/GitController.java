package de.leipzig.htwk.gitrdf.listener.api.controller;

import de.leipzig.htwk.gitrdf.database.common.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.api.exception.BadRequestException;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GitRepositoryOrderResponse;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GitRepositorySavedResponse;
import de.leipzig.htwk.gitrdf.listener.service.GitService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GitController {

    private final GitService gitService;

    @GetMapping("/api/v1/git")
    public List<GitRepositoryOrderResponse> getAllGitRepositoryOrderEntries() {
        List<GitRepositoryOrderEntity> results = gitService.findAll();
        return GitRepositoryOrderResponse.toList(results);
    }

    @PostMapping(path = "/api/v1/git/upload")
    public GitRepositorySavedResponse uploadGitRepository(
            @RequestParam("file") MultipartFile file, @RequestParam("name") String fileName) throws IOException {

        if (file.isEmpty()) {
            throw BadRequestException.noMultiPartFileWasGiven();
        }

        if (StringUtils.isBlank(fileName)) {
            throw BadRequestException.noFileNameWasGiven();
        }

        String expectedContentType = "application/zip";
        if (!file.getContentType().equals(expectedContentType)) {
            throw BadRequestException.unsupportedContentType(expectedContentType);
        }

        boolean isValidDotGitZip = multipartFileIsZipFileAndOnlyContainsDotGitFolderStructure(file);

        if (!isValidDotGitZip) {
            throw BadRequestException.invalidZipFile();
        }

        Long id = null;
        try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            id = gitService.insertGitMultipartFileIntoQueue(inputStream, file.getSize(), fileName);
        }

        return new GitRepositorySavedResponse(id);
    }

    //@GetMapping(path = "/api/v1/git/rdf/download/{id}", produces = "text/ttl; charset=utf-8")
    @GetMapping(path = "/api/v1/git/rdf/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource downloadRdf(@PathVariable("id") String id, HttpServletResponse httpServletResponse) throws SQLException, IOException {

        long longId = LongUtils.convertStringToLongIdOrThrowException(id);

        File tempRdfFile = gitService.getTempRdfFile(longId);

        Resource responseResource = new InputStreamResource(new BufferedInputStream(new FileInputStream(tempRdfFile)));

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"rdf.ttl\"");

        return responseResource;
    }

    @DeleteMapping(path = "/api/v1/git/rdf/completedelete/{id}")
    public ResponseEntity<Void> deleteGitAndRdf(@PathVariable("id") String id) {

        long longId = LongUtils.convertStringToLongIdOrThrowException(id);
        gitService.completeDelete(longId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean multipartFileIsZipFileAndOnlyContainsDotGitFolderStructure(MultipartFile file) throws IOException {

        try (ZipInputStream zipStream = new ZipInputStream(file.getInputStream())) {

            ZipEntry zipEntry;

            while ((zipEntry = zipStream.getNextEntry()) != null) {

                if (isTopLevelDirectory(zipEntry)) {

                    String entryName = removeTrailingSlash(zipEntry.getName());

                    if (!entryName.equals(".git")) {
                        return false;
                    }

                } else if (!entryIsPartOfDotGitFileParent(zipEntry)) {
                    return false;
                }

                zipStream.closeEntry();
            }

        }

        return true;
    }

    private boolean isTopLevelDirectory(ZipEntry zipEntry) {
        return zipEntry.isDirectory() && !removeTrailingSlash(zipEntry.getName()).contains("/");
    }

    private boolean entryIsPartOfDotGitFileParent(ZipEntry zipEntry) {
        return zipEntry.getName().startsWith(".git");
    }

    private String removeTrailingSlash(String value) {
        return value.substring(0, value.length() - 1);
    }

}
