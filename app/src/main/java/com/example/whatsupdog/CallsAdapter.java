package com.example.whatsupdog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class CallsAdapter extends ArrayAdapter<Call> {
    private static class ViewHolder {
        ImageView profile;
        TextView name;
        ImageView inout;
        TextView datetime;
    }

    public CallsAdapter(Context context, int resource, ArrayList<Call> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        Call data = getItem(position);

        ViewHolder viewCall;
        if (ConvertView == null) {
            viewCall = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.calls_row, parent, false);

            viewCall.profile = (ImageView) ConvertView.findViewById(R.id.call_profile_image);
            viewCall.name = (TextView) ConvertView.findViewById(R.id.call_name);
            viewCall.inout = (ImageView) ConvertView.findViewById(R.id.call_inout);
            viewCall.datetime = (TextView) ConvertView.findViewById(R.id.call_datetime);

            ConvertView.setTag(viewCall);
        } else {
            viewCall = (ViewHolder) ConvertView.getTag();
        }

        viewCall.profile.setImageResource(data.getProfile());
        viewCall.name.setText(data.getName());
        viewCall.inout.setImageResource(data.getInout());
        viewCall.datetime.setText(data.getDatetime());

        return ConvertView;
    }
}
