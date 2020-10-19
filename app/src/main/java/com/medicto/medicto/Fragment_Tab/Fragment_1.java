package com.medicto.medicto.Fragment_Tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medicto.medicto.DeleteActivity;
import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.RecyclerViewType;
import com.medicto.medicto.Setting.SettingActivity;
import com.medicto.medicto.model.InstructionModelToDB;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerItem> mList;
    ArrayList<String> KeyArrayList_DATAKEY;
    ArrayList<String> KeyArrayList_TIMEKEY;
    String deleteKey;
    String time;

    ImageButton imageButton;

    private int REQUEST_CODE = 10001;
    private int RESULT_CODE = 0;  // 0 = false , 1 = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textView;
    public Fragment_1() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters
    public static Fragment_1 newInstance() {
        Fragment_1 fragment = new Fragment_1();
        Bundle args = new Bundle();

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
               String deleteCheck = data.getStringExtra("deleteCheck");
               if (deleteCheck.equals("delete")){
                   //삭제
                   System.out.println("삭제됩니다.");
                   System.out.println(deleteKey);
                   System.out.println(time);
                    removeData(deleteKey,time);
               }else{
                   //취소

               }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.fragment_1_recycler);

        textView = (TextView)view.findViewById(R.id.fragment_1_textview_title);

        imageButton = (ImageButton)view.findViewById(R.id.fragment_1_imagebutton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        languageChanger();



       // this.initialized();
            this.initialized2();

        recyclerViewAdapter = new RecyclerViewAdapter(mList);
        recyclerViewAdapter.setLanguage(getActivity().getSharedPreferences("userData", MODE_PRIVATE).getString("userData_Lang",null));
        recyclerViewAdapter.setContext(getActivity());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);


        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                if (mList.size() > 1) {
                    Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), DeleteActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);

                    if (KeyArrayList_DATAKEY.size() != 0 && KeyArrayList_TIMEKEY.size() != 0) {
                        deleteKey = KeyArrayList_DATAKEY.get(position);
                        time = KeyArrayList_TIMEKEY.get(position);
                    }
                }else{

                }
            }
        });

        recyclerViewAdapter.notifyDataSetChanged();



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        languageChanger();
    }

    public void languageChanger(){



        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
            case "ENGLISH":
                textView.setText("HISTORY");
                break;
            case "French":
                textView.setText("Historique");
                break;
            case "한국어":
                textView.setText("기록");
                break;
            case "汉语":
                textView.setText("历史");
                break;
        }
    }

    public void removeData(String key , String time){

        FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(time).child("instructionDataTest").child(key).removeValue();
        recyclerViewAdapter.notifyDataSetChanged();
        //key 는 KeyArrayList(position);
        System.out.println("키값은 = " + key);
    }

    public void initialized2(){
        mList = new ArrayList<>();
        KeyArrayList_DATAKEY = new ArrayList<>();
        KeyArrayList_TIMEKEY = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot item : snapshot.getChildren()) {
                    mList.clear();
                    KeyArrayList_TIMEKEY.clear();
                    KeyArrayList_DATAKEY.clear();

                    FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(item.getKey()).child("instructionDataTest").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot item : snapshot.getChildren()) {

                                InstructionModelToDB instructionModelToDB1 = item.getValue(InstructionModelToDB.class);
                                mList.add(new RecyclerItem(instructionModelToDB1.name
                                        , instructionModelToDB1.amount
                                        , instructionModelToDB1.count
                                        , instructionModelToDB1.timing
                                        , instructionModelToDB1.day
                                        , instructionModelToDB1.timingList
                                        , instructionModelToDB1.effectList
                                        , instructionModelToDB1.time
                                        , RecyclerViewType.ViewType.InstructionItem));

                                KeyArrayList_DATAKEY.add(item.getKey());
                                KeyArrayList_TIMEKEY.add(instructionModelToDB1.time);
                                recyclerViewAdapter.notifyDataSetChanged();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void initialized(){

        mList = new ArrayList<>();
        KeyArrayList_DATAKEY = new ArrayList<>();
        KeyArrayList_TIMEKEY = new ArrayList<>();




        FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item : snapshot.getChildren()){
                    System.out.println(item.getKey());

                    mList.clear();
                    KeyArrayList_TIMEKEY.clear();
                    KeyArrayList_DATAKEY.clear();

                    FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(item.getKey()).child("instructionDataTest").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot item : snapshot.getChildren()){
                                System.out.println("내부의"+item.getKey());
                                System.out.println("됩니다"+item.getValue());
                                InstructionModelToDB instructionModelToDB1 = item.getValue(InstructionModelToDB.class);
                                System.out.println(instructionModelToDB1.getTime());
                                System.out.println(instructionModelToDB1.time);




                                mList.add(new RecyclerItem(instructionModelToDB1.name
                                                                        ,instructionModelToDB1.amount
                                                                        ,instructionModelToDB1.count
                                                                        ,instructionModelToDB1.timing
                                                                        ,instructionModelToDB1.time
                                                                        ,RecyclerViewType.ViewType.HitoryItem));
                                KeyArrayList_DATAKEY.add(item.getKey());
                                KeyArrayList_TIMEKEY.add(instructionModelToDB1.time);
                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}