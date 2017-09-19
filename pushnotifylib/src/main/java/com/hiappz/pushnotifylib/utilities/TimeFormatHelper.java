package com.hiappz.pushnotifylib.utilities;

import android.util.Log;

import com.hiappz.pushnotifylib.BuildConfig;
import com.hiappz.pushnotifylib.helpers.LogHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aj on 19/9/17.
 */

public class TimeFormatHelper {
    private final String TAG = "TimeFormatHelper";

    public TimeFormatHelper(){
        LogHelper.d(TAG, " -->> TimeFormatHelper -->>");
    }

    public String getAmPmTime(int hours, int minutes){
        String result = null;
        String hourString = null, minuteString = null, ampmString = null;

        ampmString = hours > 12 ? " PM" : hours == 12 ? " PM": " AM";
        hours = hours == 0 ? 12: hours == 12 ? 12: hours > 12 ? hours -12 : hours;

        hourString = hours < 10 ? "0"+hours : String.valueOf(hours);
        minuteString = minutes < 10 ? "0"+minutes : String.valueOf(minutes);

        result = hourString + ":" + minuteString + ampmString;
        return result;
    }

    public String get24HourFormattedTime(String selectedTime){
        String resultString = null;
        int hours = 0, minutes = 0;
        String hourString = null;
        String minuteString = null;
        String[] selectedTimeArr = null;

        selectedTimeArr = selectedTime.substring(0, 6).split(":");
        hours = Integer.valueOf(selectedTimeArr[0].trim());
        minutes = Integer.valueOf(selectedTimeArr[1].trim());
        hours = hours == 12 ? 12 : hours + 12;
        hourString = hours < 10 ? "0" + hours : String.valueOf(hours);
        minuteString = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        resultString = hourString + ":" + minuteString;
        return resultString;
    }

    public String get12HourFormattedTime(String selectedTime){
        String resultString = null;
        int hours = 0, minutes = 0;
        String hourString = null;
        String minuteString = null;
        String[] selectedTimeArr = null;

        selectedTimeArr = selectedTime.substring(0, 6).split(":");
        hours = Integer.valueOf(selectedTimeArr[0].trim());
        minutes = Integer.valueOf(selectedTimeArr[1].trim());
//        hours = hours == 12 ? 12 : hours + 12;
        hourString = hours < 10 ? "0" + hours : String.valueOf(hours);
        minuteString = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        resultString = hourString + ":" + minuteString;
        return resultString;
    }

    public String getFormattedTimeSlot(String timeSlotStr){
        byte hours = 0;
        byte minutes = 0;
        String[] timeArr = null;
        String AM_PM_String = null;
        String hourString = null;
        String minuteString = null;
        String resultTimeString = null;

        timeArr = timeSlotStr.split(":");
        hours = Byte.valueOf(timeArr[0]);
        minutes = Byte.valueOf(timeArr[1]);

        if (hours > 12){
            hours = (byte) (hours - 12);
            AM_PM_String = " PM";
        }else {
            AM_PM_String = hours == 12 ? " PM" : " AM";
            hours = hours == 0 ? 12 : hours;
        }

        hourString = hours < 10 ? "0" + hours : String.valueOf(hours);
        minuteString = minutes < 10 ? "0" + minutes : String.valueOf(minutes);

//        resultTimeString = hourString.equalsIgnoreCase("00") ? hourString  + AM_PM_String : hourString + ":" + minuteString + AM_PM_String;
        resultTimeString = hourString + ":" + minuteString + AM_PM_String;

        return resultTimeString;
    }

    public String convertDateFormmat(String dateString, String givenFormat, String requiredFormat){
        String result = "";
        SimpleDateFormat simpleDateFormat1 = null; //"2017-02-21" //"yyyy-MM-dd"
        SimpleDateFormat simpleDateFormat2 = null;
        Date tempDate = null;

        try{
            simpleDateFormat1 = new SimpleDateFormat(givenFormat);
            simpleDateFormat2 = new SimpleDateFormat(requiredFormat);

            tempDate = simpleDateFormat1.parse(dateString);
            result = simpleDateFormat2.format(tempDate);
        }catch (ParseException e){
            Log.e(TAG, "convertDateFormmat -->> ParseException -->> "+e.getMessage());
            if (BuildConfig.DEBUG){
                e.printStackTrace();
            }
        }catch (NullPointerException e){
            Log.e(TAG, "convertDateFormmat -->> NullPointerException -->> "+e.getMessage());
            if (BuildConfig.DEBUG){
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getTwoDigitsTime(int hours, int minutes){
        String formattedTime = null;
        String hourString = null;
        String minuteString = null;

        hourString = hours < 10 ? "0"+hours : String.valueOf(hours);
        minuteString = minutes < 10 ? "0"+minutes : String.valueOf(minutes);
        formattedTime = hourString + ":" + minuteString;
        return formattedTime;
    }
}
