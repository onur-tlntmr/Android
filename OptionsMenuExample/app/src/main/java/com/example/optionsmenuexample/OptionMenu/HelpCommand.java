package com.example.optionsmenuexample.OptionMenu;

import android.content.Context;

class HelpCommand implements IMenuCommand { //Yardım komutunu çalıştıran sınıf

    private MenuReceiver mr = null;

    public HelpCommand(Context context){

        mr = new MenuReceiver(context);
    }


    @Override
    public void execute()
    {
        mr.HelpAction();
    }
}
