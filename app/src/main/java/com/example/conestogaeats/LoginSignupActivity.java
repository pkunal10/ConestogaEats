package com.example.conestogaeats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.conestogaeats.AdapterClasses.MyTablayoutAdapter;

import java.util.ArrayList;

public class LoginSignupActivity extends AppCompatActivity {

    TabLayout mTablayout;
    public ViewPager mViewPager;
    ArrayList<String> mTabList;
    MyTablayoutAdapter myTablayoutAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        mTablayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        mTabList = new ArrayList<>();
        sharedPreferences=getSharedPreferences("CE",MODE_PRIVATE);

        isLogedin();

        mTabList.add("Log in");
        mTabList.add("Sign up");

        myTablayoutAdapter = new MyTablayoutAdapter(getSupportFragmentManager(), mTabList, LoginSignupActivity.this);
        mViewPager.setAdapter(myTablayoutAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    private void isLogedin() {
        if(!sharedPreferences.getString("UserEmail","").equalsIgnoreCase(""))
        {
            startActivity(new Intent(LoginSignupActivity.this,HomeActivity.class));
        }
    }
}
