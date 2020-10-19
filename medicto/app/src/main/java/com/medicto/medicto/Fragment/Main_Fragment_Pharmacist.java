package com.medicto.medicto.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medicto.medicto.Interface.FragmentListSetListener;
import com.medicto.medicto.Interface.FragmentPharmacistListener;
import com.medicto.medicto.R;

public class Main_Fragment_Pharmacist extends Fragment {

    Spinner spinner;
    String lang = null;

    TextView textView;

    private FragmentPharmacistListener fragmentPharmacistListener = null;

    public static Main_Fragment_Pharmacist newInstance(String lang){
        Main_Fragment_Pharmacist main_fragment_pharmacist = new Main_Fragment_Pharmacist();
        Bundle args = new Bundle();
        args.putString("lang",lang);
        main_fragment_pharmacist.setArguments(args);
        return main_fragment_pharmacist;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharmacist,container,false);
        spinner = (Spinner)view.findViewById(R.id.fragement_pharmacist_spinner);
        String[] list = getResources().getStringArray(R.array.language);
        lang = getArguments().getString("lang",null);
        textView = (TextView)view.findViewById(R.id.fragment_pharmacist_title);
        titlechanger(lang);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,list));



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = spinner.getSelectedItem().toString();
                fragmentPharmacistListener.setPharmacistLanguage(lang);
                System.out.println("데이터 선택");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    public void titlechanger(String lang){
        switch (getActivity().getSharedPreferences("userData",Context.MODE_PRIVATE).getString("userData_Lang",null).toString()){
            case "한국어":
                textView.setText("약사에게 보여주세요");
                break;
            case "ENGLISH":
                textView.setText("Show it to the pharmacist");
                break;
            case "French":
                textView.setText("Montrez-la au pharmacien");
                break;
            case "汉语":
                textView.setText("给药剂师看看");
                break;

        }

    }

    public void setFragmentPharmacistListener(FragmentPharmacistListener fragmentPharmacistListener){
        this.fragmentPharmacistListener = fragmentPharmacistListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentPharmacistListener){
            fragmentPharmacistListener = (FragmentPharmacistListener)context;
        }else{
            throw new RuntimeException(context.toString() + "must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentPharmacistListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof FragmentPharmacistListener ){
            this.fragmentPharmacistListener = (FragmentPharmacistListener)getActivity();
        }
    }
}
