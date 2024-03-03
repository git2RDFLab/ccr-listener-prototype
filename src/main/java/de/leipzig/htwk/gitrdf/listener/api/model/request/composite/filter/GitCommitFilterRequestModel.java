package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import lombok.EqualsAndHashCode;

import java.beans.ConstructorProperties;

import static de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.RepoFilterRequestModel.returnValueOrFalseIfNull;

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

    private final Boolean enableCommitDiff;

    private final Boolean enableCommitBranch;

    private final Boolean enableCommitTag;


    @ConstructorProperties({
            "enableCommitHash",
            "enableAuthorName",
            "enableAuthorEmail",
            "enableAuthorDate",
            "enableCommitDate",
            "enableCommitterName",
            "enableCommitterEmail",
            "enableCommitMessage",
            "enableCommitDiff",
            "enableCommitBranch",
            "enableCommitTag"})
    public GitCommitFilterRequestModel(
            Boolean enableCommitHash,
            Boolean enableAuthorName,
            Boolean enableAuthorEmail,
            Boolean enableAuthorDate,
            Boolean enableCommitDate,
            Boolean enableCommitterName,
            Boolean enableCommitterEmail,
            Boolean enableCommitMessage,
            Boolean enableCommitDiff,
            Boolean enableCommitBranch,
            Boolean enableCommitTag) {

        this.enableCommitHash = enableCommitHash;
        this.enableAuthorName = enableAuthorName;
        this.enableAuthorEmail = enableAuthorEmail;
        this.enableAuthorDate = enableAuthorDate;
        this.enableCommitDate = enableCommitDate;
        this.enableCommitterName = enableCommitterName;
        this.enableCommitterEmail = enableCommitterEmail;
        this.enableCommitMessage = enableCommitMessage;
        this.enableCommitDiff = enableCommitDiff;
        this.enableCommitBranch = enableCommitBranch;
        this.enableCommitTag = enableCommitTag;
    }

    public boolean areAllFilterOptionsDisabled() {
        return !isCommitHashEnabled()
                && !isAuthorNameEnabled()
                && !isAuthorEmailEnabled()
                && !isAuthorDateEnabled()
                && !isCommitDateEnabled()
                && !isCommitterNameEnabled()
                && !isCommitterEmailEnabled()
                && !isCommitMessageEnabled()
                && !isCommitDiffEnabled()
                && !isCommitBranchEnabled()
                && !isCommitTagEnabled();
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

    public Boolean isCommitMessageEnabled() { return returnValueOrFalseIfNull(enableCommitMessage); }

    public Boolean isCommitDiffEnabled() { return returnValueOrFalseIfNull(enableCommitDiff); }
    public Boolean isCommitBranchEnabled() { return returnValueOrFalseIfNull(enableCommitBranch); }
    public Boolean isCommitTagEnabled() { return returnValueOrFalseIfNull(enableCommitTag); }
}
