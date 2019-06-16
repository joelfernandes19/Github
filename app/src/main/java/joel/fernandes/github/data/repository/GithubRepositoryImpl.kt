package joel.fernandes.github.data.repository

import io.reactivex.Single
import joel.fernandes.github.data.datasource.GithubDataSourceRemote
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.datasource.model.RepoDetails
import joel.fernandes.github.domain.repository.GithubRepository

class GithubRepositoryImpl constructor(private val remote : GithubDataSourceRemote) : GithubRepository {

    override fun getRepoDetails(owner: String, repo: String): Single<RepoDetails> {
        return Single.create { emitter ->
            remote.getRepoDetails(owner, repo)
                .subscribe({
                    emitter.onSuccess(it)
                }, {
                    it.printStackTrace()
                    emitter.onError(it)
                })
        }
    }

    override fun getPullRequests(owner : String, repo : String, state : String, page : Int): Single<PullRequestsList> {
        return Single.create { emitter ->
            remote.getPullRequests(owner, repo, state, page)
                .subscribe({
                    emitter.onSuccess(PullRequestsList(list = it))
                }, {
                    it.printStackTrace()
                    emitter.onError(it)
                })
        }
    }
}