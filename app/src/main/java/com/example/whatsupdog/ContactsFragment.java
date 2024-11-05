package com.example.whatsupdog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class ContactsFragment extends Fragment {
    private static ContactsAdapter contactsAdapter;
    private static ArrayList<Contact> contactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        initializedComponent(groupFragmentView);
        return groupFragmentView;
    }

    private void initializedComponent(View v) {
        ListView lv = v.findViewById(R.id.contact_listview);
        contactList = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(getContext(), R.layout.fragment_contacts, contactList);

        addContacts(Whatsupdog_db.getInstance(getContext()).contactDao().getAll());

        SearchView searchView = (SearchView) v.findViewById(R.id.search_contact);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()) {
                    contactList.clear();
                    addContacts(Whatsupdog_db.getInstance(getContext()).contactDao().getAll());
                } else {
                    filterList(s);
                }
                return false;
            }
        });

        /*
        int[] profile = {R.drawable.baseline_person_24_blue, R.drawable.baseline_   person_24_red};
        String[] name = {"Test", "Arad"};
        String[] phone = {"0123456789", "1234567890"};
        addAllContacts(profile, name, phone);
        */

        lv.setAdapter(contactsAdapter);
    }

    public static void addContacts(List<Contact> contacts){
        contactList.addAll(contacts);
        contactsAdapter.notifyDataSetChanged();
    }

    public static void addContact(Contact contact){
        contactList.add(contact);
        contactsAdapter.notifyDataSetChanged();
    }

    private void filterList(String text){
        List<Contact> filteredList = new ArrayList<>();
        contactList.clear();
        addContacts(Whatsupdog_db.getInstance(getContext()).contactDao().getAll());
        for(Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(contact);
            }
        }
        contactList.clear();
        if (!filteredList.isEmpty()) {
            addContacts(filteredList);
        }
        contactsAdapter.notifyDataSetChanged();
    }


    /*
    private void addAllContacts(int[] profile, String[] name, String[] phone) {
        for (int i = 0; i < profile.length; i++) {
            addContact(profile[i], name[i], phone[i]);
        }
        contactsAdapter.notifyDataSetChanged();
    }

    private void addContact(int profile, String name, String phone) {
        contactList.add(new Contact(profile, name, phone));
    }
    */
}