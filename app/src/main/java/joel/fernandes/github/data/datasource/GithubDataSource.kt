package joel.fernandes.github.data.datasource

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequest

interface GithubDataSourceRemote {

    fun getPullRequests(owner : String, repo : String, state : String) : Single<List<PullRequest>>
}