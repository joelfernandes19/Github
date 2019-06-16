package joel.fernandes.github.datasource.remote

import io.reactivex.Single
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.datasource.model.RepoDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {


    @GET("/repos/{owner}/{repo}")
    fun getRepoDetails(@Path("owner") owner: String,
                       @Path("repo") repo : String) : Single<RepoDetails>

    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo : String,
        @Query("state") state: String,
        @Query("page") page : Int
    ): Single<List<PullRequest>>

    @GET("/repos/{owner}/{repo}/pulls/{pull_number}")
    fun getPullDetails(@Path("owner") owner: String,
                       @Path("repo") repo : String,
                       @Path("pull_number") pullNumber : String)
}