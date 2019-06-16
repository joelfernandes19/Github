package joel.fernandes.github.domain.usecases

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequestsList
import joel.fernandes.github.datasource.model.RepoDetails
import joel.fernandes.github.domain.repository.GithubRepository

class UCGithubRepos constructor(private val githubRepository: GithubRepository) {

    fun getRepoDetails(owner : String, repo : String) : Single<RepoDetails> = githubRepository.getRepoDetails(owner, repo)
    fun getPullRequests(owner : String, repo : String, state : String, page : Int) : Single<PullRequestsList> = githubRepository.getPullRequests(owner, repo, state, page)

}