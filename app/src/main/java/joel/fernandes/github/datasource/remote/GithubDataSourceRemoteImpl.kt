package joel.fernandes.github.datasource.remote

import io.reactivex.Single
import joel.fernandes.github.data.datasource.GithubDataSourceRemote
import joel.fernandes.github.datasource.model.PullRequest

class GithubDataSourceRemoteImpl constructor(private val api : GithubAPI) : GithubDataSourceRemote {

    override fun getPullRequests(owner : String, repo : String, state : String): Single<List<PullRequest>> =
        api.getPullRequests(owner, repo, state)
}