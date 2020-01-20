package com.example.optionsmenuexample.OptionMenu;

import android.content.Context;
import android.widget.Toast;

import com.example.optionsmenuexample.MainActivity;

class MenuReceiver { //Her komut için gerekli aksiyonların mevcut olduğu sınıf

    Context context = null;

    public MenuReceiver(Context context) {
        this.context = context;
    }

    public void FileAction() {
        Toast.makeText(context,"Dosya Menusu Açıldı",Toast.LENGTH_SHORT).show();
    }

    public void HelpAction() {
        Toast.makeText(context,"Yardım Menusu Açıldı",Toast.LENGTH_SHORT).show();


    }

    public void ExitAction() {
        ((MainActivity)context).finish();
    }
}
