package joel.fernandes.github.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.datasource.model.RepoDetails
import joel.fernandes.github.domain.usecases.UCGithubRepos

class GithubViewModel constructor(private val githubUseCases : UCGithubRepos) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pullRequestsList = MutableLiveData<PullRequestsList>()
    val repoDetails = MutableLiveData<RepoDetails>()

    fun getRepoDetails(owner : String, repo : String) = compositeDisposable.add(githubUseCases.getRepoDetails(owner, repo)
        .subscribeOn(Schedulers.io())
        .subscribe({
            repoDetails.postValue(it)
        }, {
            it.printStackTrace()
        })
    )


    fun getPullRequests(owner : String, repo : String, state : String, page : Int) =
        compositeDisposable.add(githubUseCases.getPullRequests(owner, repo, state, page)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .subscribe({
                pullRequestsList.postValue(it)
            }, {
                it.printStackTrace()
            })
        )
}