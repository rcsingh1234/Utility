package com.mindtech.utilityclass.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;


/**
 * Created by Ramchandra Singh on 08-02-2017.
 */
public class ProgressBarClass {



    private int progressBarMax = 100;
    private int progressCurrent = 0;
    private String updateMessage = "Please Wait..";
    private String titleMessage = "Downloading..";
    private ProgressDialog progressDialog;
   private Context con;
    Boolean cancelable =true;
    //        public static ProgressBarClass getInstance(Context context) {
//        if (mInstance == null) {
//
//            synchronized (ProgressBarClass.class) {
//                if (mInstance == null) {
//                    mInstance = new ProgressBarClass(context);
//                }
//            }
//        }
//
//        return mInstance;
//    }
    public Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {

                case 1:
                    progressDialog = new ProgressDialog(con);
                    progressDialog.setTitle("Downloading ..");
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setCancelable(true);
                    progressDialog.setMax(progressBarMax);
                    progressDialog.setProgress(progressCurrent);
                    progressDialog.show();

                    break;
                case 2:

                    progressDialog.setTitle(titleMessage);
                    progressDialog.setMessage(updateMessage);
                    progressDialog.setMax(progressBarMax);
                    progressDialog.setProgress(progressCurrent);
                    progressDialog.setCancelable(cancelable);

                    break;


                case 3:
                    progressDialog.dismiss();
                    break;
                case 4:
                    //UIToastMessage.show(con, updateMessage);
                    break;

            }
            return false;
        }
    });

    public ProgressBarClass(Context context) {
        con = context;
    }

    public void setProgressBarMax(int progressBarMax) {
        this.progressBarMax = progressBarMax;
    }

    public void setProgressCurrent(int progressCurrent) {
        this.progressCurrent = progressCurrent;
    }
    public void setCancelable(boolean booleanValues) {
        this.cancelable = booleanValues;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }
}
