package com.medicto.medicto.Interface;

import java.util.ArrayList;

public interface FragmentInstructionDataSetListener {
    void setInstructionDataList(String medicineName, String count, String duration, String timing, ArrayList<String> timingList,String timing2,ArrayList<String> effectList,String timingStrCode,String medicineNameCode,ArrayList<String> timingListCode,ArrayList<String> effetListCode,String periodCode,int DataType);
}
