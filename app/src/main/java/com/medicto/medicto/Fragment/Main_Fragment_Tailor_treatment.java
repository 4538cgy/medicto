package com.medicto.medicto.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.RecyclerViewType;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main_Fragment_Tailor_treatment extends Fragment {

    ArrayList<RecyclerItem> mList_Allergy = null
                            ,mList_Chronic = null
                            ,mList_Pregnancy = null
                            ,mList_Treatment = null;
    RecyclerViewAdapter recyclerViewAdapter_Allergy
                        ,recyclerViewAdapter_Chronic
                        ,recyclerViewAdapter_Pregnancy
                        ,recyclerViewAdapter_Treatment;

    Spinner spinner1
            ,spinner2
            ,spinner3;

    private String Language = null;

    public static TextView titleTextview,chronicTextview,allergyTextview,pregnancyTextview;

    String[] AllergyList = null;
    String[] PregnancyList = null;
    String[] ChronicList = null;

    public static Main_Fragment_Tailor_treatment newInstance(String Language){
        Main_Fragment_Tailor_treatment main_fragment_tailor_treatment = new Main_Fragment_Tailor_treatment();
        Bundle args = new Bundle();
        args.putString("lang",Language);
        main_fragment_tailor_treatment.setArguments(args);
        return main_fragment_tailor_treatment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tailor_treatment,container,false);
        titleTextview = (TextView)view.findViewById(R.id.fragment_tailor_treatment_textview_title);
        chronicTextview = (TextView)view.findViewById(R.id.fragment_tailor_treatment_textview_chronic);
        allergyTextview = (TextView)view.findViewById(R.id.fragment_tailor_treatment_textview_allergy);
        pregnancyTextview = (TextView)view.findViewById(R.id.fragment_tailor_treatment_textview_pregnancy);

        Language = getArguments().getString("lang");
        initializedList(Language);
        //this.initialized();
        mList_Allergy = new ArrayList<>();
        mList_Chronic = new ArrayList<>();
        mList_Pregnancy = new ArrayList<>();
      //  mList_Treatment = new ArrayList<>();

        recyclerViewAdapter_Allergy = new RecyclerViewAdapter(mList_Allergy);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_tailor_treatment_recyclerview_allergy);

        recyclerViewAdapter_Chronic = new RecyclerViewAdapter(mList_Chronic);
        RecyclerView recyclerView1 = view.findViewById(R.id.fragment_tailor_treatment_recyclerview_chronic);

        recyclerViewAdapter_Pregnancy = new RecyclerViewAdapter(mList_Pregnancy);
        RecyclerView recyclerView2 = view.findViewById(R.id.fragment_tailor_treatment_recyclerview_pregnancy);

        recyclerViewAdapter_Treatment = new RecyclerViewAdapter(mList_Treatment);

        //RecyclerView recyclerView3 = view.findViewById(R.id.fragment_tailor_treatment_recyclerview_treatment);






        spinner1 = (Spinner)view.findViewById(R.id.fragment_tailor_treatment_spinner);
        spinner2 = (Spinner)view.findViewById(R.id.fragment_tailor_treatment_spinner2);
        spinner3 = (Spinner)view.findViewById(R.id.fragment_tailor_treatment_spinner3);




        spinner1.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,ChronicList));
        spinner2.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,AllergyList));
        spinner3.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,PregnancyList));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager2.setReverseLayout(true);
        linearLayoutManager3.setReverseLayout(true);
        linearLayoutManager4.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setLayoutManager(linearLayoutManager2);
        recyclerView2.setLayoutManager(linearLayoutManager3);
        //recyclerView3.setLayoutManager(linearLayoutManager4);

        recyclerView.setAdapter(recyclerViewAdapter_Allergy);
        recyclerView1.setAdapter(recyclerViewAdapter_Chronic);
        recyclerView2.setAdapter(recyclerViewAdapter_Pregnancy);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (mList_Chronic.size() == 0){
                    mList_Chronic.add(new RecyclerItem(spinner1.getSelectedItem().toString(), RecyclerViewType.ViewType.ButtonView));
                    recyclerViewAdapter_Chronic.notifyDataSetChanged();
                }


                if (mList_Chronic.size() > 0 ){
                    if (mList_Chronic.get(0).getTitle().equals("None")
                            ||mList_Chronic.get(0).getTitle().equals("없음")
                            ||mList_Chronic.get(0).getTitle().equals("无")
                            ||mList_Chronic.get(0).getTitle().equals("Aucun")) {
                        mList_Chronic.remove(0);
                    }
                }

                for (int i = 0 ; i<mList_Chronic.size(); i++) {

                    if (mList_Chronic.get(i).getTitle().toString().equals(spinner1.getSelectedItem().toString())){
                        mList_Chronic.remove(i);
                        recyclerViewAdapter_Chronic.notifyDataSetChanged();
                    }


                }
                mList_Chronic.add(new RecyclerItem(spinner1.getSelectedItem().toString(), RecyclerViewType.ViewType.ButtonView));
                recyclerViewAdapter_Chronic.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (mList_Allergy.size() == 0){
                    mList_Allergy.add(new RecyclerItem(spinner2.getSelectedItem().toString(), RecyclerViewType.ViewType.ButtonView));
                    recyclerViewAdapter_Allergy.notifyDataSetChanged();
                }

                if (mList_Allergy.size() > 0 ){
                    if (mList_Allergy.get(0).getTitle().equals("None")
                    ||mList_Allergy.get(0).getTitle().equals("없음")
                    ||mList_Allergy.get(0).getTitle().equals("无")
                    ||mList_Allergy.get(0).getTitle().equals("Aucun")) {
                        mList_Allergy.remove(0);
                    }
                }
                for (int i = 0 ; i<mList_Allergy.size(); i++) {

                    if (mList_Allergy.get(i).getTitle().toString().equals(spinner2.getSelectedItem().toString())){
                        mList_Allergy.remove(i);
                        recyclerViewAdapter_Allergy.notifyDataSetChanged();
                    }


                }
               // System.out.println("Allergy  ====" + mList_Allergy.get(0).getTitle());

                mList_Allergy.add(new RecyclerItem(spinner2.getSelectedItem().toString(), RecyclerViewType.ViewType.ButtonView));
                recyclerViewAdapter_Allergy.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mList_Pregnancy.clear();
                mList_Pregnancy.add(new RecyclerItem(spinner3.getSelectedItem().toString(),RecyclerViewType.ViewType.ButtonView));
                recyclerViewAdapter_Pregnancy.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //recyclerView3.setAdapter(recyclerViewAdapter_Treatment);

        recyclerViewAdapter_Allergy.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0){
                    //증상 선택 리스트
                    System.out.println("asdfasdfasdfasdfasdfasdfasdfasdfsadf");

                }
            }
        });

        recyclerViewAdapter_Allergy.setOnItemRemoveListener(new RecyclerViewAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(View v, int position) {
                System.out.println("으아아아아아아아아아아ㅏ33333333");
                mList_Allergy.remove(position);
                recyclerViewAdapter_Allergy.notifyDataSetChanged();
            }
        });



        recyclerViewAdapter_Chronic.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0){
                    //증상 선택 리스트

                }else{

                }
            }
        });

        recyclerViewAdapter_Chronic.setOnItemRemoveListener(new RecyclerViewAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(View v, int position) {
                System.out.println("으아아아아아아아아아아ㅏ33333333");
                mList_Chronic.remove(position);
                recyclerViewAdapter_Chronic.notifyDataSetChanged();

            }
        });

        recyclerViewAdapter_Treatment.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0){
                    //증상 선택 리스트

                }else{

                }
            }
        });

        recyclerViewAdapter_Treatment.setOnItemRemoveListener(new RecyclerViewAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(View v, int position) {
                System.out.println("으아아아아아아아아아아ㅏ33333333");
                mList_Treatment.remove(position);
                recyclerViewAdapter_Treatment.notifyDataSetChanged();
            }
        });
        recyclerViewAdapter_Pregnancy.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0){
                    //증상 선택 리스트

                }else{

                }
            }
        });

        recyclerViewAdapter_Pregnancy.setOnItemRemoveListener(new RecyclerViewAdapter.OnItemRemoveListener() {
            @Override
            public void onItemRemove(View v, int position) {
                System.out.println("으아아아아아아아아아아ㅏ33333333");
                mList_Pregnancy.remove(position);
                recyclerViewAdapter_Pregnancy.notifyDataSetChanged();
            }
        });


        recyclerViewAdapter_Treatment.notifyDataSetChanged();
        recyclerViewAdapter_Pregnancy.notifyDataSetChanged();
        recyclerViewAdapter_Chronic.notifyDataSetChanged();
        recyclerViewAdapter_Allergy.notifyDataSetChanged();
        return view;
    }


    public void initializedList(String lang){

//titleTextview,chronicTextview,allergyTextview,pregnancyTextview
        PregnancyList = getResources().getStringArray(R.array.Pregnancy);

        switch (getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE).getString("userData_Lang",null)){

            case "ENGLISH":
                AllergyList = getResources().getStringArray(R.array.Allergy_English);
                ChronicList = getResources().getStringArray(R.array.ChronicDisease_English);
                titleTextview.setText("Questionnaire");
                chronicTextview.setText("Chronic Disease");
                allergyTextview.setText("Allergy");
                pregnancyTextview.setText("Pregnancy");
                break;
            case "한국어":
                AllergyList = getResources().getStringArray(R.array.Allergy_Korean);
                ChronicList = getResources().getStringArray(R.array.ChronicDisease_Korean);
                titleTextview.setText("문진표");
                chronicTextview.setText("만성질환");
                allergyTextview.setText("알러지");
                pregnancyTextview.setText("임신");
                break;
            case "汉语"    :
                AllergyList = getResources().getStringArray(R.array.Allergy_Chinese);
                ChronicList = getResources().getStringArray(R.array.ChronicDisease_Chinese);
                titleTextview.setText("问卷");
                chronicTextview.setText("慢性病");
                allergyTextview.setText("过敏性反应");
                pregnancyTextview.setText("妊娠");
                break;
            case "French":
                AllergyList = getResources().getStringArray(R.array.Allergy_French);
                ChronicList = getResources().getStringArray(R.array.ChronicDisease_French);
                titleTextview.setText("Questionnaire");
                chronicTextview.setText("Maladie chronique");
                allergyTextview.setText("Allergie");
                pregnancyTextview.setText("Grossesse");
                break;

        }

    }


}
