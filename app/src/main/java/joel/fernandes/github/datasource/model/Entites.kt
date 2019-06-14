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
                       val user : User)

data class User(val id: Long, val avatar : String, @field:Json(name = "login") val username : String)