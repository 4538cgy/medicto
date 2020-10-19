package com.medicto.medicto.ListView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.medicto.medicto.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemArrayList =new ArrayList<ListViewItem>();

    private static final int ITEM_VIEW_TYPE_TITLE = 0;
    private static final int ITEM_VIEW_TYPE_CONTENTS = 1;
    private static final int ITEM_VIEW_TYPE_DRAWBLECONTENTS = 2;
    private static final int ITEM_VIEW_TYPE_MAX = 3;


    public ListViewAdapter(){

    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_MAX;
    }

    @Override
    public int getItemViewType(int position) {
        return listViewItemArrayList.get(position).getType();
    }

    @Override
    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final Context context = parent.getContext();
        int viewType = getItemViewType(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            ListViewItem listViewItem = listViewItemArrayList.get(position);

            switch (viewType){

                case ITEM_VIEW_TYPE_TITLE:
                    convertView = inflater.inflate(R.layout.activity_setting_listview_header_item,parent,false);

                    TextView textView = (TextView)convertView.findViewById(R.id.activity_setting_listview_header_item_textview_title);

                    textView.setText(listViewItem.getTitleStr());

                    break;
                case ITEM_VIEW_TYPE_CONTENTS:
                    convertView = inflater.inflate(R.layout.activity_setting_listview_item,parent,false);

                    TextView textView2 = (TextView)convertView.findViewById(R.id.activity_setting_listview_item_textview_title);

                    textView2.setText(listViewItem.getTitleStr());
                    break;

                case ITEM_VIEW_TYPE_DRAWBLECONTENTS:
                    convertView = inflater.inflate(R.layout.activity_setting_listview_drawble_item,parent,false);

                    ImageView imageView = (ImageView)convertView.findViewById(R.id.activity_setting_listview_drawble_item_imageview);
                    imageView.setImageDrawable(listViewItem.getDrawable());

                    TextView textView3 = (TextView)convertView.findViewById(R.id.activity_setting_listview_drawble_item_textview);
                    textView3.setText(listViewItem.getTitleStr());

                    break;
            }

        }








        return convertView;
    }

    public void addItem_contents(Drawable drawable , String title , String code){
        ListViewItem item = new ListViewItem();
        item.setDrawable(drawable);
        item.setTitleStr(title);
        item.setCode(code);

        item.setType(ITEM_VIEW_TYPE_DRAWBLECONTENTS);

        listViewItemArrayList.add(item);
    }

    public void addItem_contents(String title,String code){
        ListViewItem item = new ListViewItem();

        item.setTitleStr(title);
        item.setCode(code);
        item.setType(ITEM_VIEW_TYPE_CONTENTS);

        listViewItemArrayList.add(item);
    }

    public void addItem_title(String title,String code){
        ListViewItem item = new ListViewItem();

        item.setTitleStr(title);
        item.setCode(code);
        item.setType(ITEM_VIEW_TYPE_TITLE);

        listViewItemArrayList.add(item);
    }
}
