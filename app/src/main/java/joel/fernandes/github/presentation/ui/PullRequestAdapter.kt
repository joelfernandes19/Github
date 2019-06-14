package joel.fernandes.github.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joel.fernandes.github.R
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.presentation.databinding.BindableAdapter
import kotlinx.android.synthetic.main.item_pull_request.view.*
import java.lang.StringBuilder

class PullRequestAdapter : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>(),
    BindableAdapter<PullRequest> {

    var pullRequests = emptyList<PullRequest>()

    override fun setData(pullRequests: List<PullRequest>) {
        this.pullRequests = pullRequests
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_pull_request,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = pullRequests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pullRequests[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {
            itemView.text.text = StringBuilder()
                .append(pullRequest.title)
                .append("\n\n")
                .append(pullRequest.body)
                .append("\n\n")
                .append(pullRequest.createdOn)
                .toString()
        }
    }

}