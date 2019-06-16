package joel.fernandes.github.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
import joel.fernandes.github.presentation.base.BaseActivity
import joel.fernandes.github.presentation.base.PageScrollListnerRV
import joel.fernandes.github.presentation.base.StateData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_input.view.*
import kotlinx.android.synthetic.main.include_repo_details_card.*
import org.koin.androidx.viewmodel.ext.viewModel


class MainActivity : BaseActivity(), AppBarLayout.OnOffsetChangedListener {

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

    private lateinit var adapter: PullRequestAdapter
    private lateinit var binding: ActivityMainBinding

    private lateinit var inputDialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        injectModules()
        initInputDialog()

        adapter = PullRequestAdapter()
        binding.rvPullRequests.adapter = adapter

        vm.repoDetails.observe(this, Observer { result ->
            when(result.status) {
                StateData.DataStatus.SUCCESS -> {
                    val repoDetails = result.data!!
                    this.repoTitle = repoDetails.fullName
                    binding.abl.addOnOffsetChangedListener(this)
                    Glide.with(imgRepoLogo)
                        .load(repoDetails.owner.avatar)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imgRepoLogo)
                    binding.repo = repoDetails
                    binding.lastUpdated = getTimeAgo(repoDetails.lastUpdated)

                }
                StateData.DataStatus.ERROR -> { }
            }
        })


        vm.pullRequestsList.observe(this, Observer { result ->
            when(result.status) {
                StateData.DataStatus.SUCCESS -> {
                    loadingNextPage = false
                    if(result.data!!.list.isNullOrEmpty()) {
                        lastPage = true
                    } else {
                        if(binding.pulls != null) {
                            val data = binding.pulls
                            (data!!.list as ArrayList<PullRequest>).addAll(data.list.size, result.data!!.list)
                            binding.pulls = data
                        } else {
                            binding.pulls = result.data!!
                        }
                    }
                }

                StateData.DataStatus.ERROR -> {
                    showToast(result.error)
                    inputDialog.show()
                }
            }
            hideLoading()
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

        bntChangeRepo.setOnClickListener { inputDialog.show() }

        inputDialog.show()
    }

    private fun initInputDialog() {
        inputDialog = AlertDialog.Builder(this).create()
        val dialogView = layoutInflater.inflate(R.layout.dialog_input, null)

        dialogView.btnGo.setOnClickListener {
            if(dialogView.etOwner.text.isNullOrEmpty()) {
                dialogView.etOwner.error = "Enter owner"
            } else if(dialogView.etRepo.text.isNullOrEmpty()) {
                dialogView.etRepo.error = "Enter repo"
            } else {
                showLoading()
                if(binding.pulls != null) (binding.pulls!!.list as ArrayList).clear()
                owner = dialogView.etOwner.text.toString()
                repo = dialogView.etRepo.text.toString()
                currentPage = 1
                vm.getRepoDetails(owner, repo)
                vm.getPullRequests(owner, repo, state, currentPage)
                inputDialog.dismiss()
            }
        }

        dialogView.btnCancel.setOnClickListener {
            inputDialog.dismiss()
        }

        inputDialog.setView(dialogView)
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
