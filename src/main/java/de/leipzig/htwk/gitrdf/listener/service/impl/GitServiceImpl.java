package de.leipzig.htwk.gitrdf.listener.service.impl;

import de.leipzig.htwk.gitrdf.database.common.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.database.common.entity.lob.GitRepositoryOrderEntityLobs;
import de.leipzig.htwk.gitrdf.database.common.repository.GitRepositoryOrderRepository;
import de.leipzig.htwk.gitrdf.listener.service.GitService;
import jakarta.persistence.EntityManager;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

@Service
public class GitServiceImpl implements GitService {

    private final EntityManager entityManager;

    private final GitRepositoryOrderRepository gitRepositoryOrderRepository;

    public GitServiceImpl(EntityManager entityManager, GitRepositoryOrderRepository gitRepositoryOrderRepository) {
        this.entityManager = entityManager;
        this.gitRepositoryOrderRepository = gitRepositoryOrderRepository;
    }

    @Override
    public List<GitRepositoryOrderEntity> findAll() {
        return gitRepositoryOrderRepository.findAll();
    }

    @Override
    @Transactional
    public long insertGitMultipartFileIntoQueue(
            InputStream fileInputStream,
            long byteLengthOfFile,
            String fileName) throws IOException {

        GitRepositoryOrderEntity gitRepositoryOrderEntity = GitRepositoryOrderEntity.newOrder(fileName);
        entityManager.persist(gitRepositoryOrderEntity);

        GitRepositoryOrderEntityLobs gitRepositoryOrderEntityLobs = new GitRepositoryOrderEntityLobs();
        gitRepositoryOrderEntityLobs.setOrderEntity(gitRepositoryOrderEntity);
        gitRepositoryOrderEntityLobs.setRdfFile(null);

        gitRepositoryOrderEntityLobs.setGitZipFile(BlobProxy.generateProxy(fileInputStream, byteLengthOfFile));

        //try (InputStream zipStream = file.getInputStream()) {
            //gitRepositoryOrderEntityLobs.setGitZipFile(BlobProxy.generateProxy(zipStream, file.getSize()));
        //}

        //try (InputStream inputStream = new BufferedInputStream(file.getInputStream())) {
            //gitRepositoryOrderEntityLobs.setGitZipFile(BlobProxy.generateProxy(inputStream, file.getSize()));
        //}

        //gitRepositoryOrderEntityLobs.setGitZipFile(BlobProxy.generateProxy(file.getBytes()));

        entityManager.persist(gitRepositoryOrderEntityLobs);

        return gitRepositoryOrderEntity.getId();
    }

    @Override
    @Transactional
    public File getTempRdfFile(long id) throws SQLException, IOException {
        GitRepositoryOrderEntityLobs lob = entityManager.find(GitRepositoryOrderEntityLobs.class, id);

        File tempFile = Files.createTempFile("RdfFileData", ".ttl").toFile();

        try (InputStream binaryInputStream = lob.getRdfFile().getBinaryStream()) {
            try (OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile))) {
                binaryInputStream.transferTo(fileOutputStream);
            }
        }

        return tempFile;
    }

    @Override
    @Transactional
    public void completeDelete(long id) {

        GitRepositoryOrderEntity gitRepositoryOrderEntity
                = entityManager.find(GitRepositoryOrderEntity.class, id);

        GitRepositoryOrderEntityLobs gitRepositoryOrderEntityLobs
                = entityManager.find(GitRepositoryOrderEntityLobs.class, id);

        entityManager.remove(gitRepositoryOrderEntityLobs);
        entityManager.remove(gitRepositoryOrderEntity);
    }
}
