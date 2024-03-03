package de.leipzig.htwk.gitrdf.listener.factory;

import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.GitCommitFilterRequestModel;
import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.GithubIssueFilterRequestModel;
import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.RepoFilterRequestModel;
import de.leipzig.htwk.gitrdf.listener.database.entity.GitCommitRepositoryFilter;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubIssueRepositoryFilter;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryFilter;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryFilterFactory {

    public GithubRepositoryFilter fromRepoFilterRequestModel(RepoFilterRequestModel repoFilterRequestModel) {

        if (repoFilterRequestModel == null) {
            return GithubRepositoryFilter.ENABLED;
        }

        return convertFromNonNullRepoFilterRequestModel(repoFilterRequestModel);
    }

    private GithubRepositoryFilter convertFromNonNullRepoFilterRequestModel(
            RepoFilterRequestModel repoFilterRequestModel) {

        if (repoFilterRequestModel == null)  {
            throw new RuntimeException("Given repoFilterRequestModel is null but shouldn't be");
        }

        return new GithubRepositoryFilter(
                convertFromGitCommitFilterRequestModel(repoFilterRequestModel.getGitCommitFilter()),
                convertFromGithubIssueFilterRequestModel(repoFilterRequestModel.getGithubIssueFilter()));
    }

    private GitCommitRepositoryFilter convertFromGitCommitFilterRequestModel(GitCommitFilterRequestModel model) {
        return new GitCommitRepositoryFilter(
                model.isCommitHashEnabled(),
                model.isAuthorNameEnabled(),
                model.isAuthorEmailEnabled(),
                model.isAuthorDateEnabled(),
                model.isCommitDateEnabled(),
                model.isCommitterNameEnabled(),
                model.isCommitterEmailEnabled(),
                model.isCommitMessageEnabled(),
                model.isCommitDiffEnabled(),
                model.isCommitBranchEnabled(),
                model.isCommitTagEnabled());
    }

    private GithubIssueRepositoryFilter convertFromGithubIssueFilterRequestModel(GithubIssueFilterRequestModel model) {
        return new GithubIssueRepositoryFilter(
                model.isIssueIdEnabled(),
                model.isIssueStateEnabled(),
                model.isIssueTitleEnabled(),
                model.isIssueBodyEnabled(),
                model.isIssueUserEnabled(),
                model.isIssueLabelsEnabled(),
                model.isIssueAssigneesEnabled(),
                model.isIssueMilestoneEnabled(),
                model.isIssueCreatedAtEnabled(),
                model.isIssueUpdatedAtEnabled(),
                model.isIssueClosedAtEnabled());
    }

}
