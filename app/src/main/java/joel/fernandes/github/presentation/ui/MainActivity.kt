package joel.fernandes.github.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import joel.fernandes.github.R
import joel.fernandes.github.databinding.ActivityMainBinding
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.getTimeAgo
import joel.fernandes.github.injectModules
import joel.fernandes.github.presentation.PageScrollListnerRV
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_repo_details_card.*
import kotlinx.android.synthetic.main.input_dialog.view.*
import org.koin.androidx.viewmodel.ext.viewModel


class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val vm : GithubViewModel by viewModel()

    private val PAGE_START = 1
    private var totalPagesCount = 1
    private var lastPage = false
    private var loadingNextPage = false
    private var currentPage = PAGE_START
    private var repoTitle = ""

    private var owner = "square"
    private var repo = "okhttp"
    private val state = "open"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        injectModules()

        val adapter = PullRequestAdapter()
        binding.rvPullRequests.adapter = adapter

        vm.repoDetails.observe(this, Observer { repoDetails ->
            this.repoTitle = repoDetails.fullName
            binding.abl.addOnOffsetChangedListener(this)
            Glide.with(imgRepoLogo)
                .load(repoDetails.owner.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(imgRepoLogo)
            binding.repo = repoDetails
            binding.lastUpdated = getTimeAgo(repoDetails.lastUpdated)
        })


        vm.pullRequestsList.observe(this, Observer {
            loadingNextPage = false
            if(it.list.isNullOrEmpty()) {
                lastPage = true
            } else {
                if(binding.pulls != null) {
                    val data = binding.pulls
                    (data!!.list as ArrayList<PullRequest>).addAll(data.list.size, it.list)
                    binding.pulls = data
                } else {
                    binding.pulls = it
                }
            }
        })


        binding.rvPullRequests.addOnScrollListener(object : PageScrollListnerRV(binding.rvPullRequests.layoutManager as LinearLayoutManager) {

            override fun loadMoreItems() {
                loadingNextPage = true
                currentPage += 1
                vm.getPullRequests(owner, repo, state, currentPage)
            }

            override fun getTotalPageCount(): Int {
                return totalPagesCount
            }

            override fun isLastPage(): Boolean {
                return lastPage
            }

            override fun isLoading(): Boolean {
                return loadingNextPage
            }
        })

        showDialog()
    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.input_dialog, null)

        dialogView.btnGo.setOnClickListener {
            if(dialogView.etOwner.text.isNullOrEmpty()) {
                dialogView.etOwner.error = "Enter owner"
            } else if(dialogView.etRepo.text.isNullOrEmpty()) {
                dialogView.etRepo.error = "Enter repo"
            } else {
                owner = dialogView.etOwner.text.toString()
                repo = dialogView.etRepo.text.toString()
                currentPage = 1
                vm.getRepoDetails(owner, repo)
                vm.getPullRequests(owner, repo, state, currentPage)
                dialogBuilder.dismiss()
            }
        }

        dialogView.btnCancel.setOnClickListener {
            dialogBuilder.dismiss()
        }

        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            toolbar.title = repoTitle
        } else {
            toolbar.title = ""
            ctl.title = ""
        }
    }
}
