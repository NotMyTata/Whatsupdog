package com.example.whatsupdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatsAdapter extends ArrayAdapter<Chat> {
    // View lookup cache
    private static class ViewHolder {
        int profile;
        TextView name;
        int status;
        TextView content;
        TextView time;
        TextView unread;
    }

    public ChatsAdapter(Context context, int resource, ArrayList<Chat> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        Chat data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewChats; // view lookup cache stored in tag
        if (ConvertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewChats = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_chats, parent, false);
            viewChats.profile = (int) ConvertView.findViewById(R.id.chat_profile_image);
            viewChats.name = (TextView) ConvertView.findViewById(R.id.chat_name);
            viewChats.content = (TextView) ConvertView.findViewById(R.id.chat_content);
            viewChats.time = (TextView) ConvertView.findViewById(R.id.chat_time);
            viewChats.unread = (TextView) ConvertView.findViewById(R.id.chat_unread);
            // Cache the viewHolder object inside the fresh view
            ConvertView.setTag(viewChats);

            Button btn = (Button) ConvertView.findViewById(R.id.btn);
            btn.setTag(position);
            btn.setOnClickListener(op);
        } else {
            viewChats = (ViewHolder) ConvertView.getTag();
        }
        viewChats.profile
        viewChats.name.setText(data.getName());

        viewChats.content.setText(data.getContent());
        viewChats.name.setText(data.getName());
        viewChats.content.setText(data.getContent());
        return ConvertView;
    }
}
