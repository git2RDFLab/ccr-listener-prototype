package de.leipzig.htwk.gitrdf.listener.database.entity.lob;

import de.leipzig.htwk.gitrdf.listener.database.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryOrderEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Data
@NoArgsConstructor
public class GithubRepositoryOrderEntityLobs {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private GithubRepositoryOrderEntity orderEntity;

    @Lob
    private Blob rdfFile;
}
