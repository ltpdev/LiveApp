package com.example.gdcp.liveapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus- on 2017/11/25.
 */

public class LiveListAdater extends BaseAdapter {
    private Context context;
    private List<Live>liveList;

    public LiveListAdater(Context context,List<Live>liveList){
        this.context=context;
        this.liveList=liveList;
    }
    @Override
    public int getCount() {
        return liveList.size();
    }

    @Override
    public Object getItem(int position) {
        return liveList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_live,parent,false);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        final Live live=liveList.get(position);
        viewHolder.textView.setText(live.getTitle());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,LiveActivity.class);
                intent.putExtra("live",live);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
