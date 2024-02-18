package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import lombok.EqualsAndHashCode;

import java.beans.ConstructorProperties;

@EqualsAndHashCode
public class GitCommitFilterRequestModel {

    public static final GitCommitFilterRequestModel ENABLED = new GitCommitFilterRequestModel(
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true);

    public static final GitCommitFilterRequestModel DISABLED = new GitCommitFilterRequestModel(
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false);

    private final Boolean enableCommitHash;

    private final Boolean enableAuthorName;

    private final Boolean enableAuthorEmail;

    private final Boolean enableAuthorDate;

    private final Boolean enableCommitDate;

    private final Boolean enableCommitterName;

    private final Boolean enableCommitterEmail;

    private final Boolean enableCommitMessage;


    @ConstructorProperties({
            "enableCommitHash",
            "enableAuthorName",
            "enableAuthorEmail",
            "enableAuthorDate",
            "enableCommitDate",
            "enableCommitterName",
            "enableCommitterEmail",
            "enableCommitMessage"})
    public GitCommitFilterRequestModel(
            Boolean enableCommitHash,
            Boolean enableAuthorName,
            Boolean enableAuthorEmail,
            Boolean enableAuthorDate,
            Boolean enableCommitDate,
            Boolean enableCommitterName,
            Boolean enableCommitterEmail,
            Boolean enableCommitMessage) {

        this.enableCommitHash = enableCommitHash;
        this.enableAuthorName = enableAuthorName;
        this.enableAuthorEmail = enableAuthorEmail;
        this.enableAuthorDate = enableAuthorDate;
        this.enableCommitDate = enableCommitDate;
        this.enableCommitterName = enableCommitterName;
        this.enableCommitterEmail = enableCommitterEmail;
        this.enableCommitMessage = enableCommitMessage;
    }

    public boolean areAllFilterOptionsDisabled() {
        // TODO (ccr): Continue here - 18.02.2024 - 20:22
    }

    public Boolean isCommitHashEnabled() {
        return returnValueOrFalseIfNull(enableCommitHash);
    }

    public Boolean isAuthorNameEnabled() {
        return returnValueOrFalseIfNull(enableAuthorName);
    }

    public Boolean isAuthorEmailEnabled() {
        return returnValueOrFalseIfNull(enableAuthorEmail);
    }

    public Boolean isAuthorDateEnabled() {
        return returnValueOrFalseIfNull(enableAuthorDate);
    }

    public Boolean isCommitDateEnabled() {
        return returnValueOrFalseIfNull(enableCommitDate);
    }

    public Boolean isCommitterNameEnabled() {
        return returnValueOrFalseIfNull(enableCommitterName);
    }

    public Boolean isCommitterEmailEnabled() {
        return returnValueOrFalseIfNull(enableCommitterEmail);
    }

    public Boolean isCommitMessageEnabled() {
        return returnValueOrFalseIfNull(enableCommitMessage);
    }

    private boolean returnValueOrFalseIfNull(Boolean value) {
        return value == null ? false : value;
    }
}
