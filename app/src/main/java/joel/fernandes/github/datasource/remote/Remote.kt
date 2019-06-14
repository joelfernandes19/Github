package joel.fernandes.github.datasource.remote

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {

    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo : String,
        @Query("state") state: String
    ): Single<List<PullRequest>>

}