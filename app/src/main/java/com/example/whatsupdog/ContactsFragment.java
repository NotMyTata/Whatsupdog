package com.example.whatsupdog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private ContactsAdapter contactsAdapter;
    private ArrayList<Contact> contactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        initializedComponent(groupFragmentView);
        return groupFragmentView;
    }

    private void initializedComponent(View v) {
        ListView lv = v.findViewById(R.id.contact_listview);
        contactList = new ArrayList<Contact>();
        contactsAdapter = new ContactsAdapter(getContext(), R.layout.fragment_contacts, contactList);

        /*
        int[] profile = {R.drawable.baseline_person_24_blue, R.drawable.baseline_person_24_red};
        String[] name = {"Test", "Arad"};
        String[] phone = {"0123456789", "1234567890"};
        addAllContacts(profile, name, phone);
        */

        lv.setAdapter(contactsAdapter);
    }

    private void addAllContacts(int[] profile, String[] name, String[] phone) {
        for (int i = 0; i < profile.length; i++) {
            addContact(profile[i], name[i], phone[i]);
        }
        contactsAdapter.notifyDataSetChanged();
    }

    private void addContact(int profile, String name, String phone) {
        contactList.add(new Contact(profile, name, phone));
    }
}