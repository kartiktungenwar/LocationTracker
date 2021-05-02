package com.android.locationtracker;

/**
 * Created by Personal on 01/10/2018.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private List<NotificationObject> list = new ArrayList<NotificationObject>();
    private NotificationListActivity context;
    public NotificationAdapter(NotificationListActivity context)
    {
        this.context = context;
    }


    public void setList(List<NotificationObject> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<NotificationObject> getList()
    {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = context.getLayoutInflater().inflate(R.layout.activity_notification_listitem, parent, false);

            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
            holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(list.get(position).push_title);
        holder.txtMessage.setText(list.get(position).push_message);
        holder.txtDate.setText(list.get(position).dateadded);

        return convertView;
    }

    static class ViewHolder
    {
        TextView txtTitle, txtMessage,txtDate;

    }

}

