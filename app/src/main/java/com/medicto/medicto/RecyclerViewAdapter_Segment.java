package com.medicto.medicto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_Segment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mList = null;

    public RecyclerViewAdapter_Segment(ArrayList<String> mList){
        this.mList = mList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recycler_greenbutton_item,parent,false);

        return new GreenButton(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((GreenButton)holder).textView.setText(mList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public class GreenButton extends RecyclerView.ViewHolder{
        TextView textView;
                GreenButton(View view){
                    super(view);
                    textView = (TextView)view.findViewById(R.id.recycler_greenbutton_textview);
                }
    }
}
