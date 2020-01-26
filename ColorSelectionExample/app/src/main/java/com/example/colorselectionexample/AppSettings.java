package com.example.colorselectionexample;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSettings {

    private Context context;
    private SharedPreferences sp;

    public AppSettings(Context context, SharedPreferences sp){
        this.context = context;
        this.sp = sp;
    }

    public void Seriliaze(int r, int g, int b) {

        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("R",r);
        editor.putInt("G",g);
        editor.putInt("B",b);

        editor.commit();
    }

    public void Deseriliaze() {
        ((MainActivity)context).Paint(sp.getInt("R",0),sp.getInt("G",0),sp.getInt("B",0));
    }
}
