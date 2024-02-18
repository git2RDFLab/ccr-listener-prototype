package de.leipzig.htwk.gitrdf.listener.api.model.request.composite.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.beans.ConstructorProperties;

@EqualsAndHashCode
public class RepoFilterRequestModel {

    public static final RepoFilterRequestModel ENABLED = new RepoFilterRequestModel(GitCommitFilterRequestModel.ENABLED);

    public static final RepoFilterRequestModel DISABLED = new RepoFilterRequestModel(GitCommitFilterRequestModel.DISABLED);

    private final GitCommitFilterRequestModel gitCommitFilter;

    @ConstructorProperties({"gitCommitFilter"})
    public RepoFilterRequestModel(GitCommitFilterRequestModel gitCommitFilter) {
        this.gitCommitFilter = gitCommitFilter;
    }

    public boolean areAllFilterOptionsDisabled() {
        return
    }

    public GitCommitFilterRequestModel getGitCommitFilter() {
        return this.gitCommitFilter == null ? GitCommitFilterRequestModel.DISABLED : this.gitCommitFilter;
    }

}
