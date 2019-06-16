package joel.fernandes.github.presentation.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.datasource.model.RepoDetails
import joel.fernandes.github.domain.usecases.UCGithubRepos
import joel.fernandes.github.presentation.base.StateLiveData

class GithubViewModel constructor(private val githubUseCases : UCGithubRepos) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pullRequestsList = StateLiveData<PullRequestsList>()
    val repoDetails = StateLiveData<RepoDetails>()

    fun getRepoDetails(owner : String, repo : String) = compositeDisposable.add(githubUseCases.getRepoDetails(owner, repo)
        .subscribeOn(Schedulers.io())
        .subscribe({
            repoDetails.postSuccess(it)
        }, {
            it.printStackTrace()
            repoDetails.postError("An error occurred ".plus(it.message.toString()))
        })
    )


    fun getPullRequests(owner : String, repo : String, state : String, page : Int) =
        compositeDisposable.add(githubUseCases.getPullRequests(owner, repo, state, page)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .subscribe({
                pullRequestsList.postSuccess(it)
            }, {
                it.printStackTrace()
                pullRequestsList.postError("An error occurred ".plus(it.message.toString()))
            })
        )


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}