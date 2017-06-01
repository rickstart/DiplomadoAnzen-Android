package com.mobintum.feedplaces.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.models.Notification;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ricardo on 26/05/17.
 * www.mobintum.com
 */

public class NotificationRVAdapter extends RecyclerView.Adapter<NotificationRVAdapter.ViewHolder> {
    private List<Notification> notifications;

    public NotificationRVAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Notification notification = notifications.get(position);
        holder.txtTime.setText(notification.getTime()+" ");
        holder.txtBody.setText(notification.getBody());
        holder.txtTitleNotif.setText(notification.getTitle());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleNotif,txtTime,txtBody;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitleNotif = (TextView) itemView.findViewById(R.id.txtTitleNotif);
            txtBody = (TextView) itemView.findViewById(R.id.txtBody);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
        }
    }
}
