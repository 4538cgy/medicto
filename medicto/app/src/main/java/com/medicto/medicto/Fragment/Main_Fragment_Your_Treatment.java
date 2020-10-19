package com.medicto.medicto.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.LoginActivity;
import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.RecyclerViewType;
import com.medicto.medicto.WriteNoteActivity;
import com.medicto.medicto.model.InstructionModel;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Main_Fragment_Your_Treatment extends Fragment {

    ArrayList<RecyclerItem> mList = null;
    ArrayList<RecyclerItem> mList2 = null;
    ArrayList<RecyclerItem> codeList = null;
    RecyclerViewAdapter recyclerViewAdapter;
    TextView dayTextView,durationTextView,joursTextView,timingTextView,timingTextView2;
    ImageButton imageButton;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    TextView textViewtitle;

    public static Main_Fragment_Your_Treatment newInstance(String medicineName, String dayStr, String durationStr, String timingStr, ArrayList<String> timingList, String period, ArrayList<String> effectList, ArrayList<InstructionModel> list,ArrayList<RecyclerItem> instructionList,ArrayList<RecyclerItem> instructionCodeList,String Language){
        Main_Fragment_Your_Treatment main_fragment_your_treatment = new Main_Fragment_Your_Treatment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("instructionList",list);
        args.putString("medicineName",medicineName);
        args.putString("dayStr",dayStr);
        args.putString("durationStr",durationStr);
        args.putString("timingStr",timingStr);
        args.putString("period",period);
        args.putStringArrayList("timingList",timingList);
        args.putStringArrayList("effectList",effectList);
        args.putParcelableArrayList("instructionList", instructionList);
        args.putParcelableArrayList("instructionCodeList",instructionCodeList);

        args.putString("lang",Language);



        main_fragment_your_treatment.setArguments(args);
        return main_fragment_your_treatment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_treatment,container,false);

        textViewtitle = (TextView)view.findViewById(R.id.fragment_your_treatment_textview3);
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment_your_treatment_recycler);

        mList2 = getArguments().getParcelableArrayList("instructionList");
        codeList = getArguments().getParcelableArrayList("instructionCodeList");
        System.out.println("mlist의 크기는 = " +  mList2.size());

        for (int  i = 0 ;  i < mList2.size() ; i ++){
            System.out.println("mList의 목록" + i + "번째" + mList2.get(i).getDay() + mList2.get(i).getTiming());
        }

        switch (getArguments().getString("lang").toString()){
            case "한국어":
                textViewtitle.setText("결과");
                break;
            case "ENGLISH":
                textViewtitle.setText("Result");
                break;
            case "French":
                textViewtitle.setText("Résultat");
                break;
            case "汉语":
                textViewtitle.setText("结果");
                break;

        }

        this.initialized(getArguments().getString("lang").toString());
        imageButton = (ImageButton)view.findViewById(R.id.fragment_your_treatment_imagebutton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("asdmkfaksdf");
                Intent intent = new Intent(getActivity(), WriteNoteActivity.class);
                startActivity(intent);
            }
        });

            //recyclerViewAdapter = new RecyclerViewAdapter(mList2);

            recyclerViewAdapter = new RecyclerViewAdapter(mList2);

        recyclerViewAdapter.setContext(getActivity());
        recyclerViewAdapter.setLanguage(getArguments().getString("lang").toString());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData",MODE_PRIVATE);


        recyclerViewAdapter.setLanguage(sharedPreferences.getString("userData_Lang",null));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);



        recyclerViewAdapter.notifyDataSetChanged();






        return view;
    }

    public void initialized(String lang){
        mList = new ArrayList<>();


        for (int i = 0 ; i < codeList.size(); i ++){

            System.out.println("codelist" + codeList.get(i).getMedicineCode());
            System.out.println("instructionList" + mList2.get(i).getMedicineName());
            System.out.println("codelist" + codeList.get(i).getTimingStrCode());
            System.out.println("instructionList" + mList2.get(i).getTiming());
            System.out.println("codelist" + codeList.get(i).getEffectListCode());
            System.out.println("instructionList" + mList2.get(i).getEffectList());
            System.out.println("codelist" + codeList.get(i).getDayCode());
            System.out.println("instructionList" + mList2.get(i).getDay());
            System.out.println("codelist" + codeList.get(i).getTimingListCode());
            System.out.println("instructionList" + mList2.get(i).getTiming2());

        }




        mList.add(new RecyclerItem(getArguments().getString("medicineName"),getArguments().getString("dayStr"),getArguments().getString("durationStr"),getArguments().getString("timingStr"),getArguments().getString("period"),getArguments().getStringArrayList("timingList"),getArguments().getStringArrayList("effectList"),"", RecyclerViewType.ViewType.InstructionItem));


    }
}
