package com.example.whatsupdog;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactsAdapter extends ArrayAdapter<Contact> {
    private static class ViewHolder {
        ImageView profile;
        TextView name;
    }

    public ContactsAdapter(Context context, int resource, ArrayList<Contact> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView, ViewGroup parent) {
        Contact data = getItem(position);

        ViewHolder viewContacts;
        if (ConvertView == null) {
            viewContacts = new ViewHolder();
            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_row, parent, false);

            viewContacts.profile = (ImageView) ConvertView.findViewById(R.id.contact_profile_image);
            viewContacts.name = (TextView) ConvertView.findViewById(R.id.contact_name);

            ConvertView.setTag(viewContacts);
        } else {
            viewContacts = (ViewHolder) ConvertView.getTag();
        }

        viewContacts.profile.setImageResource(data.getProfile());
        viewContacts.name.setText(data.getName());

        return ConvertView;
    }
}
