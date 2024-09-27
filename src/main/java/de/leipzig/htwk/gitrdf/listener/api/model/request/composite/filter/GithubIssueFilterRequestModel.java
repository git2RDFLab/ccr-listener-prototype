package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;

import java.beans.ConstructorProperties;

import static de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.RepoFilterRequestModel.returnValueOrFalseIfNull;

@EqualsAndHashCode
public class GithubIssueFilterRequestModel {

    public static final GithubIssueFilterRequestModel ENABLED = new GithubIssueFilterRequestModel(
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

    public static final GithubIssueFilterRequestModel DISABLED = new GithubIssueFilterRequestModel(
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

    private final Boolean enableIssueId;

    private final Boolean enableIssueNumber;

    private final Boolean enableIssueState;

    private final Boolean enableIssueTitle;

    private final Boolean enableIssueBody;

    private final Boolean enableIssueUser;

    private final Boolean enableIssueLabels;

    private final Boolean enableIssueAssignees;

    private final Boolean enableIssueMilestone;

    private final Boolean enableIssueCreatedAt;

    private final Boolean enableIssueUpdatedAt;

    private final Boolean enableIssueClosedAt;


    @ConstructorProperties({
            "enableIssueId",
            "enableIssueNumber",
            "enableIssueState",
            "enableIssueTitle",
            "enableIssueBody",
            "enableIssueUser",
            "enableIssueLabels",
            "enableIssueAssignees",
            "enableIssueMilestone",
            "enableIssueCreatedAt",
            "enableIssueUpdatedAt",
            "enableIssueClosedAt"})
    public GithubIssueFilterRequestModel(
            Boolean enableIssueId,
            Boolean enableIssueNumber,
            Boolean enableIssueState,
            Boolean enableIssueTitle,
            Boolean enableIssueBody,
            Boolean enableIssueUser,
            Boolean enableIssueLabels,
            Boolean enableIssueAssignees,
            Boolean enableIssueMilestone,
            Boolean enableIssueCreatedAt,
            Boolean enableIssueUpdatedAt,
            Boolean enableIssueClosedAt) {

        this.enableIssueId = enableIssueId;
        this.enableIssueNumber = enableIssueNumber;
        this.enableIssueState = enableIssueState;
        this.enableIssueTitle = enableIssueTitle;
        this.enableIssueBody = enableIssueBody;
        this.enableIssueUser = enableIssueUser;
        this.enableIssueLabels = enableIssueLabels;
        this.enableIssueAssignees = enableIssueAssignees;
        this.enableIssueMilestone = enableIssueMilestone;
        this.enableIssueCreatedAt = enableIssueCreatedAt;
        this.enableIssueUpdatedAt = enableIssueUpdatedAt;
        this.enableIssueClosedAt = enableIssueClosedAt;
    }

    public boolean areAllFilterOptionsDisabled() {
        return !isIssueIdEnabled()
                && !isIssueNumberEnabled()
                && !isIssueStateEnabled()
                && !isIssueTitleEnabled()
                && !isIssueBodyEnabled()
                && !isIssueUserEnabled()
                && !isIssueLabelsEnabled()
                && !isIssueAssigneesEnabled()
                && !isIssueMilestoneEnabled()
                && !isIssueCreatedAtEnabled()
                && !isIssueUpdatedAtEnabled()
                && !isIssueClosedAtEnabled();
    }

    @Schema(hidden = true)
    public Boolean isIssueIdEnabled() {
        return returnValueOrFalseIfNull(enableIssueId);
    }

    @Schema(hidden = true)
    public Boolean isIssueNumberEnabled() {
        return returnValueOrFalseIfNull(enableIssueNumber);
    }

    @Schema(hidden = true)
    public Boolean isIssueStateEnabled() {
        return returnValueOrFalseIfNull(enableIssueState);
    }

    @Schema(hidden = true)
    public Boolean isIssueTitleEnabled() {
        return returnValueOrFalseIfNull(enableIssueTitle);
    }

    @Schema(hidden = true)
    public Boolean isIssueBodyEnabled() {
        return returnValueOrFalseIfNull(enableIssueBody);
    }

    @Schema(hidden = true)
    public Boolean isIssueUserEnabled() {
        return returnValueOrFalseIfNull(enableIssueUser);
    }

    @Schema(hidden = true)
    public Boolean isIssueLabelsEnabled() {
        return returnValueOrFalseIfNull(enableIssueLabels);
    }

    @Schema(hidden = true)
    public Boolean isIssueAssigneesEnabled() {
        return returnValueOrFalseIfNull(enableIssueAssignees);
    }

    @Schema(hidden = true)
    public Boolean isIssueMilestoneEnabled() {
        return returnValueOrFalseIfNull(enableIssueMilestone);
    }

    @Schema(hidden = true)
    public Boolean isIssueCreatedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueCreatedAt);
    }

    @Schema(hidden = true)
    public Boolean isIssueUpdatedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueUpdatedAt);
    }

    @Schema(hidden = true)
    public Boolean isIssueClosedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueClosedAt);
    }
}
