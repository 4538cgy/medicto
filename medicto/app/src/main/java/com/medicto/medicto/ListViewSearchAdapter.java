package com.medicto.medicto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.model.PharmacyModel;

import java.util.ArrayList;

public class ListViewSearchAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PharmacyModel> list;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;


    public ListViewSearchAdapter(ArrayList<PharmacyModel> list , Context context){
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.listview_search_item,null);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.listview_search_item_textview_pharname);
            viewHolder.engName = (TextView)convertView.findViewById(R.id.listview_search_item_textview_engname);
            viewHolder.address = (TextView)convertView.findViewById(R.id.listview_search_item_textview_address);
            viewHolder.number = (TextView)convertView.findViewById(R.id.listview_search_item_textview_phonenumber);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.name.setText( list.get(position).getPharName());
        viewHolder.engName.setText(list.get(position).getEngName());
        viewHolder.address.setText(list.get(position).getPharAddress());
        viewHolder.number.setText(list.get(position).getPharPhoneNumber());

        return convertView;
    }


    class ViewHolder{
        public TextView name;
        public TextView engName;
        public TextView address;
        public TextView number;
    }
}
