package com.example.optionsmenuexample.OptionMenu;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.myapplication.R;

public class MenuHelper {


    private Context context;

    private MenuInvoker menuInvoker;

    public MenuHelper(Context context){
        this.context = context;
        menuInvoker = new MenuInvoker(context);
    }

    public boolean Build(Menu menu){ //menu nesnesini olutşturan yardımcı metod

        MenuInflater mi = ((Activity)context).getMenuInflater(); //menu oluşturma servisi örneği
        mi.inflate(R.menu.menu,menu); //menu oluşturuluyor
        return true; // event handling için true dönderiliyor
    }


    public boolean ItemSelect(int id){ // menu itemi secilince calistirilacak metot
        menuInvoker.invoke(id); //islem yapılıyor
        return true;// event handling için true dönderiliyor
    }

}
