package joel.fernandes.github.presentation.ui

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import joel.fernandes.github.R
import joel.fernandes.github.datasource.model.PullRequest
import joel.fernandes.github.getTimeAgo
import joel.fernandes.github.presentation.databinding.BindableAdapter
import kotlinx.android.synthetic.main.item_pull_request.view.*


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
            inflater.inflate(R.layout.item_pull_request, parent, false)
        )
    }

    override fun getItemCount() = pullRequests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pullRequests[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest) {
            itemView.tvPullTitle.text = pullRequest.title
            itemView.tvPullDescription.text = SpannableStringBuilder()
                .bold { append("#${pullRequest.pullReqNumber}") }
                .append(" ${pullRequest.user.username} wants to merge ")
                .bold { append(pullRequest.head.ref) }
                .append(" to ")
                .bold { append(pullRequest.base.ref) }
            itemView.tvTimestamp.text = getTimeAgo(pullRequest.createdOn)
            Glide.with(itemView.imgAvatar.context)
                .load(pullRequest.user.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.imgAvatar)
        }
    }

}