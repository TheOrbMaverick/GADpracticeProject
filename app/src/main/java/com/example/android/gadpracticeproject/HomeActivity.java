package com.example.android.gadpracticeproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Toolbar myToolbar;
    private Button subButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        subButton = findViewById(R.id.submit_btn);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitIntent = new Intent(HomeActivity.this, SubmissionActivity.class);
                startActivity(submitIntent);
            }
        });



        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        //Adding Fragments

        adapter.AddFragment(new FragmentLearners(), "Leading Leaders");
        adapter.AddFragment(new FragmentSkillIQ(), "Skill IQ Leaders");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
