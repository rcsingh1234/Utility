package com.mindtech.utilityclass.utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.PopupWindow;


/**
 * Created by RamChandra Singh on 27,September,2016
 * MindTech Solutions Pvt Ltd,
 * Mumbai , India.
 */
public class AlertDialogBox {
    private static final String VALID = "OK";
    Boolean values = false;
    ;
    PopupWindow changeSortPopUp;
    private Context con;

    public AlertDialogBox(Context context) {
        con = context;

    }


    public void showAlertDialog(String title, String message
    ) {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(con).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);


        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton(VALID, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showAlertDialogFinish(String title, String message
    ) {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(con).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);


        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setButton(VALID, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Activity) con).finish();
            }
        });
        alertDialog.show();
    }


    public void showError(final String str) {
        ((Activity) con).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showAlertDialog("Error", str);
            }
        });
    }


}
