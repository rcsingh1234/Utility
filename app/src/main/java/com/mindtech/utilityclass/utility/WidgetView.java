package com.mindtech.utilityclass.utility;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by RamChandra Singh on 27,September,2016
 * MindTech Solutions Pvt Ltd,
 * Mumbai , India.
 */
public class WidgetView {
    private Context con;

    public WidgetView(Context context) {
        con = context;
    }

    public void toast(String str) {
        Toast ts = Toast.makeText(con, str, Toast.LENGTH_SHORT);
        ts.setGravity(Gravity.CENTER, 0, 0);
        ts.show();
    }

    public String replaceNullZero(String str) {
        if (str == null) {
            return "0";
        }
        if (str.equalsIgnoreCase("null")) {
            return "0";
        }

        if (str.equalsIgnoreCase("")) {
            return "0";
        }
        return str;
    }

    public String replaceNull(String str) {
        if (str == null) {
            return "";
        }
        if (str.equalsIgnoreCase("null")) {
            return "";
        }

        if (str.equalsIgnoreCase("")) {
            return "";
        }
        return str;
    }


    public String M1D(String str){

        if (str.startsWith(".")) {
            str = "0" + str;
        }

        return str;
    }

    public String M2D(String str){

        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.DOWN);

      String str1 = String.valueOf(df.format(Double.valueOf(str)));

        return str1;
    }


    public String Multi(String str1, String str2){

        String Data="0";

        try {
            if(str1==null){
                str1="0";
            }
            if(str2==null){
                str2="0";
            }

            if(str1.trim().equalsIgnoreCase("") | str1.trim().equalsIgnoreCase("null")){
                str1="0";
            }
            if(str2.trim().equalsIgnoreCase("") | str2.trim().equalsIgnoreCase("null")){
                str2="0";
            }

            double data  = Double.valueOf(str1)* Double.valueOf(str2);
            DecimalFormat df = new DecimalFormat("#.000");
            df.setRoundingMode(RoundingMode.DOWN);
            Data=   df.format(Double.parseDouble(String.valueOf(data)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return Data;
    }


    public String M4D(String str){

        if (str.length() == 1) {
            str = "000" + str;
        } else if (str.length() == 2) {
            str = "00" + str;
        } else if (str.length() == 3) {
            str = "0" + str;
        }else if(str.length()>4){
            str = str.substring(0,4);
        }

        return str;
    }

    public String M6D(String str){

        if (str.length() == 1) {
            str = "00000" + str;
        } else if (str.length() == 2) {
            str = "0000" + str;
        } else if (str.length() == 3) {
            str = "000" + str;
        }else if (str.length() == 4) {
            str = "00" + str;
        }else if (str.length() == 5) {
            str = "0" + str;
        }else if(str.length()>6){
            str = str.substring(0,6);
        }

        return str;
    }



}
