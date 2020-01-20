package com.example.optionsmenuexample.OptionMenu;

import android.content.Context;

class ExitCommand implements IMenuCommand { //Çıkış komutunu çalıştıran komut

    private MenuReceiver mr = null;

    public ExitCommand(Context context){

        mr = new MenuReceiver(context);
    }


    @Override
    public void execute() {

        mr.ExitAction();
    }
}
