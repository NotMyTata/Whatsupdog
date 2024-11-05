package com.example.whatsupdog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class CallsFragment extends Fragment {
    private CallsAdapter callsAdapter;
    private ArrayList<Call> callList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View groupFragmentView = inflater.inflate(R.layout.fragment_calls, container, false);
        initializedComponent(groupFragmentView);
        return groupFragmentView;
    }

    private void initializedComponent(View v) {
        ListView lv = v.findViewById(R.id.call_listview);
        callList = new ArrayList<Call>();
        callsAdapter = new CallsAdapter(getContext(), R.layout.fragment_calls, callList);

        // TODO - Remove this and replace it with a working database insert and fetch
        int[] profile = {R.drawable.baseline_person_24_blue, R.drawable.baseline_person_24_red};
        String[] name = {"Test", "Arad"};
        int[] inout = {R.drawable.baseline_call_made_success_18, R.drawable.baseline_call_received_failed_18};
        String[] datetime = {"Today, 21:30", "Yesterday, 07:00"};
        addAllCall(profile, name, inout, datetime);

        lv.setAdapter(callsAdapter);
    }

    private void addAllCall(int[] profile, String[] name, int[] inout, String[] datetime) {
        for (int i = 0; i < profile.length; i++) {
            addCall(profile[i], name[i], inout[i], datetime[i]);
        }
        callsAdapter.notifyDataSetChanged();
    }

    private void addCall(int profile, String name, int inout, String datetime) {
        callList.add(new Call(profile, name, inout, datetime));
    }
}