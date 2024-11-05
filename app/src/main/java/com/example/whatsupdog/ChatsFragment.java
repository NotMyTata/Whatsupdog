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
        initializedComponent(groupFragmentView);
        return groupFragmentView;
    }

    private void initializedComponent(View v) {
        ListView lv = v.findViewById(R.id.chat_listview);
        chatList = new ArrayList<Chat>();
        chatsAdapter = new ChatsAdapter(getContext(), R.layout.fragment_chats, chatList);

        // TODO - Remove this and replace it with a working database insert and fetch
        int bp = R.drawable.baseline_person_24_blue;
        int rp = R.drawable.baseline_person_24_red;
        int s = R.drawable.baseline_check_18_sent;
        int rc = R.drawable.baseline_done_all_18_received;
        int rd = R.drawable.baseline_done_all_18_read;

        int[] profile = {bp, rp};
        String[] name = {"Test", "Arad"};
        int[] status = {rd, rc};
        String[] content = {"Hello World", "Bucharest"};
        String[] time = {"21:30", "07:00"};
        String[] unread = {"0", "1"};
        addAllChats(profile, name, status, content, time, unread);

        lv.setAdapter(chatsAdapter);
    }

    private void addAllChats(int[] profile, String[] name, int[] status, String[] content, String[] time, String[] unread) {
        for (int i = 0; i < profile.length; i++) {
            addChat(profile[i], name[i], status[i], content[i], time[i], unread[i]);
        }
        chatsAdapter.notifyDataSetChanged();
    }

    private void addChat(int profile, String name, int status, String content, String time, String unread) {
        chatList.add(new Chat(profile, name, status, content, time, unread));
    }
}