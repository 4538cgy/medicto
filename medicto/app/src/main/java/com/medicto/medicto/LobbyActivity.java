package com.medicto.medicto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medicto.medicto.model.UserModel;

import java.util.ArrayList;

public class LobbyActivity extends AppCompatActivity {


    String Language = null;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);



        ViewPager vp = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),LobbyActivity.this);
        vp.setAdapter(viewPagerAdapter);

        TabLayout tab = findViewById(R.id.tablayout);
        tab.setupWithViewPager(vp);

        ArrayList<Integer>  images = new ArrayList<>();
        images.add(R.drawable.ic_add_menu);
        images.add(R.drawable.mappoint2);
        images.add(R.drawable.ic_filefull);



        for (int i=0; i<3; i++) {
            tab.getTabAt(i).setIcon(images.get(i));
        }

    }


}