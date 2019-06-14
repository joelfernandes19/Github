package joel.fernandes.github.domain.repository

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequestsList

interface GithubRepository {
    fun getPullRequests(owner : String, repo : String, state : String) : Single<PullRequestsList>
}