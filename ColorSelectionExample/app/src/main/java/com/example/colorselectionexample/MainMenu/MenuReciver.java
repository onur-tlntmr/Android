package com.example.colorselectionexample.MainMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import com.example.colorselectionexample.AppSettings;

class MenuReciver {

    private Context context;
    private SharedPreferences sharedPreferences;
    private AppSettings appSettings;


    public MenuReciver(Context context, SharedPreferences sh) {
        this.context = context;
        this.sharedPreferences = sh;
        appSettings = new AppSettings(context, sharedPreferences);
    }


    public void SaveAction(int r, int g, int b) {
        appSettings.Seriliaze(r, g, b);
    }


    public void LoadAction() {
        appSettings.Deseriliaze();
    }

    public void AboutAction() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Author by Onur Talantimur\nDate: 26.01.2020");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
