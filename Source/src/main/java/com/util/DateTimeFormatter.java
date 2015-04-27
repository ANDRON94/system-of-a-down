package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 27.04.15.
 */
public class DateTimeFormatter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static Date parseStringToDate(String date){
        try {
          return  dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
