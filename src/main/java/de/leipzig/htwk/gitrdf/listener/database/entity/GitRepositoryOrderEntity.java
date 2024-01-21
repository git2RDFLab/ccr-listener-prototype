package de.leipzig.htwk.gitrdf.listener.database.entity;

import de.leipzig.htwk.gitrdf.listener.database.entity.enums.GitRepositoryOrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gitrepositoryorder")
@Data
@NoArgsConstructor
public class GitRepositoryOrderEntity {

    public static GitRepositoryOrderEntity newOrder() {

        GitRepositoryOrderEntity gitRepositoryOrderEntity = new GitRepositoryOrderEntity();

        gitRepositoryOrderEntity.setStatus(GitRepositoryOrderStatus.RECEIVED);
        gitRepositoryOrderEntity.setNumberOfTries(0);

        return gitRepositoryOrderEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GitRepositoryOrderStatus status;

    @Column(nullable = false)
    private int numberOfTries;


}
