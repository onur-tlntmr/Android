package com.example.colorselectionexample.MainMenu;

import com.example.colorselectionexample.R;

import java.util.HashMap;

public class MenuInvoker {

    private HashMap<Integer,Command> commands;

    private MenuReciver mr;

    public MenuInvoker(MenuReciver mr){
        this.mr = mr;

        commands = new HashMap<>();

        commands.put(R.id.mnuLoad,new LoadCommand(mr));
        commands.put(R.id.mnuSave,new SaveCommand(mr));
        commands.put(R.id.mnuAbout,new AbautCommand(mr));
    }


    public void Invoke(int id,Object[] args){
        commands.get(id).execute(args);
    }

}
