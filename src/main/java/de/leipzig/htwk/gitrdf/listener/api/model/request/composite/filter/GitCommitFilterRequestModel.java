package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import io.swagger.v3.oas.annotations.media.Schema;
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

    private final Boolean enableBranchSnapshot;


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
            "enableCommitTag",
            "enableBranchSnapshot"})

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
            Boolean enableCommitTag,
            Boolean enableBranchSnapshot) {

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
        this.enableBranchSnapshot = enableBranchSnapshot;
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
                && !isCommitTagEnabled()
                && !isBranchSnapshotEnabled();
    }

    @Schema(hidden = true)
    public Boolean isCommitHashEnabled() {
        return returnValueOrFalseIfNull(enableCommitHash);
    }

    @Schema(hidden = true)
    public Boolean isAuthorNameEnabled() {
        return returnValueOrFalseIfNull(enableAuthorName);
    }

    @Schema(hidden = true)
    public Boolean isAuthorEmailEnabled() {
        return returnValueOrFalseIfNull(enableAuthorEmail);
    }

    @Schema(hidden = true)
    public Boolean isAuthorDateEnabled() {
        return returnValueOrFalseIfNull(enableAuthorDate);
    }

    @Schema(hidden = true)
    public Boolean isCommitDateEnabled() {
        return returnValueOrFalseIfNull(enableCommitDate);
    }

    @Schema(hidden = true)
    public Boolean isCommitterNameEnabled() {
        return returnValueOrFalseIfNull(enableCommitterName);
    }

    @Schema(hidden = true)
    public Boolean isCommitterEmailEnabled() {
        return returnValueOrFalseIfNull(enableCommitterEmail);
    }

    @Schema(hidden = true)
    public Boolean isCommitMessageEnabled() {
        return returnValueOrFalseIfNull(enableCommitMessage);
    }

    @Schema(hidden = true)
    public Boolean isCommitDiffEnabled() {
        return returnValueOrFalseIfNull(enableCommitDiff);
    }

    @Schema(hidden = true)
    public Boolean isCommitBranchEnabled() {
        return returnValueOrFalseIfNull(enableCommitBranch);
    }

    @Schema(hidden = true)
    public Boolean isCommitTagEnabled() {
        return returnValueOrFalseIfNull(enableCommitTag);
    }

    @Schema(hidden = true)
    public Boolean isBranchSnapshotEnabled() {
        return returnValueOrFalseIfNull(enableBranchSnapshot);
    }
}
