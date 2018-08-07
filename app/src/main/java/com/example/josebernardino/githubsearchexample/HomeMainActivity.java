package com.example.josebernardino.githubsearchexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.josebernardino.githubsearchexample.api.GitHub;
import com.example.josebernardino.githubsearchexample.model.Owner;
import com.example.josebernardino.githubsearchexample.model.Repos;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.josebernardino.githubsearchexample.api.GitHub.API_BASE_URL;

public class HomeMainActivity extends AppCompatActivity {

    private static final String TAG = HomeMainActivity.class.getSimpleName();

    public static final String ACTION_SHOW_REPOS = "listRepo";
    public static final String OWNER_OBJECT = "owner";

    ProgressDialog mDialog;

    @BindView(R.id.edt_txt_search)
    EditText editTextSearch;

    @BindView(R.id.btn_search)
    Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        ButterKnife.bind(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog = new ProgressDialog(HomeMainActivity.this);
                Window window = mDialog.getWindow();
                if(window!=null) {
                    window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }
                mDialog.setIndeterminate(true);
                mDialog.setCancelable(true);
                mDialog.show();
                mDialog.setContentView(R.layout.custom_progress_bar);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create an instance of our GitHub API interface.
                GitHub gitHub = retrofit.create(GitHub.class);
                Call<List<Repos>> call = gitHub.reposData(editTextSearch.getText().toString());

                call.enqueue(new Callback<List<Repos>>() {
                    @Override
                    public void onResponse(final Call<List<Repos>> call, final Response<List<Repos>> response) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            List<Repos> repos = response.body();
                            if (repos != null) {
                                Owner owner = repos.get(0).getOwner();
                                mDialog.cancel();
                                Intent intent = new Intent(getApplicationContext(), DetailRepoActivity.class);
                                intent.putExtra(OWNER_OBJECT, owner);
                                intent.putParcelableArrayListExtra(ACTION_SHOW_REPOS, (ArrayList<? extends Parcelable>) repos);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Repos>> call, Throwable t) {
                        // Log error here since request failed
                    }
                });

            }
        });
    }
}
