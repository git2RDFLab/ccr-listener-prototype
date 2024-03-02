package de.leipzig.htwk.gitrdf.listener.api.model.response;

import de.leipzig.htwk.gitrdf.database.common.entity.GithubRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.database.common.entity.enums.GitRepositoryOrderStatus;
import lombok.Value;

import java.util.List;

@Value
public class GithubRepositoryOrderResponse {

    public static GithubRepositoryOrderResponse from(GithubRepositoryOrderEntity entity) {
        return new GithubRepositoryOrderResponse(
                entity.getId(),
                entity.getStatus(),
                entity.getNumberOfTries(),
                entity.getOwnerName(),
                entity.getRepositoryName());
    }

    public static List<GithubRepositoryOrderResponse> toList(List<GithubRepositoryOrderEntity> entities) {
        return entities.stream().map(GithubRepositoryOrderResponse::from).toList();
    }

    long id;
    GitRepositoryOrderStatus status;
    int numberOfTries;
    String owner;
    String repository;
}
