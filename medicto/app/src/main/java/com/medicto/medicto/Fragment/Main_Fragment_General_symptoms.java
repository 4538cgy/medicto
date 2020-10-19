package com.medicto.medicto.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.Interface.FragmentListSetListener;
import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.RecyclerViewType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main_Fragment_General_symptoms extends Fragment {


    ArrayList<RecyclerItem> mList = null;
    ArrayList<String> SelectList = null;
    ArrayList<String> removeList = null;
    RecyclerViewAdapter recyclerViewAdapter;
   static TextView textView;
    String Langauge;

    private FragmentListSetListener fragmentListSetListener = null;

    public static Main_Fragment_General_symptoms newInstance(String Language){
        Main_Fragment_General_symptoms main_fragment_general_symptoms = new Main_Fragment_General_symptoms();
        Bundle args = new Bundle();
        args.putString("lang",Language);
        main_fragment_general_symptoms.setArguments(args);
        return main_fragment_general_symptoms;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_symptoms,container,false);
        textView = (TextView)view.findViewById(R.id.fragment_general_symptoms_textview_title);
        Langauge = getArguments().getString("lang");

        this.initialized(Langauge);
        SelectList = new ArrayList<>();
        removeList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(mList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_general_symptoms_recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter.notifyDataSetChanged();

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                ImageButton imageButton = (ImageButton)v.findViewById(R.id.recycler_square_item_imagebutton);

                if(imageButton.isSelected() == true){
                    for (int i = 0 ; i < SelectList.size(); i++){





                        if (SelectList.get(i).equals(mList.get(position).getInitialCode().toString())){
                            SelectList.remove(i);
                        }


                    }

                }else{


                    SelectList.add(mList.get(position).getInitialCode().toString());
                    imageButton.setSelected(false);
                }

                fragmentListSetListener.setFragmentListData(SelectList);
            }
        });

        return view;
    }



    public void initialized(String lang){

        mList = new ArrayList<>();
       // Drawable drawable,String title,int viewType



        switch (lang){
            /*

            0 = Cold
            1 = Hangover
            2 = Stomachache
            3 = SkinDisease
            4 = WomenHealth
            5 = 번역필요요

            */

            case "ENGLISH":
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cold),"Cold","A-CLD1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_hangover),"Hangover","A-HNGVR1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_stomachache),"Stomachache","A-STMCH1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_skin_disease),"Skin Disease","A-SKND1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_women_health),"Women Health","A-WMHT1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_others),"Medical Supplies","A-MS1000", RecyclerViewType.ViewType.SquareImageTextView));
                textView.setText("Choose your symptoms");
                break;
            case "한국어":
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cold),"감기","A-CLD1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_hangover),"숙취","A-HNGVR1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_stomachache),"복통","A-STMCH1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_skin_disease),"피부 질환","A-SKND1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_women_health),"여성 건강","A-WMHT1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_others),"의약외품","A-MS1000", RecyclerViewType.ViewType.SquareImageTextView));
                textView.setText("증상을 선택해주세요");
                break;
            case "汉语"    :
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cold),"感冒","A-CLD1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_hangover),"宿醉","A-HNGVR1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_stomachache),"腹痛","A-STMCH1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_skin_disease),"皮肤疾患","A-SKND1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_women_health),"女性健康","A-WMHT1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_others),"医疗用品","A-MS1000", RecyclerViewType.ViewType.SquareImageTextView));
                textView.setText("选择你的症状");
                break;
            case "French":
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cold),"Rhume","A-CLD1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_hangover),"Gueule de bois","A-HNGVR1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_stomachache),"Mal de ventre","A-STMCH1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_skin_disease),"Eruption cutanée","A-SKND1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_women_health),"Santé féminine","A-WMHT1000", RecyclerViewType.ViewType.SquareImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_others),"Boîte à pharmacie","A-MS1000", RecyclerViewType.ViewType.SquareImageTextView));
                textView.setText("Choisissez vos symptômes");
                break;

        }



    }



    public void setFragmentListSetListener(FragmentListSetListener fragmentListSetListener){
        this.fragmentListSetListener = fragmentListSetListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentListSetListener){
            fragmentListSetListener = (FragmentListSetListener)context;
        }else{
            throw new RuntimeException(context.toString() + "must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListSetListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof FragmentListSetListener ){
            this.fragmentListSetListener = (FragmentListSetListener)getActivity();
        }
    }
}
