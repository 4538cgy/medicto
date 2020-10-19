package com.medicto.medicto.Fragment_Tab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.medicto.medicto.LobbyActivity;
import com.medicto.medicto.MainActivity;
import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.Setting.SettingActivity;
import com.medicto.medicto.SplashActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static  Button button;
    Button startButton;
    TextView titleTextview;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_2 newInstance(String param1, String param2) {
        Fragment_2 fragment = new Fragment_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2,container,false);

        button = (Button) view.findViewById(R.id.fragment_2_button_add);
        titleTextview = (TextView)view.findViewById(R.id.fragment_2_textview);
        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
            case "한국어":
                button.setText("시작");
                titleTextview.setText("증상을 약사에게 보여주세요");
                break;
            case "ENGLISH":
                button.setText("START");
                titleTextview.setText("Show Your Symptoms\n To Pharmacist");
                break;
            case "French":
                button.setText("Début");
                titleTextview.setText("Montrez vos symptômes au pharmacien");
                break;
            case "汉语":
                button.setText("开始");
                titleTextview.setText("给药剂师看你的症状");
                break;
        }


       // languageChanger();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

//    public void languageChanger(){
//        switch (getActivity().getSharedPreferences("userData",Context.MODE_PRIVATE).getString("userData_Lang",null)){
//            case "ENGLISH":
//                button.setText("ADD INSTRUCTION");
//                break;
//            case "French":
//                button.setText("ADD INSTRUCTION");
//                break;
//            case "한국어":
//                button.setText("문진 추가");
//                break;
//            case "汉语":
//                button.setText("ADD INSTRUCTION");
//                break;
//        }
//    }
}