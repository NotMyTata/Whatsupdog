package com.example.whatsupdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatsAdapter extends ArrayAdapter<Chat> {

    private static class ViewHolder {
        ImageView profile;
        TextView name;
        ImageView status;
        TextView content;
        TextView time;
        TextView unread;
    }

    public ChatsAdapter(Context context, int resource, ArrayList<Chat> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        Chat data = getItem(position);

        ViewHolder viewChats;
        if (ConvertView == null) {
            viewChats = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.chats_row, parent, false);

            viewChats.profile = (ImageView) ConvertView.findViewById(R.id.chat_profile_image);
            viewChats.name = (TextView) ConvertView.findViewById(R.id.chat_name);
            viewChats.status = (ImageView) ConvertView.findViewById(R.id.chat_status);
            viewChats.content = (TextView) ConvertView.findViewById(R.id.chat_content);
            viewChats.time = (TextView) ConvertView.findViewById(R.id.chat_time);
            viewChats.unread = (TextView) ConvertView.findViewById(R.id.chat_unread_text);

            ConvertView.setTag(viewChats);
        } else {
            viewChats = (ViewHolder) ConvertView.getTag();
        }

        viewChats.profile.setImageResource(data.getProfile());
        viewChats.name.setText(data.getName());
        viewChats.status.setImageResource(data.getStatus());
        viewChats.content.setText(data.getContent());
        viewChats.name.setText(data.getName());
        viewChats.content.setText(data.getContent());

        return ConvertView;
    }
}
