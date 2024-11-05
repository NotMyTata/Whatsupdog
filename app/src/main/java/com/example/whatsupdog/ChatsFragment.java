package com.example.whatsupdog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_chats, container, false);
        initialization(groupFragmentView);
        // Inflate the layout for this fragment
        return groupFragmentView;
    }

    private void initialization(View v) {
        ListView lv = v.findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.fragment_chats, arrayList);
        int[] profile = {R.drawable.baseline_person_24_blue, R.drawable.baseline_person_24_red};
        String[] name = {"Test", "Arad"};
        int[] status = {3, 2};
        String[] content = {"Hello World", "Bucharest"};
        String[] time = {"21:30", "07:00"};
        String[] unread = {"0", "1"};
        addAll(profile, name, status, content, time, unread);
    }

    private void addAll(int[] profile, String[] name, int[] status, String[] content, String[] time, String[] unread) {
        for (int i = 0; i < profile.length; i++) {
            arrayList.
        }
    }
}