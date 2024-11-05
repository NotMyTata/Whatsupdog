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
    private ChatsAdapter chatsAdapter;
    private ArrayList<Chat> chatList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_chats, container, false);
        initialization(groupFragmentView);
        return groupFragmentView;
    }

    private void initialization(View v) {
        ListView lv = v.findViewById(R.id.listview);
        chatList = new ArrayList<Chat>();
        chatsAdapter = new ChatsAdapter(getContext(), R.layout.fragment_chats, chatList);

        int[] profile = {R.drawable.baseline_person_24_blue, R.drawable.baseline_person_24_red};
        String[] name = {"Test", "Arad"};
        int[] status = {R.drawable.baseline_done_all_18_read, R.drawable.baseline_done_all_18_received};
        String[] content = {"Hello World", "Bucharest"};
        String[] time = {"21:30", "07:00"};
        String[] unread = {"0", "1"};
        addAll(profile, name, status, content, time, unread);

        lv.setAdapter(chatsAdapter);
    }

    private void addAll(int[] profile, String[] name, int[] status, String[] content, String[] time, String[] unread) {
        for (int i = 0; i < profile.length; i++) {
            addChat(profile[i], name[i], status[i], content[i], time[i], unread[i]);
        }
        chatsAdapter.notifyDataSetChanged();
    }

    private void addChat(int profile, String name, int status, String content, String time, String unread) {
        chatList.add(new Chat(profile, name, status, content, time, unread));
    }
}