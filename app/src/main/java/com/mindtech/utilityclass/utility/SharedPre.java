package com.mindtech.utilityclass.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by RamChandra Singh on 27,September,2016
 * MindTech Solutions Pvt Ltd,
 * Mumbai , India.
 */
public class SharedPre {
    SharedPreferences rSharedpreference;
    SharedPreferences.Editor rEditor;

    public SharedPre(Context context){

        rSharedpreference = PreferenceManager.getDefaultSharedPreferences(context);
        rEditor = rSharedpreference.edit();
    }

    public void setValues(String name, String values ){
        rEditor.putString(name,values);
        rEditor.commit();
    }
    public void setValues(String name, int values ){
        rEditor.putInt(name,values);rEditor.commit();
    }
    public void close(){ rEditor.commit();}

    public String getStrValues(String key){
        return rSharedpreference.getString(key,null);
    }

    public int getIntValues(String key){
        return rSharedpreference.getInt(key,0);
    }
}


// LoginStatus for login status.