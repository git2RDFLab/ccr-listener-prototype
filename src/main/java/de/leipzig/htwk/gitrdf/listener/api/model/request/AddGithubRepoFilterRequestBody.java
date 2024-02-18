package de.leipzig.htwk.gitrdf.listener.api.model.request;

import de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter.RepoFilterRequestModel;

import java.beans.ConstructorProperties;

public class AddGithubRepoFilterRequestBody extends AddGithupRepoRequestBody {

    private final RepoFilterRequestModel repositoryFilter;

    @ConstructorProperties({"owner", "repository", "repositoryFilter"})
    public AddGithubRepoFilterRequestBody(String owner, String repository, RepoFilterRequestModel repositoryFilter) {
        super(owner, repository);
        this.repositoryFilter = repositoryFilter;
    }

    public RepoFilterRequestModel getRepositoryFilter() {
        return this.repositoryFilter == null ? RepoFilterRequestModel.DISABLED : this.repositoryFilter;
    }

    public boolean isRepositoryFilterEmpty() {
        return this.repositoryFilter == null;
    }

}
