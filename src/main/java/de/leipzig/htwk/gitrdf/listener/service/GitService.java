package de.leipzig.htwk.gitrdf.listener.service;

import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GitService {

    /**
     * Find all .git repository order entries.
     *
     * @return List of GitRepGitRepositoryOrderEntity
     */
    List<GitRepositoryOrderEntity> findAll();

    /**
     * Inserts the given .git repository (in the multipart file) into the worker queue.
     *
     * @param file The .git repository (in the multipart file).
     * @param fileName The name of the file.
     * @return The id of the queue entry.
     */
    long insertGitMultipartFileIntoQueue(MultipartFile file, String fileName) throws IOException;



}
