package com.medicto.medicto;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.medicto.medicto.Fragment_Tab.Fragment_1;
import com.medicto.medicto.Fragment_Tab.Fragment_2;
import com.medicto.medicto.Fragment_Tab.Fragment_3;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<>();

    String Language = null;
    public ViewPagerAdapter(@NonNull FragmentManager fm,Context context) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new Fragment_2());
        items.add(new Fragment_3());
        items.add(new Fragment_1());


        SharedPreferences sharedPreferences = context.getSharedPreferences("userData",MODE_PRIVATE);
        Language = sharedPreferences.getString("userData_Lang",null);


        switch (Language) {
            case "ENGLISH":

                itext.add("SHOW");
                itext.add("PHARMACY");
                itext.add("HISTORY");
                break;
            case "French":
                itext.add("Montrer");
                itext.add("Pharmacie");
                itext.add("Historique");

                break;
            case "한국어":
                itext.add("보여주기");
                itext.add("약국");
                itext.add("기록");





                break;
            case "汉语":
                itext.add("瞧瞧");
                itext.add("药房");
                itext.add("历史");
                break;
        }

    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }
}
