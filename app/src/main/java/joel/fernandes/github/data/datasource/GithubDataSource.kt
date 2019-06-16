package joel.fernandes.github.data.datasource

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.datasource.model.RepoDetails

interface GithubDataSourceRemote {

    fun getRepoDetails(owner : String, repo : String) : Single<RepoDetails>
    fun getPullRequests(owner : String, repo : String, state : String, page : Int) : Single<List<PullRequest>>
}