package joel.fernandes.github

import joel.fernandes.github.datasource.remote.GithubAPI
import joel.fernandes.network.createNetworkClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        networkModule,
        cacheModule
    )
}

val networkModule: Module = module {
    single { githubApi }
}

val cacheModule: Module = module {

}


private const val GITHUB_BASE_URL = "https://api.github.com"
private val retrofitGithub: Retrofit = createNetworkClient(GITHUB_BASE_URL, BuildConfig.DEBUG)
private val githubApi: GithubAPI = retrofitGithub.create(GithubAPI::class.java)