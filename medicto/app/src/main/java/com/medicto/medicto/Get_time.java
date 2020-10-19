package com.medicto.medicto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Get_time {
    public String getTime() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        String getTime = simpleDateFormat.format(mDate);

        return getTime;
    }
}
