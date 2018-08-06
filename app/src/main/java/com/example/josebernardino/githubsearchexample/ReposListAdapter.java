package com.example.josebernardino.githubsearchexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.josebernardino.githubsearchexample.model.Repos;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.ViewHolder> {
    private final Context mContext;
    private List<Repos> mListRepos;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.repos_title)
        TextView mTitleTextView;

        @BindView(R.id.repos_subtitle)
        TextView mSubtitleTextView;

        private ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReposListAdapter(List<Repos> repos, Context context) {
        mListRepos = repos;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ReposListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(mContext)
                    .inflate(R.layout.repo_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitleTextView.setText(mListRepos.get(position).getName());
        holder.mSubtitleTextView.setText(mListRepos.get(position).getLanguage());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mListRepos.size();
    }
}