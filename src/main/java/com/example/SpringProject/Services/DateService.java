package com.example.SpringProject.Services;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {

    public boolean isValid(int day, int month, int year) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if (day > 31) return false;
        else if (month <= 0 && month >= 13) return false;
        String dateStr = convert(day, month, year);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException r) {
            return false;
        }
        return true;
    }

    public String convert(int d, int m, int y) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String ds = Integer.toString(d);
        String ms = Integer.toString(m);
        String ys = Integer.toString(y);
        if (ds.length() == 1) ds = "0" + ds;
        if (ms.length() == 1) ms = "0" + ms;
        String dateStr = ms + "/" + ds + "/" + ys;
        return dateStr;
    }

    public int compare(String d1, String d2) throws ParseException {
        SimpleDateFormat sdfo = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = sdfo.parse(d1);
        Date date2 = sdfo.parse(d2);
        return date1.compareTo(date2);
    }
}
