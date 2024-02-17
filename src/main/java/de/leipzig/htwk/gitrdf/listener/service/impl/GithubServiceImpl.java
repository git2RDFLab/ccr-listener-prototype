package de.leipzig.htwk.gitrdf.listener.service.impl;

import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.database.entity.lob.GitRepositoryOrderEntityLobs;
import de.leipzig.htwk.gitrdf.listener.database.entity.lob.GithubRepositoryOrderEntityLobs;
import de.leipzig.htwk.gitrdf.listener.database.repository.GithubRepositoryOrderRepository;
import de.leipzig.htwk.gitrdf.listener.service.GithubService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

@Service
public class GithubServiceImpl implements GithubService {

    private final EntityManager entityManager;

    private final GithubRepositoryOrderRepository githubRepositoryOrderRepository;

    public GithubServiceImpl(
            EntityManager entityManager,
            GithubRepositoryOrderRepository githubRepositoryOrderRepository) {

        this.entityManager = entityManager;
        this.githubRepositoryOrderRepository = githubRepositoryOrderRepository;
    }

    @Override
    public List<GithubRepositoryOrderEntity> findAll() {
        return githubRepositoryOrderRepository.findAll();
    }

    @Transactional
    @Override
    public long insertGithubRepositoryIntoQueue(String owner, String repository) {

        GithubRepositoryOrderEntity githubRepositoryOrderEntity
                = GithubRepositoryOrderEntity.newOrder(owner, repository);

        entityManager.persist(githubRepositoryOrderEntity);

        GithubRepositoryOrderEntityLobs githubRepositoryOrderEntityLobs = new GithubRepositoryOrderEntityLobs();
        githubRepositoryOrderEntityLobs.setOrderEntity(githubRepositoryOrderEntity);
        githubRepositoryOrderEntityLobs.setRdfFile(null);

        entityManager.persist(githubRepositoryOrderEntityLobs);

        return githubRepositoryOrderEntity.getId();
    }

    @Transactional
    @Override
    public File getTempRdfFile(long id) throws SQLException, IOException {

        GithubRepositoryOrderEntityLobs lob = entityManager.find(GithubRepositoryOrderEntityLobs.class, id);

        File tempFile = Files.createTempFile("RdfFileData", ".ttl").toFile();

        try (InputStream binaryInputStream = lob.getRdfFile().getBinaryStream()) {
            try (OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile))) {
                binaryInputStream.transferTo(fileOutputStream);
            }
        }

        return tempFile;
    }

    @Transactional
    @Override
    public void completeDelete(long id) {

        GithubRepositoryOrderEntity githubRepositoryOrderEntity
                = entityManager.find(GithubRepositoryOrderEntity.class, id);

        GithubRepositoryOrderEntityLobs githubRepositoryOrderEntityLobs
                = entityManager.find(GithubRepositoryOrderEntityLobs.class, id);

        entityManager.remove(githubRepositoryOrderEntity);
        entityManager.remove(githubRepositoryOrderEntityLobs);
    }

}
