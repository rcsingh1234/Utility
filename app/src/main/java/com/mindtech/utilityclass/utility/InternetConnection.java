package com.mindtech.utilityclass.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RamChandra Singh on 27,September,2016
 * MindTech Solutions Pvt Ltd,
 * Mumbai , India.
 */
public class InternetConnection {
    Context con;
    private static InternetConnection ic = null;

    public static synchronized InternetConnection getInstanceInternet(Context con) {
        if (ic == null) {
            ic = new InternetConnection(con);
        }
        return ic;
    }

    private InternetConnection(Context con) {
        this.con = con;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
