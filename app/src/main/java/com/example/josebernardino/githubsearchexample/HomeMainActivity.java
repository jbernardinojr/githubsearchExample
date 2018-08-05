package com.example.josebernardino.githubsearchexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMainActivity extends AppCompatActivity {

    @BindView(R.id.edt_txt_search)
    EditText editTextSearch;

    @BindView(R.id.btn_search)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        ButterKnife.bind(this);
    }
}
