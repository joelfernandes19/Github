package joel.fernandes.github.datasource.model

import com.squareup.moshi.Json

data class PullRequestsList(val list : List<PullRequest>)

data class PullRequest(val id : Long,
                       val state : String, val title : String, val body : String,
                       @field:Json(name = "number") val pullReqNumber : Long,
                       @field:Json(name = "url") val pullRepoApi : String,
                       @field:Json(name = "html_url") val htmlUrl : String,
                       @field:Json(name = "created_at") val createdOn : String,
                       @field:Json(name = "updated_at") val updatedOn : String,
                       @field:Json(name = "closed_at") val closedOn : String,
                       @field:Json(name = "merged_at") val mergedOn : String,
                       val user : User, val head : Head, val base : Base, val repo : Repo)

data class User(val id: Long, @field:Json(name = "avatar_url") val avatar : String, @field:Json(name = "login") val username : String)
data class Head(val label : String, val ref : String)
data class Base(val user : User, val label : String, val ref : String)
data class Repo(val id: Long, val name : String, @field:Json(name = "avatar_url") val fullName : String, val private : Boolean, val owner : User)


data class RepoDetails(val id: Long, val size : Long, val language : String,
                       val description: String,
                       @field:Json(name = "full_name") val fullName : String,
                       @field:Json(name = "private") val isPrivate : Boolean,
                       @field:Json(name = "updated_at") val lastUpdated : String,
                       @field:Json(name = "stargazers_count") val starsCount : Long,
                       @field:Json(name = "watchers_count") val watchersCount : Long,
                       @field:Json(name = "forks_count") val forksCount : Long,
                       @field:Json(name = "open_issues_count") val openIssuesCount : Long,
                       @field:Json(name = "subscribers_count") val subscribersCount : Long,
                       val owner: User, val organization : User, val license : License)

data class License(val key : String, val name : String, val url : String)