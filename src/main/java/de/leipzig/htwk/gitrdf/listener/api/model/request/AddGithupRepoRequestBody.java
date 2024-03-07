package de.leipzig.htwk.gitrdf.listener.api.model.request;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Value;

@Getter
public class AddGithupRepoRequestBody {

    @NotBlank
    @Size(max = 255)
    @Schema(example = "dotnet")
    private final String owner;

    @NotBlank
    @Size(max = 255)
    @Schema(example = "core")
    private final String repository;

    public AddGithupRepoRequestBody(String owner, String repository) {
        this.owner = owner;
        this.repository = repository;
    }
}
