package com.example.optionsmenuexample.OptionMenu;

import android.content.Context;

class FileCommand implements IMenuCommand { //Dosya komudunu çalıştıran sınıf

    private MenuReceiver mr = null;

    public FileCommand(Context context){
        mr = new MenuReceiver(context);
    }


    @Override
    public void execute() {
        mr.FileAction();
    }
}
