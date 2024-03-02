package de.leipzig.htwk.gitrdf.listener.api.model.response;

import de.leipzig.htwk.gitrdf.database.common.entity.GitRepositoryOrderEntity;
import de.leipzig.htwk.gitrdf.database.common.entity.enums.GitRepositoryOrderStatus;
import lombok.Value;

import java.util.List;

@Value
public class GitRepositoryOrderResponse {

    public static GitRepositoryOrderResponse from(GitRepositoryOrderEntity entity) {
        return new GitRepositoryOrderResponse(
                entity.getId(),
                entity.getStatus(),
                entity.getNumberOfTries(),
                entity.getFileName());
    }

    public static List<GitRepositoryOrderResponse> toList(List<GitRepositoryOrderEntity> entities) {
        return entities.stream().map(GitRepositoryOrderResponse::from).toList();
    }

    long id;
    GitRepositoryOrderStatus status;
    int numberOfTries;
    String fileName;
}
