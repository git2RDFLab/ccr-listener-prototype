package de.leipzig.htwk.gitrdf.listener.service;

import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryFilter;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryOrderEntity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GithubService {
    public List<GithubRepositoryOrderEntity> findAll();

    long insertGithubRepositoryIntoQueue(String owner, String repository, GithubRepositoryFilter githubRepositoryFilter);

    boolean isRdfFileAvailable(long id);

    File getTempRdfFile(long id) throws SQLException, IOException;

    void completeDelete(long id);
}
