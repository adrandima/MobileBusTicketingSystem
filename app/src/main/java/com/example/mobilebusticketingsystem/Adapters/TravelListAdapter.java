package com.example.mobilebusticketingsystem.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobilebusticketingsystem.Model.Travel;
import com.example.mobilebusticketingsystem.R;

import java.util.List;

public class TravelListAdapter  extends BaseAdapter {

    private Context mContext;
    private List<Travel> mTravelList;

    //Constructor

    public TravelListAdapter(Context mContext, List<Travel> mTravelList) {
        this.mContext = mContext;
        this.mTravelList = mTravelList;
    }

    @Override
    public int getCount() {
        return mTravelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTravelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.travel_history_list, null);
        TextView travelDate = (TextView)v.findViewById(R.id.travel_date);
        TextView travel_start_destination = (TextView)v.findViewById(R.id.travel_start_destination);
        TextView travelFee = (TextView)v.findViewById(R.id.travel_fee);
        //Set text for TextView


        travelDate.setText(mTravelList.get(position).getDate().substring(0, mTravelList.get(position).getDate().indexOf('T'))+"  "+mTravelList.get(position).getDate().substring( mTravelList.get(position).getDate().indexOf('T')+1,mTravelList.get(position).getDate().indexOf('T')+6));
        travel_start_destination.setText(String.valueOf(mTravelList.get(position).getStartingPoint())+":"+String.valueOf(mTravelList.get(position).getEndingPoint()));
        travelFee.setText(String.valueOf(mTravelList.get(position).getFare()));

        //Save product id to tag
        v.setTag(mTravelList.get(position).get_id());

        return v;
    }

}
