package com.example.colorselectionexample.MainMenu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.colorselectionexample.R;

public class MenuHelper {

    private Context context;
    private MenuInvoker menuInvoker;

    public MenuHelper(Context context, SharedPreferences sp){
        this.context = context;
        menuInvoker = new MenuInvoker(new MenuReciver(context,sp));
    }


    public boolean Build(Menu menu){
        MenuInflater mi = ((Activity)context).getMenuInflater();
        mi.inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean ItemSelect(int id,Object[] args){
        menuInvoker.Invoke(id,args);
        return true;
    }

}
