package com.example.mobilebusticketingsystem.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobilebusticketingsystem.Model.Bus;
import com.example.mobilebusticketingsystem.R;

import java.util.List;

public class BusListAdapter  extends BaseAdapter {

    private Context mContext;
    private List<Bus> mBusList;

    //Constructor

    public BusListAdapter(Context mContext, List<Bus> mBusList) {
        this.mContext = mContext;
        this.mBusList = mBusList;
    }

    @Override
    public int getCount() {
        return mBusList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBusList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.bus_details_list, null);
        TextView busNo = (TextView)v.findViewById(R.id.bus_number);
        TextView price = (TextView)v.findViewById(R.id.bus_to_info);
        TextView startTime = (TextView)v.findViewById(R.id.bus_start_time);
        TextView endTime = (TextView)v.findViewById(R.id.bus_end_time);
        //Set text for TextView

        busNo.setText(mBusList.get(position).getBusNo());
        price.setText(String.valueOf(mBusList.get(position).getPrice()));
        startTime.setText(String.valueOf(mBusList.get(position).getStartTime()));
        endTime.setText(String.valueOf(mBusList.get(position).getEndTime()));

        //Save product id to tag
        v.setTag(mBusList.get(position).getBusNo());

        return v;
    }

}

