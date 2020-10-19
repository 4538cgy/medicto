package com.medicto.medicto.ListView;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private String titleStr;
    private String code;
    private Drawable drawable;

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public void setTitleStr(String titleStr){
        this.titleStr = titleStr;
    }
    public String getTitleStr(){
        return this.titleStr;
    }
}
