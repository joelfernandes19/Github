package joel.fernandes.github.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.domain.usecases.UCGithubRepos

class GithubViewModel constructor(private val githubUseCases : UCGithubRepos) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val pullRequestsList = MutableLiveData<PullRequestsList>()

    fun getPullRequests(owner : String, repo : String, state : String) =
        compositeDisposable.add(githubUseCases.getPullRequests(owner, repo, state)
            .subscribeOn(Schedulers.io())
            .map {
                it
            }
            .subscribe({
                if(!it.list.isNullOrEmpty()) {
                    pullRequestsList.postValue(it)
                }

            }, {
                it.printStackTrace()
            })
        )
}