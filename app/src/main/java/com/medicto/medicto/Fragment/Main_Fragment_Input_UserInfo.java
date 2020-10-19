package com.medicto.medicto.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medicto.medicto.Interface.FragmentDataSetListener;
import com.medicto.medicto.R;

public class Main_Fragment_Input_UserInfo extends Fragment implements View.OnClickListener{


    Spinner spinner1,spinner2,spinner3;
    ArrayAdapter<String> arrayAdapter;
    ToggleButton toggleButton;
    ImageButton profile;
    EditText name;
    boolean licenseCheck = false;


    private FragmentDataSetListener fragmentDataSetListener = null;

    public static Main_Fragment_Input_UserInfo newInstance(){
        Main_Fragment_Input_UserInfo main_fragment_input_userInfo = new Main_Fragment_Input_UserInfo();
        Bundle args = new Bundle();
        main_fragment_input_userInfo.setArguments(args);
        return main_fragment_input_userInfo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_userinfo,container,false);

        toggleButton = (ToggleButton)view.findViewById(R.id.activity_sign_up_togglebutton);
        toggleButton.setOnClickListener(this);

        profile = (ImageButton)view.findViewById(R.id.activity_sign_up_imagebutton);
        profile.setOnClickListener(this);

        name = (EditText)view.findViewById(R.id.activity_sign_up_edittext);
        name.setOnClickListener(this);

        spinner1 = (Spinner)view.findViewById(R.id.fragment_user_info_spinner1);
        spinner2 = (Spinner)view.findViewById(R.id.fragment_user_info_spinner2);
        spinner3 = (Spinner)view.findViewById(R.id.fragment_user_info_spinner3);

        String[] list = getResources().getStringArray(R.array.language);
        String[] list2 = getResources().getStringArray(R.array.nationality);
        String[] list3 = getResources().getStringArray(R.array.gender);

        spinner1.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,list));
        spinner2.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,list2));
        spinner3.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,list3));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_up_togglebutton:
                toggleButton.setTextOn("V");

                break;
            case R.id.activity_sign_up_imagebutton:
                Toast.makeText(getActivity(),"dddd",Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_sign_up_edittext:

                if (name.getText().equals("name *")) {
                    name.setText("");
                }

                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof FragmentDataSetListener){
            fragmentDataSetListener = (FragmentDataSetListener)context;

        }else {
            throw new RuntimeException(context.toString()+"must implement FragmentDataSetListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentDataSetListener = null;
    }
}
