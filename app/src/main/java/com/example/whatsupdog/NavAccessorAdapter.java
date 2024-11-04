package com.example.whatsupdog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NavAccessorAdapter extends FragmentPagerAdapter {
    public NavAccessorAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new ChatsFragment();
            case 1: return new ContactsFragment();
            case 2: return new CallsFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return "Chats";
            case 1: return "Contacts";
            case 2: return "Calls";
            default: return null;
        }
    }
}
