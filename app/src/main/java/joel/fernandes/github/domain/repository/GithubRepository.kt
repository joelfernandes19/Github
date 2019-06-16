package joel.fernandes.github.domain.repository

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.datasource.model.RepoDetails

interface GithubRepository {
    fun getRepoDetails(owner : String, repo : String) : Single<RepoDetails>
    fun getPullRequests(owner : String, repo : String, state : String, page : Int) : Single<PullRequestsList>
}