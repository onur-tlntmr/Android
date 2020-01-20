package com.example.optionsmenuexample.OptionMenu;

import android.content.Context;

import com.example.myapplication.R;

import java.util.HashMap;

public class MenuInvoker { //Komutları tutan ve çalıştırmasını sağlayan sınıf

    private HashMap<Integer,IMenuCommand> commands;

    public MenuInvoker(Context context) {

        commands = new HashMap<>();

        commands.put(R.id.mnuFile,new FileCommand(context));
        commands.put(R.id.mnuHelp,new HelpCommand(context));
        commands.put(R.id.mnuExit,new ExitCommand(context));
    }

    public void invoke(int id){
        commands.get(id).execute();
    }

}
