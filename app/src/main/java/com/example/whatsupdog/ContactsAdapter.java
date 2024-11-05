package com.example.whatsupdog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.room.Room;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    private static class ViewHolder {
        ImageView profile;
        TextView name;
        AppCompatImageButton btn_edit;
        AppCompatImageButton btn_delete;
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
            viewContacts.btn_edit = (AppCompatImageButton) ConvertView.findViewById(R.id.contact_edit);
            viewContacts.btn_delete = (AppCompatImageButton) ConvertView.findViewById(R.id.contact_delete);

            ConvertView.setTag(viewContacts);
        } else {
            viewContacts = (ViewHolder) ConvertView.getTag();
        }

        viewContacts.profile.setImageResource(data.getProfile());
        viewContacts.name.setText(data.getName());
        viewContacts.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Whatsupdog_db db = Whatsupdog_db.getInstance(getContext());
                String name = data.getName();
                String phone = data.getPhone();

                LayoutInflater li = LayoutInflater.from(getContext());
                View dialogView = li.inflate(R.layout.dialog_add_contact, null);;

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(dialogView);

                EditText et_name = (EditText)dialogView.findViewById(R.id.dialog_add_contact_name_input);
                EditText et_phone = (EditText)dialogView.findViewById(R.id.dialog_add_contact_phonenum_input);
                et_name.setText(name);
                et_phone.setText(phone);

                builder
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                String name = et_name.getText().toString();
                                String phone = et_phone.getText().toString();

                                if (name.isEmpty() || phone.isEmpty() || phone.contains(" ")){
                                    Toast.makeText(getContext(), "Incorrect Value", Toast.LENGTH_LONG).show();
                                } else {
                                    db.contactDao().updateContactFromId(data.getId(), name, phone);
                                    data.setName(name);
                                    data.setPhone(phone);
                                    Toast.makeText(getContext(), "Contact Edited", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
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
        });
        viewContacts.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Whatsupdog_db db = Whatsupdog_db.getInstance(getContext());
                db.contactDao().deleteContact(data);
                remove(data);
                notifyDataSetChanged();
            }
        });

        return ConvertView;
    }
}
