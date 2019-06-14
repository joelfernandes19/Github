package joel.fernandes.github

import joel.fernandes.github.data.datasource.GithubDataSourceRemote
import joel.fernandes.github.data.repository.GithubRepositoryImpl
import joel.fernandes.github.datasource.remote.GithubAPI
import joel.fernandes.github.datasource.remote.GithubDataSourceRemoteImpl
import joel.fernandes.github.domain.repository.GithubRepository
import joel.fernandes.github.domain.usecases.UCGithubRepos
import joel.fernandes.github.presentation.ui.GithubViewModel
import joel.fernandes.network.createNetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        githubUseCaseModule,
        githubRepoModule,
        githubDSModule,
        networkModule,
        cacheModule
    )
}

val viewModelModule: Module = module {
    viewModel { GithubViewModel(githubUseCases = get()) }
}

val githubUseCaseModule: Module = module {
    factory { UCGithubRepos(githubRepository = get()) }
}

val githubRepoModule: Module = module {
    single { GithubRepositoryImpl(remote = get()) as GithubRepository }
}

val githubDSModule: Module = module {
    single { GithubDataSourceRemoteImpl(api = githubApi) as GithubDataSourceRemote }
}

val networkModule: Module = module {
    single { githubApi }
}

val cacheModule: Module = module {

}


private const val GITHUB_BASE_URL = "https://api.github.com"
private val retrofitGithub: Retrofit = createNetworkClient(GITHUB_BASE_URL, BuildConfig.DEBUG)
private val githubApi: GithubAPI = retrofitGithub.create(GithubAPI::class.java)