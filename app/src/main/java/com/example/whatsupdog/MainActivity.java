package com.example.whatsupdog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.intellij.lang.annotations.RegExp;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NavAccessorAdapter navAccessorAdapter;

    private Whatsupdog_db db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Whatsupdog_db.getInstance(getApplicationContext());
        contactDao = db.contactDao();

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
                        switch(position){
                            case 0: break;
                            case 1:
                                String name = ((EditText)dialogView.findViewById(R.id.dialog_add_contact_name_input)).getText().toString();
                                String phone = ((EditText)dialogView.findViewById(R.id.dialog_add_contact_phonenum_input)).getText().toString();

                                if (name.isEmpty() || phone.isEmpty() || phone.contains(" ")){
                                    Toast.makeText(MainActivity.this, "Incorrect Value", Toast.LENGTH_LONG).show();
                                } else {
                                    contactDao.insertContact(new Contact(R.drawable.baseline_person_24_blue, name, phone));
                                    Toast.makeText(MainActivity.this, "New Contact Added", Toast.LENGTH_SHORT).show();
                                    ContactsFragment.addContact(contactDao.getLastContact());
                                }
                                break;
                            default:
                        }
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