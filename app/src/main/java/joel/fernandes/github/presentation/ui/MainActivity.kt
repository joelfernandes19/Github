package joel.fernandes.github.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import joel.fernandes.github.R
import joel.fernandes.github.databinding.ActivityMainBinding
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.injectModules
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : AppCompatActivity() {

    private val vm : GithubViewModel by viewModel()
    private val pullRequestsList = ArrayList<PullRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        injectModules()

        val adapter = PullRequestAdapter()
        binding.rvPullRequests.adapter = adapter

        vm.pullRequestsList.observe(this, Observer {
            pullRequestsList.clear()
            pullRequestsList.addAll(it.list)
            binding.data = it
        })

        vm.getPullRequests("square", "okhttp", "open")

    }
}
