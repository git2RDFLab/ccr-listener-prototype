package de.leipzig.htwk.gitrdf.listener.api.model.request;

import lombok.Value;

@Value
public class AddGithupRepoRequestBody {
    String owner;
    String repository;
}
