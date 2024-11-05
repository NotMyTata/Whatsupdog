package com.example.whatsupdog;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavAccessorAdapter navAccessorAdapter;

    private SQLiteDatabase db;
    private SQLiteOpenHelper dbopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        navAccessorAdapter = new NavAccessorAdapter(getSupportFragmentManager());
        viewPager.setAdapter(navAccessorAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_message_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_group_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_call_24);
    }
}