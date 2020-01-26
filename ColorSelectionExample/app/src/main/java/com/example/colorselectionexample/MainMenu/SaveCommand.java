package com.example.colorselectionexample.MainMenu;

public class SaveCommand extends Command {

    protected SaveCommand(MenuReciver reciver) {
        super(reciver);
    }

    @Override
    public void execute(Object[] args) {
        super.reciver.SaveAction(Integer.parseInt(args[0].toString()),Integer.parseInt(args[1].toString()),Integer.parseInt(args[2].toString()));
    }
}
