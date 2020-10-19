package com.medicto.medicto.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medicto.medicto.Interface.FragmentInstructionDataAddListener;
import com.medicto.medicto.Interface.FragmentInstructionDataSetListener;
import com.medicto.medicto.MainActivity;
import com.medicto.medicto.R;
import com.medicto.medicto.RecyclerItem;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Main_Fragment_Instruction extends Fragment implements View.OnClickListener {

    TextView medicineNameTextView;
    TextView countTextView,durationTextView,timingTextView,effectTextView,beforeTextView,afterTextView;
    Spinner spinner;
    Button dayButton,lunchButton,dinnerButton,eveningButton,sleepyButton,headacheButton,upButton,button4,addButton;
    ToggleButton toggleButton,toggleButton2;

    Spinner spinner1, spinner2;
    boolean dayCheck = false, durationCheck = false, timingCheck = false, timingCheck2 = false, effectCheck = false;

    EditText editText;

    int day = 1,duration = 1;
    String period;
    int medicineCount ;

    String medicineName,dayStr,durationStr,timingStr;

    String timingStrCode,medicineNameCode;
    String periodCode;

    ArrayList<String> timingListCode,effecListCode;

    ArrayList<String> timingList,effectList;
    ArrayList<RecyclerItem> mList;
    private FragmentInstructionDataSetListener fragmentInstructionDataSetListener = null;
    private FragmentInstructionDataAddListener fragmentInstructionDataAddListener = null;

    String[] list = null;

    public static Main_Fragment_Instruction newInstance(int translateCheck, int medicineCount,String Language){
        Main_Fragment_Instruction main_fragment_instruction = new Main_Fragment_Instruction();
        Bundle args = new Bundle();
        args.putString("lang",Language);
        args.putInt("medicineCount",medicineCount);
        main_fragment_instruction.setArguments(args);

        return main_fragment_instruction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction,container,false);
        mList = new ArrayList<>();
        timingList = new ArrayList<>();
        effectList = new ArrayList<>();
        timingListCode = new ArrayList<>();
        effecListCode = new ArrayList<>();


        spinner1 = (Spinner) view.findViewById(R.id.fragment_instruction_item_spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.fragment_instruction_item_spinner2);
        dayButton = (Button)view.findViewById(R.id.fragment_instruction_item_button);
        lunchButton = (Button)view.findViewById(R.id.fragment_instruction_item_button2);
        dinnerButton = (Button)view.findViewById(R.id.fragment_instruction_item_button3);
        eveningButton = (Button)view.findViewById(R.id.fragment_instruction_item_button4);
        sleepyButton = (Button)view.findViewById(R.id.fragment_instruction_item_button5);
        headacheButton = (Button)view.findViewById(R.id.fragment_instruction_item_button6);
        upButton = (Button)view.findViewById(R.id.fragment_instruction_item_button7);
        button4 = (Button)view.findViewById(R.id.fragment_instruction_item_button8);
        addButton = (Button)view.findViewById(R.id.fragment_instruction_addbutton);
        toggleButton = (ToggleButton)view.findViewById(R.id.fragment_instruction_item_togglebutton1);
        toggleButton2 = (ToggleButton)view.findViewById(R.id.fragment_instruction_item_togglebutton2);
        spinner = (Spinner)view.findViewById(R.id.fragment_instruction_item_spinner);
        medicineNameTextView = (TextView) view.findViewById(R.id.fragment_instruction_item_textview10);
        beforeTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview7);
        afterTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview8) ;

        countTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview2);
        durationTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview4);
        timingTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview6);
        effectTextView = (TextView)view.findViewById(R.id.fragment_instruction_item_textview9);


        dayButton.setOnClickListener(this);
        lunchButton.setOnClickListener(this);
        dinnerButton.setOnClickListener(this);
        eveningButton.setOnClickListener(this);
        sleepyButton.setOnClickListener(this);
        headacheButton.setOnClickListener(this);
        upButton.setOnClickListener(this);
        button4.setOnClickListener(this);
        addButton.setOnClickListener(this);
        toggleButton.setOnClickListener(this);
        toggleButton2.setOnClickListener(this);


        LanguageChanger(getArguments().getString("lang"));

        medicineCount = getArguments().getInt("medicineCount");


        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
            case "ENGLISH":
                medicineName = "Medicine" + medicineCount;
                medicineNameCode = "Medi-001"+ medicineCount;
                break;
            case "French":
                medicineName = "Médicament" + medicineCount;
                medicineNameCode = "Medi-001"+ medicineCount;

                break;
            case "한국어":
                medicineName = "의약품" + medicineCount;
                medicineNameCode = "Medi-001"+ medicineCount;
                break;
            case "汉语":
                medicineName = "医学" + medicineCount;
                medicineNameCode = "Medi-001"+ medicineCount;

                break;
        }


        medicineNameTextView.setText(medicineName);



        String[] numberList = getResources().getStringArray(R.array.number);
        String[] numberList2 = getResources().getStringArray(R.array.number);

        spinner.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,list));
        spinner1.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,numberList));
        spinner2.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_spinner,numberList2));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*
                period = spinner.getSelectedItem().toString();

                switch (position){
                    case 0:
                        periodCode = "Day-1001";
                        break;
                    case 1:
                        periodCode = "Week-1001";
                        break;
                }

                 */


                switch (getActivity().getSharedPreferences("userData",Context.MODE_PRIVATE).getString("userData_Lang",null).toString()){
                    case "한국어":
                        switch (position){
                            case 0:
                                period = "일";
                                break;
                            case 1:
                                period = "주";
                                break;
                        }
                        break;
                    case "ENGLISH":
                        switch (position){
                            case 0:
                                period = "Days";
                                break;
                            case 1:
                                period = "Weeks";
                                break;
                        }
                        break;
                    case "French":
                        switch (position){
                            case 0:
                                period = "Jour";
                                break;
                            case 1:
                                period = "Semaine";
                                break;
                        }
                        break;
                    case "汉语":
                        switch (position){
                            case 0:
                                period = "日";
                                break;
                            case 1:
                                period = "一周";
                                break;
                        }
                        break;

                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayStr = spinner1.getSelectedItem().toString();
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                dayCheck = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                durationStr = spinner2.getSelectedItem().toString();
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                durationCheck = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInstructionDataSetListener){
            fragmentInstructionDataSetListener = (FragmentInstructionDataSetListener)context;
        }else{
            throw  new RuntimeException(context.toString() + "must implements FragmentListener");
        }
        if (context instanceof FragmentInstructionDataAddListener){
            fragmentInstructionDataAddListener = (FragmentInstructionDataAddListener)context;
        }else {
            throw  new RuntimeException(context.toString()+"must implements FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInstructionDataSetListener = null;
        fragmentInstructionDataAddListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof FragmentInstructionDataSetListener){
            this.fragmentInstructionDataSetListener = (FragmentInstructionDataSetListener)getActivity();
        }
        if (getActivity() instanceof FragmentInstructionDataAddListener){
            this.fragmentInstructionDataAddListener = (FragmentInstructionDataAddListener)getActivity();
        }
    }

    public void LanguageChanger(String lang){
        switch (lang){

            case "한국어":
                countTextView.setText("복약량");
                durationTextView.setText("기간");
                timingTextView.setText("언제");
                effectTextView.setText("주의사항");
                beforeTextView.setText("식전");
                afterTextView.setText("식후");
                dayButton.setText("아침");
                lunchButton.setText("점심");
                dinnerButton.setText("저녁");
                eveningButton.setText("자기전");
                sleepyButton.setText("없음");
                headacheButton.setText("졸림");
                upButton.setText("혈압");
                button4.setText("설사");
                addButton.setText("추가");
                list = getResources().getStringArray(R.array.days_korean);
                break;
            case "ENGLISH":
                countTextView.setText("Dosage");
                durationTextView.setText("Period");
                timingTextView.setText("When");
                effectTextView.setText("Caution");
                beforeTextView.setText("before meal");
                afterTextView.setText("after meal");
                dayButton.setText("Breakfast");
                lunchButton.setText("Lunch");
                dinnerButton.setText("Dinner");
                eveningButton.setText("Before Sleep");
                sleepyButton.setText("None");
                headacheButton.setText("Drowsiness");
                upButton.setText("Blood pressure ↑");
                button4.setText("Diarrhea");
                addButton.setText("Add");
                list = getResources().getStringArray(R.array.days_english);
                break;
            case "汉语":
                countTextView.setText("多萨奇");
                durationTextView.setText("期间");
                timingTextView.setText("什么时候");
                effectTextView.setText("注意事项");
                beforeTextView.setText("饭前");
                afterTextView.setText("饭后");
                dayButton.setText("早饭");
                lunchButton.setText("午饭");
                dinnerButton.setText("晚饭");
                eveningButton.setText("就寝 之前");
                sleepyButton.setText("无");
                headacheButton.setText("困意");
                upButton.setText("血压上升");
                button4.setText("腹泻");
                addButton.setText("添加");
                list = getResources().getStringArray(R.array.days_chinese);
                break;
            case "French":
                countTextView.setText("Dose");
                durationTextView.setText("Période");
                timingTextView.setText("Quand");
                effectTextView.setText("Attention");
                beforeTextView.setText("Avant le repas");
                afterTextView.setText("Après le repas");
                dayButton.setText("petit-déjeuner");
                lunchButton.setText("déjeuner");
                dinnerButton.setText("dîner");
                eveningButton.setText("avant le coucher");
                sleepyButton.setText("Aucun");
                headacheButton.setText("assoupissement");
                upButton.setText("tension artérielle ↑");
                button4.setText("Diarrhée");
                addButton.setText("Ajouter");
                list = getResources().getStringArray(R.array.days_french);
                break;

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.fragment_instruction_item_togglebutton1:
                toggleButton2.setTextOff(null);

                switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                    case "ENGLISH":
                        timingStr = "Before meal";
                        timingStrCode = "B-BFML1001";
                        break;
                    case "French":
                        timingStr = "Avant le repas";
                        timingStrCode = "B-BFML1001";

                        break;
                    case "한국어":
                        timingStr = "식전";
                        timingStrCode = "B-BFML1001";
                        break;
                    case "汉语":
                        timingStr = "饭前";
                        timingStrCode = "B-BFML1001";

                        break;
                }

                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck2 = true;
                break;
            case R.id.fragment_instruction_item_togglebutton2:
                toggleButton.setTextOff(null);

                switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                    case "ENGLISH":
                        timingStr = "After meal";
                        timingStrCode = "B-AFML1001";
                        break;
                    case "French":
                        timingStr = "Après le repas";
                        timingStrCode = "B-AFML1001";

                        break;
                    case "한국어":
                        timingStr = "식후";
                        timingStrCode = "B-AFML1001";

                        break;
                    case "汉语":
                        timingStr = "饭后";
                        timingStrCode = "B-AFML1001";

                        break;
                }



                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck2 = true;
                break;




            case R.id.fragment_instruction_item_button:



                if(dayButton.isSelected()){
                    dayButton.setBackgroundResource(R.drawable.edittext_background);
                    dayButton.setSelected(false);
                    for (int i=0; i<timingList.size(); i++){


                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                if(timingList.get(i).toString()=="Breakfast"){
                                    timingList.remove(i);
                                }
                                break;
                            case "French":
                                if(timingList.get(i).toString()=="petit-déjeuner"){
                                    timingList.remove(i);
                                }
                                break;
                            case "한국어":
                                if(timingList.get(i).toString()=="아침"){
                                    timingList.remove(i);
                                }
                                break;
                            case "汉语":
                                if(timingList.get(i).toString()=="早饭"){
                                    timingList.remove(i);
                                }
                                break;
                        }
                    }

                    for (int i=0; i<timingListCode.size(); i++){
                        if (timingListCode.get(i).toString() == "B-BRFT1001"){
                            timingListCode.remove(i);
                        }
                    }


                }else{
                    dayButton.setBackgroundResource(R.drawable.edittext_background_green);
                    dayButton.setSelected(true);
                    if (!timingList.contains(dayButton.getText().toString())) {
                       // timingList.add(dayButton.getText().toString());
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                timingList.add("Breakfast");
                                break;
                            case "French":
                                timingList.add("petit-déjeuner");
                                break;
                            case "한국어":
                                timingList.add("아침");
                                break;
                            case "汉语":
                                timingList.add("早饭 ");
                                break;
                        }
                    }
                    if (!timingListCode.contains("B-BRFT1001")) {


                        timingListCode.add("B-BRFT1001");
                    }

                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck = true;
                break;



            case R.id.fragment_instruction_item_button2:
                if(lunchButton.isSelected()){
                    lunchButton.setBackgroundResource(R.drawable.edittext_background);
                    lunchButton.setSelected(false);
                    for (int i=0; i<timingList.size(); i++){
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                            case "ENGLISH":
                                if (timingList.get(i).toString() == "Lunch") {
                                    timingList.remove(i);
                                }
                                break;
                            case "French":
                                if (timingList.get(i).toString() == "déjeuner") {
                                    timingList.remove(i);
                                }
                                break;
                            case "한국어":
                                if (timingList.get(i).toString() == "점심") {
                                    timingList.remove(i);
                                }
                                break;
                            case "汉语":
                                if (timingList.get(i).toString() == "午饭") {
                                    timingList.remove(i);
                                }
                                break;
                        }
                    }
                    for (int i=0; i<timingListCode.size(); i++){
                        if (timingListCode.get(i).toString() == "B-LNCH1001"){
                            timingListCode.remove(i);
                        }
                    }
                }else{
                    lunchButton.setBackgroundResource(R.drawable.edittext_background_green);
                    lunchButton.setSelected(true);
                    if (!timingList.contains(lunchButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                timingList.add("Lunch");
                                break;
                            case "French":
                                timingList.add("déjeuner");
                                break;
                            case "한국어":
                                timingList.add("점심");
                                break;
                            case "汉语":
                                timingList.add("午饭 ");
                                break;
                        }
                    }
                    if (!timingListCode.contains("B-LNCH1001")) {
                        timingListCode.add("B-LNCH1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck = true;
                break;




            case R.id.fragment_instruction_item_button3:
                if(dinnerButton.isSelected()){
                    dinnerButton.setBackgroundResource(R.drawable.edittext_background);
                    dinnerButton.setSelected(false);
                    for (int i=0; i<timingList.size(); i++){

                            switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                                case "ENGLISH":
                                    if (timingList.get(i).toString() == "Dinner") {
                                        timingList.remove(i);
                                    }
                                    break;
                                case "French":
                                    if (timingList.get(i).toString() == "dîner") {
                                        timingList.remove(i);
                                    }
                                    break;
                                case "한국어":
                                    if (timingList.get(i).toString() == "저녁") {
                                        timingList.remove(i);
                                    }
                                    break;
                                case "汉语":
                                    if (timingList.get(i).toString() == "晚饭") {
                                        timingList.remove(i);
                                    }
                                    break;
                            }

                    }
                    for (int i=0; i<timingListCode.size(); i++){
                        if (timingListCode.get(i).toString() == "B-DNNR1001"){
                            timingListCode.remove(i);
                        }
                    }
                }else{
                    dinnerButton.setBackgroundResource(R.drawable.edittext_background_green);
                    dinnerButton.setSelected(true);
                    if (!timingList.contains(dinnerButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                timingList.add("Dinner");
                                break;
                            case "French":
                                timingList.add("dîner");
                                break;
                            case "한국어":
                                timingList.add("저녁");
                                break;
                            case "汉语":
                                timingList.add("晚饭");
                                break;
                        }
                    }
                    if (!timingListCode.contains("B-DNNR1001")) {
                        timingListCode.add("B-DNNR1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck = true;
                break;




            case R.id.fragment_instruction_item_button4:
                if(eveningButton.isSelected()){
                    eveningButton.setBackgroundResource(R.drawable.edittext_background);
                    eveningButton.setSelected(false);
                    for (int i=0; i<timingList.size(); i++){
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                            case "ENGLISH":
                                if (timingList.get(i).toString() == "Before Sleep") {
                                    timingList.remove(i);
                                }
                                break;
                            case "French":
                                if (timingList.get(i).toString() == "avant le coucher") {
                                    timingList.remove(i);
                                }
                                break;
                            case "한국어":
                                if (timingList.get(i).toString() == "취침 전") {
                                    timingList.remove(i);
                                }
                                break;
                            case "汉语":
                                if (timingList.get(i).toString() == "就寝 之前") {
                                    timingList.remove(i);
                                }
                                break;
                        }
                    }
                    for (int i=0; i<timingListCode.size(); i++){
                        if (timingListCode.get(i).toString() == "B-BFSL1001"){
                            timingListCode.remove(i);
                        }
                    }
                }else{
                    eveningButton.setBackgroundResource(R.drawable.edittext_background_green);
                    eveningButton.setSelected(true);
                    if (!timingList.contains(eveningButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                timingList.add("Before Sleep");
                                break;
                            case "French":
                                timingList.add("avant le coucher");
                                break;
                            case "한국어":
                                timingList.add("취침 전");
                                break;
                            case "汉语":
                                timingList.add("就寝 之前");
                                break;
                        }
                    }

                    if (!timingListCode.contains("B-BFSL1001")) {
                        timingListCode.add("B-BFSL1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                timingCheck = true;
                break;




            case R.id.fragment_instruction_item_button5:
                if(sleepyButton.isSelected()){
                    sleepyButton.setBackgroundResource(R.drawable.edittext_background);
                    sleepyButton.setSelected(false);
                    for (int i=0; i<effectList.size(); i++){



                            switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                                case "ENGLISH":
                                    if (effectList.get(i).toString() == "None") {
                                        effectList.remove(i);
                                    }
                                    break;
                                case "French":
                                    if (effectList.get(i).toString() == "Aucun") {
                                        effectList.remove(i);
                                    }
                                    break;
                                case "한국어":
                                    if (effectList.get(i).toString() == "해당사항없음") {
                                        effectList.remove(i);
                                    }
                                    break;
                                case "汉语":
                                    if (effectList.get(i).toString() == "无") {
                                        effectList.remove(i);
                                    }
                                    break;
                            }

                    }

                    for (int i = 0 ; i < effecListCode.size(); i ++){
                        if (effecListCode.get(i).toString() == "NONE"){
                            effecListCode.remove(i);
                        }
                    }
                }else{
                    sleepyButton.setBackgroundResource(R.drawable.edittext_background_green);
                    sleepyButton.setSelected(true);
                    if (!effectList.contains(sleepyButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                effectList.add("None");
                                break;
                            case "French":
                                effectList.add("Aucun");
                                break;
                            case "한국어":
                                effectList.add("해당사항없음");
                                break;
                            case "汉语":
                                effectList.add("无");
                                break;
                        }
                    }

                    if (!effecListCode.contains("NONE")){
                        effecListCode.add("NONE");
                    }

                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                effectCheck = true;
                break;
            case R.id.fragment_instruction_item_button6:
                if(headacheButton.isSelected()){
                    headacheButton.setBackgroundResource(R.drawable.edittext_background);
                    headacheButton.setSelected(false);
                    for (int i=0; i<effectList.size(); i++){
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                            case "ENGLISH":
                                if (effectList.get(i).toString() == "Drowsiness") {
                                    effectList.remove(i);
                                }
                                break;
                            case "French":
                                if (effectList.get(i).toString() == "assoupissement") {
                                    effectList.remove(i);
                                }
                                break;
                            case "한국어":
                                if (effectList.get(i).toString() == "졸음") {
                                    effectList.remove(i);
                                }
                                break;
                            case "汉语":
                                if (effectList.get(i).toString() == "困意") {
                                    effectList.remove(i);
                                }
                                break;
                        }
                    }
                    for (int i = 0 ; i < effecListCode.size(); i ++){
                        if (effecListCode.get(i).toString() == "B-DRWS1001"){
                            effecListCode.remove(i);
                        }
                    }
                }else{
                    headacheButton.setBackgroundResource(R.drawable.edittext_background_green);
                    headacheButton.setSelected(true);
                    if (!effectList.contains(headacheButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                effectList.add("Drowsiness");
                                break;
                            case "French":
                                effectList.add("assoupissement");
                                break;
                            case "한국어":
                                effectList.add("졸음");
                                break;
                            case "汉语":
                                effectList.add("困意");
                                break;
                        }
                    }
                    if (!effecListCode.contains("B-DRWS1001")){
                        effecListCode.add("B-DRWS1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                effectCheck = true;
                break;
            case R.id.fragment_instruction_item_button7:
                if(upButton.isSelected()){
                    upButton.setBackgroundResource(R.drawable.edittext_background);
                    upButton.setSelected(false);
                    for (int i=0; i<effectList.size(); i++){
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                            case "ENGLISH":
                                if (effectList.get(i).toString() == "Blood pressure ↑") {
                                    effectList.remove(i);
                                }
                                break;
                            case "French":
                                if (effectList.get(i).toString() == "tension artérielle ↑") {
                                    effectList.remove(i);
                                }
                                break;
                            case "한국어":
                                if (effectList.get(i).toString() == "혈압 ↑") {
                                    effectList.remove(i);
                                }
                                break;
                            case "汉语":
                                if (effectList.get(i).toString() == "血压上升") {
                                    effectList.remove(i);
                                }
                                break;
                        }
                    }
                    for (int i = 0 ; i < effecListCode.size(); i ++){
                        if (effecListCode.get(i).toString() == "B-HBLD1001"){
                            effecListCode.remove(i);
                        }
                    }
                }else{
                    upButton.setBackgroundResource(R.drawable.edittext_background_green);
                    upButton.setSelected(true);
                    if (!effectList.contains(upButton.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                effectList.add("Blood pressure ↑");
                                break;
                            case "French":
                                effectList.add("tension artérielle ↑");
                                break;
                            case "한국어":
                                effectList.add("혈압 ↑");
                                break;
                            case "汉语":
                                effectList.add("血压上升");
                                break;
                        }
                    }
                    if (!effecListCode.contains("B-HBLD1001")){
                        effecListCode.add("B-HBLD1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                effectCheck = true;
                break;
            case R.id.fragment_instruction_item_button8:
                if(button4.isSelected()){
                    button4.setBackgroundResource(R.drawable.edittext_background);

                    button4.setSelected(false);
                    for (int i=0; i<effectList.size(); i++){
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)) {
                            case "ENGLISH":
                                if (effectList.get(i).toString() == "Diarrhea") {
                                    effectList.remove(i);
                                }
                                break;
                            case "French":
                                if (effectList.get(i).toString() == "Diarrhée") {
                                    effectList.remove(i);
                                }
                                break;
                            case "한국어":
                                if (effectList.get(i).toString() == "설사") {
                                    effectList.remove(i);
                                }
                                break;
                            case "汉语":
                                if (effectList.get(i).toString() == "腹泻") {
                                    effectList.remove(i);
                                }
                                break;
                        }
                    }
                    for (int i = 0 ; i < effecListCode.size(); i ++){
                        if (effecListCode.get(i).toString() == "B-VOMIT1001"){
                            effecListCode.remove(i);
                        }
                    }
                }else{
                    button4.setBackgroundResource(R.drawable.edittext_background_green);
                    button4.setSelected(true);
                    if (!effectList.contains(button4.getText().toString())) {
                        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
                            case "ENGLISH":
                                effectList.add("Diarrhea");
                                break;
                            case "French":
                                effectList.add("Diarrhée");
                                break;
                            case "한국어":
                                effectList.add("설사");
                                break;
                            case "汉语":
                                effectList.add("腹泻");
                                break;
                        }
                    }
                    if (!effecListCode.contains("B-VOMIT1001")){
                        effecListCode.add("B-VOMIT1001");
                    }
                }
                fragmentInstructionDataSetListener.setInstructionDataList(medicineName,dayStr,durationStr,period,timingList,timingStr,effectList,timingStrCode,medicineNameCode,timingListCode,effecListCode,periodCode,0);
                effectCheck = true;
                break;
            case R.id.fragment_instruction_addbutton:


                if (dayCheck == true && durationCheck == true && timingCheck == true && timingCheck2 == true && effectCheck == true){



                    fragmentInstructionDataAddListener.addInstructionDataList();
                    //medicineCount = getArguments().getInt("medicineCount");
                    //((MainActivity)getActivity()).replaceFragment(Main_Fragment_Instruction.newInstance(0,medicineCount));
                    dayCheck = false;
                    durationCheck = false;
                    timingCheck2 = false;
                    timingCheck = false;
                    effectCheck = false;
                }

                break;

        }
    }
}
