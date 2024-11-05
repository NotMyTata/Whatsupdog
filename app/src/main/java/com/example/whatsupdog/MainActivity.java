package com.example.whatsupdog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavAccessorAdapter navAccessorAdapter;

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
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                AppCompatImageButton btn_new = findViewById(R.id.btn_new);
                switch(tab.getPosition()){
                    case 0:
                        btn_new.setImageResource(R.drawable.baseline_add_comment_24);
                        tab.setIcon(R.drawable.baseline_message_24);
                        tabLayout.getTabAt(1).setIcon(R.drawable.outline_group_24);
                        tabLayout.getTabAt(2).setIcon(R.drawable.outline_call_24);
                        break;
                    case 1:
                        btn_new.setImageResource(R.drawable.baseline_person_add_alt_1_24);
                        tabLayout.getTabAt(0).setIcon(R.drawable.outline_message_24);
                        tab.setIcon(R.drawable.baseline_group_24);
                        tabLayout.getTabAt(2).setIcon(R.drawable.outline_call_24);
                        break;
                    case 2:
                        btn_new.setVisibility(View.GONE);
                        tabLayout.getTabAt(0).setIcon(R.drawable.outline_message_24);
                        tabLayout.getTabAt(1).setIcon(R.drawable.outline_group_24);
                        tab.setIcon(R.drawable.baseline_call_24);
                        break;
                    default: break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0: break;
                    case 1: break;
                    case 2:
                        AppCompatImageButton btn_new = findViewById(R.id.btn_new);
                        btn_new.setVisibility(View.VISIBLE);
                        break;
                    default:
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        AppCompatImageButton btn_new = (AppCompatImageButton) findViewById(R.id.btn_new);
        btn_new.setOnClickListener(v -> {
            if(v.getId() == R.id.btn_new){
                openDialog(tabLayout.getSelectedTabPosition());
            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_message_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.outline_group_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.outline_call_24);
    }

    private void openDialog(int position){
        LayoutInflater li = LayoutInflater.from(this);
        View dialogView;
        switch(position){
            case 0: dialogView = li.inflate(R.layout.dialog_add_chat, null); break;
            case 1: dialogView = li.inflate(R.layout.dialog_add_contact, null); break;
            default: return;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
}