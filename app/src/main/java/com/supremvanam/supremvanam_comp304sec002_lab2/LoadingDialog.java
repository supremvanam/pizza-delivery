package com.supremvanam.supremvanam_comp304sec002_lab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void startLoading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading_spinner, null));
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void stopLoading() {
        alertDialog.dismiss();
    }

}
