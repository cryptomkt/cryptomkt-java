package com.cryptomarket.sdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeFlow {
    private static Calendar datetime = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void reset() {
        TimeFlow.datetime.set(2020, 12, 12, 12, 12, 12);
    }

    public static Boolean checkNextTimestamp(String isoDatetime) {
        Boolean goodFlow = true;
        Calendar datetime = Calendar.getInstance();
        try {
            datetime.setTime(TimeFlow.sdf.parse(isoDatetime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (TimeFlow.datetime.after(datetime)) goodFlow = false;
        TimeFlow.datetime = datetime;
        return goodFlow;
    }
}

