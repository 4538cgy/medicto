package com.medicto.medicto.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.medicto.medicto.AgreementActivity;
import com.medicto.medicto.Agreement_2;
import com.medicto.medicto.ListView.ListViewAdapter;
import com.medicto.medicto.ListView.ListViewItem;
import com.medicto.medicto.R;

public class SettingActivity extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



        adapter = new ListViewAdapter();



        listView = (ListView)findViewById(R.id.activity_setting_listview);

        listView.setAdapter(adapter);



        SharedPreferences sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);

        switch ( sharedPreferences.getString("userData_Lang",null)){
            case "ENGLISH":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"MY ACCOUNT",  "ACCOUNT");
                //adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_privacy),"PRIVACY INFO",  "PRIVACY");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"TERMS OF USE",  "TERMS");
                //adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_patch),"PATCH NOTE",  "PATCH");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"CONTACT US",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"LANGUAGE SETTING","TRAN");
                break;
            case "한국어":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"계정",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"이용 약관",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"문의",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"언어 설정","TRAN");
                break;
            case "French":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"Mon compte",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"Conditions d'utilisation",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"Contactez-nous",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"Cadre linguistique","TRAN");
                break;
            case "汉语":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"我的帐户",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"使用条款",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"联络我们",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"翻译","TRAN");
                break;

        }




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem)parent.getItemAtPosition(position);

                String titleStr = item.getTitleStr();
                Intent intent;
                switch (item.getCode()){
                    case "PATCH":
                        intent = new Intent(getApplicationContext(), PatchNoteActivity.class);
                        startActivity(intent);
                        break;
                    case "TERMS":
                        intent = new Intent(getApplicationContext(), Agreement_2.class);
                        startActivity(intent);
                        break;

                    case "ACCOUNT":
                        intent = new Intent(getApplicationContext(), Setting_AccountActivity.class);
                        startActivity(intent);
                        break;
                    case "TRAN":
                        intent = new Intent(getApplicationContext(),TranslationActivity.class);
                        startActivity(intent);
                        break;
                    case "CONTACT":
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        String[] address = {"10loco.official@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL,address);
                        intent.putExtra(intent.EXTRA_SUBJECT,"Contact Us");
                        intent.putExtra(Intent.EXTRA_TEXT,"Thank you for contacting us.\n" +
                                "If you send us the details, we will do our best to reply you back.\n" +
                                "- 10loco Inc.\n");
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        adapter = new ListViewAdapter();



        listView = (ListView)findViewById(R.id.activity_setting_listview);

        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);

        switch ( sharedPreferences.getString("userData_Lang",null)){
            case "ENGLISH":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"MY ACCOUNT",  "ACCOUNT");
                //adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_privacy),"PRIVACY INFO",  "PRIVACY");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"TERMS OF USE",  "TERMS");
                //adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_patch),"PATCH NOTE",  "PATCH");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"CONTACT US",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"LANGUAGE SETTING","TRAN");
                break;
            case "한국어":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"계정",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"이용 약관",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"문의",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"언어 설정","TRAN");
                break;
            case "French":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"Mon compte",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"Conditions d'utilisation",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"Contactez-nous",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"Cadre linguistique","TRAN");
                break;
            case "汉语":
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_account),"我的帐户",  "ACCOUNT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_terms),"使用条款",  "TERMS");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_contact),"联络我们",  "CONTACT");
                adapter.addItem_contents(getResources().getDrawable(R.drawable.ic_translation),"翻译","TRAN");
                break;
        }
    }
}