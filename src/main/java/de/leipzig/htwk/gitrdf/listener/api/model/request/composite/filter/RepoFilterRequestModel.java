package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.beans.ConstructorProperties;

@EqualsAndHashCode
public class RepoFilterRequestModel {

    public static final RepoFilterRequestModel ENABLED
            = new RepoFilterRequestModel(GitCommitFilterRequestModel.ENABLED, GithubIssueFilterRequestModel.ENABLED);

    public static final RepoFilterRequestModel DISABLED
            = new RepoFilterRequestModel(GitCommitFilterRequestModel.DISABLED, GithubIssueFilterRequestModel.DISABLED);

    private final GitCommitFilterRequestModel gitCommitFilter;

    private final GithubIssueFilterRequestModel githubIssueFilter;


    @ConstructorProperties({"gitCommitFilter", "githubIssueFilter"})
    public RepoFilterRequestModel(
            GitCommitFilterRequestModel gitCommitFilter,
            GithubIssueFilterRequestModel githubIssueFilter) {

        this.gitCommitFilter = gitCommitFilter;
        this.githubIssueFilter = githubIssueFilter;
    }

    public boolean areAllFilterOptionsDisabled() {
        return gitCommitFilter.areAllFilterOptionsDisabled() && githubIssueFilter.areAllFilterOptionsDisabled();
    }

    public GitCommitFilterRequestModel getGitCommitFilter() {
        return this.gitCommitFilter == null ? GitCommitFilterRequestModel.DISABLED : this.gitCommitFilter;
    }

    public GithubIssueFilterRequestModel getGithubIssueFilter() {
        return this.githubIssueFilter == null ? GithubIssueFilterRequestModel.DISABLED : this.githubIssueFilter;
    }

    public static boolean returnValueOrFalseIfNull(Boolean value) {
        return value == null ? false : value;
    }

}
