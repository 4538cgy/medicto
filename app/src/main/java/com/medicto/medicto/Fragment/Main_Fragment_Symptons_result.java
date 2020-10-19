package com.medicto.medicto.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;
import com.medicto.medicto.RecyclerViewAdapter;
import com.medicto.medicto.RecyclerViewType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main_Fragment_Symptons_result extends Fragment {

    ArrayList<RecyclerItem> mList = null;
    ArrayList<RecyclerItem> dataList = null;
    RecyclerViewAdapter recyclerViewAdapter;
    static TextView textView;
    ArrayList<RecyclerItem> transList = null;
    ArrayList<RecyclerItem> cleanMList = null;
    String Language = null;

    public static Main_Fragment_Symptons_result newInstance(int translateCheck, ArrayList<RecyclerItem> list,String Language) {
        Main_Fragment_Symptons_result main_fragment_symptons_result = new Main_Fragment_Symptons_result();
        Bundle args = new Bundle();
        args.putInt("translateCheck", translateCheck);
        args.putString("lang",Language);
        args.putParcelableArrayList("dataList", list);
        main_fragment_symptons_result.setArguments(args);
        return main_fragment_symptons_result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptons_result, container, false);
        textView = (TextView) view.findViewById(R.id.fragment_symptons_result_textview);
        cleanMList = new ArrayList<>();
        mList = new ArrayList<>();
        transList = new ArrayList<>();
        dataList = getArguments().getParcelableArrayList("dataList");
        Language = getArguments().getString("lang");


        this.initializedTransList(Language);

        translate();

        // this.initialized();



        if (getArguments().getInt("translateCheck") == 0){
            recyclerViewAdapter = new RecyclerViewAdapter(dataList);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_symptons_result_recycler);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerViewAdapter.notifyDataSetChanged();
        }else{

            recyclerViewAdapter = new RecyclerViewAdapter(cleanMList);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_symptons_result_recycler);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerViewAdapter.notifyDataSetChanged();

        }


        return view;
    }

    public void translate(){



        for (int  i = 0; i < dataList.size(); i++){

            for (int j= 0; j<mList.size(); j++){

                if (dataList.get(i).getInitialCode().equals(mList.get(j).getInitialCode())){
                    transList.add(mList.get(j));
                }

            }


        }


        Set<String> titles = new HashSet<String>();
        for (RecyclerItem list : transList){
            if (titles.add(list.getInitialCode())){
                cleanMList.add(list);
            }
        }

    }



    public void initializedTransList(String lang){
        switch (lang){

            case "한국어":
                initializedKoreanList(lang);
                textView.setText("이런 증상들이 있어요");
                break;
            case "ENGLISH":
                initializedEnglishList(lang);
                textView.setText("I have these symptoms");
                break;
            case "汉语":
                initializedChineseList(lang);
                textView.setText("我有这些症状");
                break;
            case "French":
                initializedFrenchList(lang);
                textView.setText("J'ai ces symptômes");
                break;

        }
    }

    public void initializedEnglishList(String a){

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cough),"Cough","B-CGH1001" , RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"Headache","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_runnynose),"Runny nose","B-RNNS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_sore_throat),"Sore Throat","B-SRTH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_fever),"Fever","B-FEVR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bodyache),"Body ache","B-BOAC1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"Headache","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"Diarrhea","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_vomit),"Vomit","B-VMIT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"Nausea","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"Heartburn","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"Diarrhea","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"Period Cramp","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_indigestion),"Indigestion","B-INDI1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_constipation),"Constipation","B-COPA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"Nausea","B-NAUS1001",RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"Heartburn","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_abrasion),"abrasion","B-ABRA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_acne),"Acne","B-ACNE1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_rash),"Rash","B-RASH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_ecma),"Ecma","B-ECZM1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_blister),"Blister","B-BLST1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_athletesfoot),"Athlete's Foot","B-ATFT1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"Period Cramp","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_pregnancy_test),"Pregnancy Test","B-PGTT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_birth_control),"Birth Control","B-BHCT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_morning_pill),"Morning Pill","B-MGPL1001", RecyclerViewType.ViewType.CircleImageTextView));

        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_saline),"Saline","B-SEWR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_test),"Pregnacy Test","B-SPRY1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_eyedrop),"Eyedrop","B-EYDR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandaid),"Bandaid","B-BDAD1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandage),"Bandage","B-BDAG1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_disinfectant),"Disinfectant","B-DSIT1001", RecyclerViewType.ViewType.CircleImageTextView));





    }

    public void initializedChineseList(String a){

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cough),"咳嗽","B-CGH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"头痛","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_runnynose),"流鼻涕","B-RNNS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_sore_throat),"咽喉痛","B-SRTH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_fever),"发烧","B-FEVR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bodyache),"全身酸痛","B-BOAC1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"头痛","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"腹泻","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_vomit),"呕吐","B-VMIT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"恶心","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"胃酸","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"腹泻","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"生理痛","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_indigestion),"消化不良","B-INDI1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_constipation),"便秘","B-COPA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"恶心","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"胃痛","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_abrasion),"擦伤","B-ABRA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_acne),"青春痘","B-ACNE1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_rash),"风疹","B-ECZM1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_ecma),"湿疹","B-COPA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_blister),"水泡","B-BLST1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_athletesfoot),"脚气","B-ATFT1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"生理痛","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_pregnancy_test),"妊娠测试","B-PGTT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_birth_control),"长期避孕药","B-BHCT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_morning_pill),"紧急避孕药","B-MGPL1001", RecyclerViewType.ViewType.CircleImageTextView));

        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_saline),"生理盐水","B-SEWR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_test),"啫喱水","B-SPRY1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_eyedrop),"人工泪液","B-EYDR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandaid),"创可贴","B-BDAD1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandage),"绷带","B-BDAG1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_disinfectant),"消毒水","B-DSIT1001", RecyclerViewType.ViewType.CircleImageTextView));




    }


    public void initializedFrenchList(String a){



                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cough),"Toux","B-CGH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"Mal de tête","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_runnynose),"Nez qui coule","B-RNNS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_sore_throat),"Mal de gorge","B-SRTH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_fever),"Fièvre","B-FEVR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bodyache),"Courbatures","B-BOAC1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"Mal de tête","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"Diarrhée","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_vomit),"Vomissement","B-VMIT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"Nausée","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"Mal au coeur","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"Diarrhée","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"Crampes menstruelles","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_indigestion),"Indigestion","B-INDI1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_constipation),"Constipation","B-COPA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"Nausée","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"Brûlures d'estomac","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_abrasion),"écorchure","B-ABRA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_acne),"Acné","B-ACNE1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_rash),"Irritations","B-RASH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_ecma),"Eczema","B-ECZM1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_blister),"Cloque","B-BLST1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_athletesfoot),"Champignons","B-ATFT1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"crampes menstruelles","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_pregnancy_test),"Test de grossesse","B-PGTT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_birth_control),"Pillule contraceptive","B-BHCT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_morning_pill),"Pillule du lendemain","B-MGPL1001", RecyclerViewType.ViewType.CircleImageTextView));

        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_saline),"Solution saline","B-SEWR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_test),"Spray","B-SPRY1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_eyedrop),"Collyre","B-EYDR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandaid),"Pansement","B-BDAD1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandage),"Bandage","B-BDAG1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_disinfectant),"Désinfectant","B-DSIT1001", RecyclerViewType.ViewType.CircleImageTextView));





    }


    public void initializedKoreanList(String a){

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cough),"기침","B-CGH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"두통","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_runnynose),"콧물","B-RNNS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_sore_throat),"인후염","B-SRTH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_fever),"열","B-FEVR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bodyache),"몸살","B-BOAC1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_headache),"두통","B-HDAC1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"설사","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_vomit),"구토","B-VMIT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"메스꺼움","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"속쓰림","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_diarrhea),"설사","B-DIAR1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"생리통","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_indigestion),"소화불량","B-INDI1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_constipation),"변비","B-COPA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_nausea),"메스꺼움","B-NAUS1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_heartburn),"속쓰림","B-HTBN1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_abrasion),"찰과상","B-ABRA1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_acne),"여드름","B-ACNE1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_rash),"두드러기","B-RASH1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_ecma),"습진","B-ECZM1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_blister),"물집","B-BLST1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_athletesfoot),"무좀","B-ATFT1001", RecyclerViewType.ViewType.CircleImageTextView));

                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_cramps),"생리통","B-PDCP1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_pregnancy_test),"임신테스트","B-PGTT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_birth_control),"경구피임약","B-BHCT1001", RecyclerViewType.ViewType.CircleImageTextView));
                mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_morning_pill),"사후피임약","B-MGPL1001", RecyclerViewType.ViewType.CircleImageTextView));

        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_saline),"식염수","B-SEWR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_test),"스프레이","B-SPRY1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_eyedrop),"인공눈물","B-EYDR1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandaid),"반창고","B-BDAD1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_bandage),"붕대","B-BDAG1001", RecyclerViewType.ViewType.CircleImageTextView));
        mList.add(new RecyclerItem(getResources().getDrawable(R.drawable.ic_disinfectant),"소독약","B-DSIT1001", RecyclerViewType.ViewType.CircleImageTextView));





    }



}

