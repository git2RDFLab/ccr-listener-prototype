package de.leipzig.htwk.gitrdf.listener.service;

import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
     * @param fileInputStream  The byte stream of the .git repository (in the multipart file).
     * @param byteLengthOfFile The amount of bytes in the .git repository byte stream
     * @param fileName         The name of the file.
     * @return The id of the queue entry.
     */
    long insertGitMultipartFileIntoQueue(InputStream fileInputStream, long byteLengthOfFile, String fileName) throws IOException;

    File getTempRdfFile(long id) throws SQLException, IOException;

    void completeDelete(long id);

}
