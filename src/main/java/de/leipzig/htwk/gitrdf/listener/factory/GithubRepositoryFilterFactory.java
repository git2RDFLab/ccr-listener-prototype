package de.leipzig.htwk.gitrdf.listener.factory;

import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.GitCommitFilterRequestModel;
import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.RepoFilterRequestModel;
import de.leipzig.htwk.gitrdf.listener.database.entity.GitCommitRepositoryFilter;
import de.leipzig.htwk.gitrdf.listener.database.entity.GithubRepositoryFilter;
import org.springframework.stereotype.Component;

@Component
public class GithubRepositoryFilterFactory {

    public GithubRepositoryFilter fromRepoFilterRequestModel(RepoFilterRequestModel repoFilterRequestModel) {

        if (repoFilterRequestModel == null) {
            return GithubRepositoryFilter.ENABLED;
        }



    }

    private GithubRepositoryFilter convertFromNonNullRepoFilterRequestModel(
            RepoFilterRequestModel repoFilterRequestModel) {

        if (repoFilterRequestModel == null)  {
            throw new RuntimeException("Given repoFilterRequestModel is null but shouldn't be");
        }

        return new GithubRepositoryFilter(
                null, convertFromGitCommitFilterRequestModel(repoFilterRequestModel.getGitCommitFilter()));
    }

    private GitCommitRepositoryFilter convertFromGitCommitFilterRequestModel(GitCommitFilterRequestModel model) {
        return new GitCommitRepositoryFilter(
                null,
                model.isCommitHashEnabled(),
                model.isAuthorNameEnabled(),
                model.isAuthorEmailEnabled(),
                model.isAuthorDateEnabled(),
                model.isCommitDateEnabled(),
                model.isCommitterNameEnabled(),
                model.isCommitterEmailEnabled(),
                model.isCommitMessageEnabled());
    }

}
