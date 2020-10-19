package com.medicto.medicto.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class InstructionModel implements Parcelable {
    String day;
    String duration;
    String period;

    protected InstructionModel(Parcel in) {
        day = in.readString();
        duration = in.readString();
        period = in.readString();
        timingStr = in.readString();
        medicineName = in.readString();
        timingList = in.createStringArrayList();
        effectList = in.createStringArrayList();
    }

    public static final Creator<InstructionModel> CREATOR = new Creator<InstructionModel>() {
        @Override
        public InstructionModel createFromParcel(Parcel in) {
            return new InstructionModel(in);
        }

        @Override
        public InstructionModel[] newArray(int size) {
            return new InstructionModel[size];
        }
    };

    public String getDay() {
        return day;
    }

    public String getDuration() {
        return duration;
    }

    public String getPeriod() {
        return period;
    }

    public String getTimingStr() {
        return timingStr;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public ArrayList<String> getTimingList() {
        return timingList;
    }

    public ArrayList<String> getEffectList() {
        return effectList;
    }

    String timingStr;
    String medicineName;


    ArrayList<String> timingList;
    ArrayList<String> effectList;


    public InstructionModel(String medicineName,String day,String duration,String timingStr,ArrayList<String> timingList,String timingStr2,ArrayList<String> effectList){
        this.medicineName = medicineName;
        this.day = day;
        this.duration = duration;
        this.period = timingStr;
        this.timingList = timingList;
        this.timingStr = timingStr2;
        this.effectList = effectList;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(duration);
        dest.writeString(period);
        dest.writeString(timingStr);
        dest.writeString(medicineName);
        dest.writeStringList(timingList);
        dest.writeStringList(effectList);
    }
}
