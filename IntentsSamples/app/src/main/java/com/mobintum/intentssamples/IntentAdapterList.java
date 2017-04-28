package com.mobintum.intentssamples;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ricardo on 24/04/17.
 * www.mobintum.com
 */

public class IntentAdapterList  extends ArrayAdapter<IntentModel>{
    private Context context;
    private ArrayList<IntentModel> objects;
    private int resource;
    private LayoutInflater inflater;

    public IntentAdapterList(Context context, int resource, ArrayList<IntentModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.from(context).inflate(resource,parent,false);
            holder = new ViewHolder();
            holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.txtActionName = (TextView) convertView.findViewById(R.id.txtActionName);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        IntentModel object = objects.get(position);
        holder.txtActionName.setText(object.getIntentName());
        holder.imgIcon.setImageDrawable(object.getIcon());
        return convertView;
    }

    @Nullable
    @Override
    public IntentModel getItem(int position) {
        return objects.get(position);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    class ViewHolder{
        ImageView imgIcon;
        TextView txtActionName;
    }
}
