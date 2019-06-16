package joel.fernandes.github.datasource.remote

import io.reactivex.Single
import joel.fernandes.github.data.datasource.GithubDataSourceRemote
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.datasource.model.RepoDetails

class GithubDataSourceRemoteImpl constructor(private val api : GithubAPI) : GithubDataSourceRemote {

    override fun getRepoDetails(owner: String, repo: String): Single<RepoDetails> =
        api.getRepoDetails(owner, repo)

    override fun getPullRequests(owner : String, repo : String, state : String, page : Int): Single<List<PullRequest>> =
        api.getPullRequests(owner, repo, state, page)
}