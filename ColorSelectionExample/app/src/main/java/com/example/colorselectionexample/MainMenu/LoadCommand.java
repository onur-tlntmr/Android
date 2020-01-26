package com.example.colorselectionexample.MainMenu;


public class LoadCommand extends Command {


    protected LoadCommand(MenuReciver reciver) {
        super(reciver);
    }

    @Override
    public void execute(Object[] args) {
        super.reciver.LoadAction();
    }
}
