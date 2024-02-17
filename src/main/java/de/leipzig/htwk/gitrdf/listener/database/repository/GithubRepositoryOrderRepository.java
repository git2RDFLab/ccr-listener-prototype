package de.leipzig.htwk.gitrdf.listener.database.repository;

import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepositoryOrderRepository extends JpaRepository<GithubRepositoryOrderEntity, Long> {
}
