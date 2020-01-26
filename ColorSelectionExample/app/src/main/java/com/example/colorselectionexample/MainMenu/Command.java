package com.example.colorselectionexample.MainMenu;

public abstract class Command {

    protected MenuReciver reciver;

    protected Command(MenuReciver reciver ){
        this.reciver = reciver;
    }


    public abstract void execute(Object[] args);


}
