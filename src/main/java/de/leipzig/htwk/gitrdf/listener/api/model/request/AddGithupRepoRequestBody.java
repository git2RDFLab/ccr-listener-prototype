package de.leipzig.htwk.gitrdf.listener.api.model.request;

import lombok.Getter;
import lombok.Value;

@Getter
public class AddGithupRepoRequestBody {

    private final String owner;

    private final String repository;

    public AddGithupRepoRequestBody(String owner, String repository) {
        this.owner = owner;
        this.repository = repository;
    }
}
