package de.leipzig.htwk.gitrdf.listener.api.controller;

import de.leipzig.htwk.gitrdf.listener.api.exception.BadRequestException;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GitRepositoryOrderResponse;
import de.leipzig.htwk.gitrdf.listener.api.model.response.GitRepositorySavedResponse;
import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.service.GitService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequiredArgsConstructor
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

        long id = gitService.insertGitMultipartFileIntoQueue(file, fileName);

        return new GitRepositorySavedResponse(id);
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
