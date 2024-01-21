package de.leipzig.htwk.gitrdf.listener.database.repository;

import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitRepositoryOrderRepository extends JpaRepository<GitRepositoryOrderEntity, Long> {
}
