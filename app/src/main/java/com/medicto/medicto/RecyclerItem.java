package com.medicto.medicto;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class RecyclerItem implements Parcelable {

    private Drawable drawable;
    private String title;
    private int viewType;


    String medicineCode,timingStrCode,dayCode;
    ArrayList<String> timingListCode,effectListCode;

    public RecyclerItem(String timingStrCode,String dayCode,String medicineCode,ArrayList<String> timingListCode,ArrayList<String> effectListCode){
        this.medicineCode = medicineCode;
        this.timingStrCode = timingStrCode;
        this.dayCode = dayCode;
        this.timingListCode = timingListCode;
        this.effectListCode = effectListCode;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getTimingStrCode() {
        return timingStrCode;
    }

    public void setTimingStrCode(String timingStrCode) {
        this.timingStrCode = timingStrCode;
    }

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }

    public ArrayList<String> getTimingListCode() {
        return timingListCode;
    }

    public void setTimingListCode(ArrayList<String> timingListCode) {
        this.timingListCode = timingListCode;
    }

    public ArrayList<String> getEffectListCode() {
        return effectListCode;
    }

    public void setEffectListCode(ArrayList<String> effectListCode) {
        this.effectListCode = effectListCode;
    }

    private String day,duration,timing,timing2;
    ArrayList<String> list;
    ArrayList<String> effectList;

    private String initialCode;

    private String medicineName,medicineCount,medicineDuration,period,time;

    public String getDay() {
        return this.day;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getTiming() {
        return this.timing;
    }

    public String getTiming2() {
        return this.timing2;
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public ArrayList<String> getEffectList(){
        return this.effectList;
    }


    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineCount() {
        return medicineCount;
    }

    public void setMedicineCount(String medicineCount) {
        this.medicineCount = medicineCount;
    }

    public String getMedicineDuration() {
        return medicineDuration;
    }

    public void setMedicineDuration(String medicineDuration) {
        this.medicineDuration = medicineDuration;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public RecyclerItem(String medicineName,String day, String duration, String timing, String timing2, ArrayList<String> list, ArrayList<String> effectList,String time, int viewType)
    {
        this.medicineName = medicineName;
        this.day = day;
        this.duration = duration;
        this.timing = timing;
        this.timing2 = timing2;
        this.list = list;
        this.effectList = effectList;
        this.time = time;
        this.viewType = viewType;
    }

    public String getInitialCode() {
        return initialCode;
    }

    public void setInitialCode(String initialCode) {
        this.initialCode = initialCode;
    }

    public RecyclerItem(String medicineName, String medicineCount, String medicineDuration, String period, String time, int viewType){
        this.medicineName = medicineName;
        this.medicineCount = medicineCount;
        this.medicineDuration = medicineDuration;
        this.period = period;
        this.time = time;

        this.viewType = viewType;

    }

    public RecyclerItem(Drawable drawable,String title,String initialCode,int viewType){

        this.drawable = drawable;
        this.title = title;
        this.initialCode = initialCode;
        this.viewType = viewType;

    }

    public RecyclerItem(String title,int viewType){
        this.title = title;
        this.viewType = viewType;
    }

    protected RecyclerItem(Parcel in) {
        title = in.readString();
        viewType = in.readInt();
    }



    public static final Creator<RecyclerItem> CREATOR = new Creator<RecyclerItem>() {
        @Override
        public RecyclerItem createFromParcel(Parcel in) {
            return new RecyclerItem(in);
        }

        @Override
        public RecyclerItem[] newArray(int size) {
            return new RecyclerItem[size];
        }
    };

    public Drawable getDrawable(){
        return this.drawable;
    }
    public String getTitle(){
        return  this.title;
    }
    public int getViewType(){
        return this.viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(viewType);
    }

    @Override
    public boolean equals(Object o) {

        /*
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecyclerItem that = (RecyclerItem) o;
        return viewType == that.viewType &&
                Objects.equals(drawable, that.drawable) &&
                Objects.equals(title, that.title);

         */
        if (o instanceof RecyclerItem){
            RecyclerItem recyclerItem = (RecyclerItem) o;
            if(this.title.equals(recyclerItem.getTitle()) && this.drawable.equals(recyclerItem.getDrawable())){
                return true;
            }
        }
        return  false;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(drawable, title, viewType);

        return (this.drawable.hashCode()+this.title.hashCode());
    }
}
