package com.medicto.medicto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.medicto.medicto.Fragment.Main_Fragment_General_symptoms;
import com.medicto.medicto.Fragment.Main_Fragment_General_symptoms_2;
import com.medicto.medicto.Fragment.Main_Fragment_Input_UserInfo;
import com.medicto.medicto.Fragment.Main_Fragment_Instruction;
import com.medicto.medicto.Fragment.Main_Fragment_Pharmacist;
import com.medicto.medicto.Fragment.Main_Fragment_Symptons_result;
import com.medicto.medicto.Fragment.Main_Fragment_Tailor_treatment;
import com.medicto.medicto.Fragment.Main_Fragment_Your_Treatment;
import com.medicto.medicto.Interface.FragmentDataSetListener;
import com.medicto.medicto.Interface.FragmentDetailListSetListener;
import com.medicto.medicto.Interface.FragmentInstructionDataAddListener;
import com.medicto.medicto.Interface.FragmentInstructionDataSetListener;
import com.medicto.medicto.Interface.FragmentListSetListener;
import com.medicto.medicto.Interface.FragmentPharmacistListener;
import com.medicto.medicto.model.InstructionModel;
import com.medicto.medicto.model.InstructionModelToDB;
import com.medicto.medicto.model.MainSymtonsModel;
import com.medicto.medicto.model.SubSymtonsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                                                                FragmentDataSetListener,
                                                                FragmentListSetListener,
                                                                FragmentDetailListSetListener,
                                                                FragmentInstructionDataSetListener,
                                                                FragmentInstructionDataAddListener,
        FragmentPharmacistListener {

    Button button;
    int FragmentPage = 1;
    ArrayList<String> fragmentDataList = null;
    ArrayList<RecyclerItem> fragmentDetailDataList = null;

    String medicineName, dayStr = null, durationStr = null, period = null, timingStr = null,timingStrCode = null,medicineNameCode = null,periodCode = null;
    ArrayList<String> timingListPath = null, effectListPath = null;
    ArrayList<String> fragmentDetailDataStringList = null;
    ArrayList<String> timingListCode = null, effectListCode = null;
    ArrayList<InstructionModel> instructionModelArrayList;
    ArrayList<RecyclerItem> instructionList = null;
    ArrayList<RecyclerItem> instructionCodeList = null;


    int instructionCount = 0;

    String Language;
    String PharLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);
        Language = sharedPreferences.getString("userData_Lang",null);

        button = (Button) findViewById(R.id.activity_main_button);
        button.setOnClickListener(this);
        setButtonText(Language);


        timingListPath = new ArrayList<>();
        effectListPath = new ArrayList<>();
        instructionList = new ArrayList<>();
        instructionCodeList = new ArrayList<>();
        checkFragment(1);


    }

    public void setButtonText(String language){
        switch (language){
            case "한국어":
                button.setText("확인");
                break;
            case "ENGLISH":
                button.setText("Confirm");
                break;
            case "French":
                button.setText("Confirmer");
                break;
            case "汉语":
                button.setText("确认");
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_framelayout, fragment).commit();
    }

    private void checkFragment(int count) {

        switch (count) {
            case 0:
                replaceFragment(Main_Fragment_Input_UserInfo.newInstance());
                break;

            case 1:
                replaceFragment(Main_Fragment_Tailor_treatment.newInstance(Language));
                break;

            case 2:
                replaceFragment(Main_Fragment_General_symptoms.newInstance(Language));
                break;

            case 3:

                replaceFragment(Main_Fragment_General_symptoms_2.newInstance(fragmentDataList,Language));

                break;
            case 4:
                replaceFragment(Main_Fragment_Symptons_result.newInstance(0, fragmentDetailDataList,Language));
                break;
            case 5:
                replaceFragment(Main_Fragment_Pharmacist.newInstance(Language));
                break;
            case 6:
                replaceFragment(Main_Fragment_Symptons_result.newInstance(1, fragmentDetailDataList,PharLang));
                break;
            case 7:
                replaceFragment(Main_Fragment_Instruction.newInstance(0,instructionCount+1,PharLang));
                break;
            case 8:
                replaceFragment(Main_Fragment_Your_Treatment.newInstance(medicineName,dayStr,durationStr,timingStr,timingListPath,period,effectListPath,instructionModelArrayList,instructionList,instructionCodeList,Language));
                break;
            case 9:
                Intent intent = new Intent(this,LobbyActivity.class);
                startActivity(intent);
                //데이터들 DB에 올리는 그시기 추가해주길~
                finish();
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {




            //Validate 버튼
            case R.id.activity_main_button:



                FragmentPage++;

                switch (FragmentPage) {


                    case 3:
                        if (fragmentDataList != null) {
                            checkFragment(FragmentPage);
                        } else {
                            FragmentPage = 2;
                            Toast.makeText(getApplicationContext(), "Check the item", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 4:
                        if (fragmentDetailDataList != null) {
                            checkFragment(FragmentPage);
                        } else {
                            FragmentPage = 3;
                            Toast.makeText(getApplicationContext(), "Check the item", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 6:
                        switch (PharLang){
                            case "한국어":
                                button.setText("확인");
                                break;
                            case "ENGLISH":
                                button.setText("Confirm");
                                break;
                            case "French":
                                button.setText("Confirmer");
                                break;
                            case "汉语":
                                button.setText("确认");
                                break;
                        }
                        checkFragment(FragmentPage);
                        break;


                    case 8:

                        switch (getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "한국어":
                                button.setText("확인");
                                break;
                            case "ENGLISH":
                                button.setText("Confirm");
                                break;
                            case "French":
                                button.setText("Confirmer");
                                break;
                            case "汉语":
                                button.setText("确认");
                                break;
                        }


                        if (instructionCount > 0){
                            checkFragment(FragmentPage);
                            //데이터 DB에 저장
                            //PuID,time,instructionData
                            String time = new Get_time().getTime();
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            fragmentDetailDataStringList = new ArrayList<>();

                            for(int i = 0 ; i < instructionList.size(); i++) {
                                InstructionModelToDB instructionModelToDB = new InstructionModelToDB();

                               // instructionModelToDB.name = instructionList.get(i).getInitialCode().toString();


                                instructionModelToDB.name = instructionList.get(i).getMedicineName().toString();
                                instructionModelToDB.amount = instructionList.get(i).getDay().toString();
                                instructionModelToDB.count = instructionList.get(i).getDuration().toString();
                                instructionModelToDB.timingList = instructionList.get(i).getList();
                                instructionModelToDB.effectList = instructionList.get(i).getEffectList();
                                instructionModelToDB.timing = instructionList.get(i).getTiming().toString();
                                instructionModelToDB.day = instructionList.get(i).getTiming2();
                                instructionModelToDB.time = time;
                                instructionModelToDB.pUid = uid;

                                FirebaseDatabase.getInstance().getReference().child("UserData").child(uid).child(time).child("instructionDataTest").push().setValue(instructionModelToDB);
                            }
                            MainSymtonsModel mainSymtonsModel = new MainSymtonsModel();
                            mainSymtonsModel.pUid = uid;
                            mainSymtonsModel.mainSymtonsList = fragmentDataList;
                            mainSymtonsModel.time = time;
                            FirebaseDatabase.getInstance().getReference().child("UserData").child(uid).child(time).child("mainSymptonsData").push().setValue(mainSymtonsModel);

                            for (int  i = 0 ; i < fragmentDetailDataList.size(); i++){
                                //fragmentDetailDataStringList.add(fragmentDetailDataList.get(i).getTitle());
                                fragmentDetailDataStringList.add(fragmentDetailDataList.get(i).getInitialCode());
                            }

                            SubSymtonsModel subSymtonsModel = new SubSymtonsModel();
                            subSymtonsModel.pUid = uid;
                           subSymtonsModel.subSymtonsList = fragmentDetailDataStringList;
                            subSymtonsModel.time = time;



                            FirebaseDatabase.getInstance().getReference().child("UserData").child(uid).child(time).child("subSymptonsData").push().setValue(subSymtonsModel);

                                FirebaseDatabase.getInstance().getReference().child("UserData").child("mainSymptonsData").push().setValue(mainSymtonsModel);
                                FirebaseDatabase.getInstance().getReference().child("UserData").child("subSymptonsData").push().setValue(subSymtonsModel);

                        }else
                        {
                            FragmentPage = 6;
                            Toast.makeText(getApplicationContext(), "Add Instruction", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 9:
                        checkFragment(FragmentPage);
                        break;
                    default:
                        checkFragment(FragmentPage);
                        break;
                }

                break;

            case 1:
                break;

        }
    }

    @Override
    public void FragmentDataSetListener(int page) {
        checkFragment(page);
    }

    @Override
    public void setFragmentListData(ArrayList<String> list) {

        System.out.println("으아아아아아앜ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");

        fragmentDataList = list;

        for (int i = 0; i < fragmentDataList.size(); i++) {

            System.out.println(fragmentDataList.get(i).toString());
        }
    }

    @Override
    public void setFragmentDetailListData(ArrayList<RecyclerItem> list) {
        System.out.println("으아아아앜 세부정보 세부정보오오오오옼 끠에에에에에에엙");
        fragmentDetailDataList = list;

        for (int i = 0; i < list.size(); i++) {

            System.out.println(fragmentDetailDataList.get(i).toString());
        }

    }


    @Override
    public void setInstructionDataList(String medicineName, String count, String duration, String timing, ArrayList<String> timingList, String timing2, ArrayList<String> effectList,String timingStrCode,String medicineNameCode,ArrayList<String> timingListCode,ArrayList<String> effectListCode,String periodCode, int DataType) {

        if (DataType == 0) {
            this.timingStrCode = timingStrCode;
            this.medicineName = medicineName;
            this.dayStr = count;
            this.durationStr = duration;
            this.timingStr = timing;
            this.timingListPath = timingList;
            this.period = timing2;
            this.effectListPath = effectList;
            this.medicineNameCode = medicineNameCode;
            this.effectListCode = effectListCode;
            this.timingListCode = timingListCode;
            this.periodCode = periodCode;
            System.out.println("medicineName = " + medicineName + "\nday = " + count + "\nduration" + duration + "\ntiming" + timing + "\ntiming2" + timing2 );
        } else if(DataType == 1){
            instructionModelArrayList.add(new InstructionModel(medicineName,count,duration,timing,timingList,timing2,effectList));
            System.out.println("2222" + "\nmedicineName = " + medicineName + "\nday = " + count + "\nduration" + duration + "\ntiming" + timing + "\ntiming2" + timing2 );
        }
    }

    @Override
    public void setInstructionDataListAdd(ArrayList<RecyclerItem> list) {


    }

    @Override
    public void addInstructionDataList() {
        instructionCount ++ ;




        instructionList.add(new RecyclerItem(medicineName,dayStr,durationStr,timingStr,period,timingListPath,effectListPath,"",RecyclerViewType.ViewType.InstructionItem));
        instructionCodeList.add(new RecyclerItem(timingStrCode,periodCode,medicineNameCode,timingListCode,effectListCode));
        this.replaceFragment(Main_Fragment_Instruction.newInstance(0,instructionCount,PharLang));

    }

    @Override
    public void setPharmacistLanguage(String s) {
        PharLang = s;
        System.out.println("데이터 전달");
        System.out.println("s의 값" + s);
        System.out.println("PharLang의 값"+PharLang);
    }
}