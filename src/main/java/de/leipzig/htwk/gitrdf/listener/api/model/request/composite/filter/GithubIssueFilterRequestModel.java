package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

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

    public Boolean isIssueIdEnabled() {
        return returnValueOrFalseIfNull(enableIssueId);
    }

    public Boolean isIssueNumberEnabled() {
        return returnValueOrFalseIfNull(enableIssueNumber);
    }

    public Boolean isIssueStateEnabled() {
        return returnValueOrFalseIfNull(enableIssueState);
    }

    public Boolean isIssueTitleEnabled() {
        return returnValueOrFalseIfNull(enableIssueTitle);
    }

    public Boolean isIssueBodyEnabled() {
        return returnValueOrFalseIfNull(enableIssueBody);
    }

    public Boolean isIssueUserEnabled() {
        return returnValueOrFalseIfNull(enableIssueUser);
    }

    public Boolean isIssueLabelsEnabled() {
        return returnValueOrFalseIfNull(enableIssueLabels);
    }

    public Boolean isIssueAssigneesEnabled() {
        return returnValueOrFalseIfNull(enableIssueAssignees);
    }

    public Boolean isIssueMilestoneEnabled() {
        return returnValueOrFalseIfNull(enableIssueMilestone);
    }

    public Boolean isIssueCreatedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueCreatedAt);
    }

    public Boolean isIssueUpdatedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueUpdatedAt);
    }

    public Boolean isIssueClosedAtEnabled() {
        return returnValueOrFalseIfNull(enableIssueClosedAt);
    }
}
