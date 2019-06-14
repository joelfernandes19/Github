package joel.fernandes.github.data.repository

import io.reactivex.Single
import joel.fernandes.github.data.datasource.GithubDataSourceRemote
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.domain.repository.GithubRepository

class GithubRepositoryImpl constructor(private val remote : GithubDataSourceRemote) : GithubRepository {

    override fun getPullRequests(owner : String, repo : String, state : String): Single<PullRequestsList> {
        return Single.create { emitter ->
            remote.getPullRequests(owner, repo, state)
                .subscribe({
                    emitter.onSuccess(PullRequestsList(list = it))
                }, {
                    it.printStackTrace()
                    emitter.onError(it)
                })
        }
    }
}