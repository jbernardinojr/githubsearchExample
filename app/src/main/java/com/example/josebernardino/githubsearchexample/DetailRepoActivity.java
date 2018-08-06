package com.example.josebernardino.githubsearchexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.josebernardino.githubsearchexample.model.Owner;
import com.example.josebernardino.githubsearchexample.model.Repos;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.josebernardino.githubsearchexample.HomeMainActivity.ACTION_SHOW_REPOS;
import static com.example.josebernardino.githubsearchexample.HomeMainActivity.OWNER_OBJECT;

public class DetailRepoActivity extends AppCompatActivity {

    private static final String TAG = DetailRepoActivity.class.getSimpleName();
    List<Repos> mRepos;

    @BindView(R.id.recycler_view_repos)
    RecyclerView mRecyclerView;

    @BindView(R.id.iv_owner_avatar)
    ImageView imageViewAvatar;

    @BindView(R.id.tv_owner_name)
    TextView mTextViewOwnerName;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_repo);
        ButterKnife.bind(this);

        //mToolbar.setTitle(getString(R.string.app_name));
       // mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        Intent intent = getIntent();
        if (intent != null) {
            mRepos = intent.getParcelableArrayListExtra(ACTION_SHOW_REPOS);
            Owner owner = intent.getParcelableExtra(OWNER_OBJECT);
            if (mRepos != null) {
                Log.d(TAG, "onCreate: not null mRepos");
                if (owner != null) {
                    Glide.with(this).load(owner.getAvatarUrl()).into(imageViewAvatar);
                    mTextViewOwnerName.setText(owner.getLogin());
                }
                RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setAdapter(new ReposListAdapter(mRepos, this));
                mRecyclerView.setLayoutManager(layout);
            }
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeMainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
