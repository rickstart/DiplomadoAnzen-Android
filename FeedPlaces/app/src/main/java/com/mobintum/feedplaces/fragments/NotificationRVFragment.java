package com.mobintum.feedplaces.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.adapters.NotificationRVAdapter;
import com.mobintum.feedplaces.models.Notification;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationRVFragment extends Fragment {
    RecyclerView rvNotifications;
    NotificationRVAdapter adapter;


    public NotificationRVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_rv, container, false);
        rvNotifications = (RecyclerView) view.findViewById(R.id.rvNotifications);
        rvNotifications.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new NotificationRVAdapter(Notification.getNotifications(getContext()));
        rvNotifications.setAdapter(adapter);
        return view;
    }

}
