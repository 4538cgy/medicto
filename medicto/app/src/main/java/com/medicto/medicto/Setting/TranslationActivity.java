package com.medicto.medicto.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.medicto.medicto.ListView.ListViewAdapter;
import com.medicto.medicto.ListView.ListViewItem;
import com.medicto.medicto.R;

public class TranslationActivity extends AppCompatActivity {

    ListView listView;
    ListViewAdapter listViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        listViewAdapter = new ListViewAdapter();


        listView = (ListView)findViewById(R.id.activity_translation_listview);

        listView.setAdapter(listViewAdapter);


        listViewAdapter.addItem_contents("한국어","korea");
        listViewAdapter.addItem_contents("French","french");
        listViewAdapter.addItem_contents("ENGLISH","english");
        listViewAdapter.addItem_contents("汉语","chinese");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);
                String  language = null;
                SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);;
                SharedPreferences.Editor  editor = sharedPreferences.edit();;

                switch (item.getCode()){
                    case "korea":

                        language = item.getTitleStr();


                        break;
                    case "french":
                        language = item.getTitleStr();


                        break;
                    case "english":
                        language = item.getTitleStr();


                        break;
                    case "chinese":
                        language = item.getTitleStr();


                        break;
                }

                editor.putString("userData_Lang", language);
                editor.commit();

                finishAffinity();
            }
        });
    }
}