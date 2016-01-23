package com.google.devplat.lmoroney.maps3_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PartyAdapter extends ArrayAdapter<Party> {

    Context context;
    int layoutResourceId;
    Party data[] = null;

    public PartyAdapter(Context context, int layoutResourceId, Party[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PartyHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PartyHolder();
            holder.venue = (TextView)row.findViewById(R.id.venue_time_textview);
            holder.name = (TextView)row.findViewById(R.id.name_textview);

            row.setTag(holder);
        }
        else
        {
            holder = (PartyHolder)row.getTag();
        }

        Party party = data[position];
        holder.name.setText(party.name);
        holder.venue.setText(party.venue);

        return row;
    }

    static class PartyHolder
    {
        TextView name;
        TextView venue;
    }
}