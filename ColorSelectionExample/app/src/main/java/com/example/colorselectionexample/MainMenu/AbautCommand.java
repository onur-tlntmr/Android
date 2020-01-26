package com.example.colorselectionexample.MainMenu;

class AbautCommand extends Command {

    public AbautCommand(MenuReciver mr) {
        super(mr);
    }

    @Override
    public void execute(Object[] args) {
        super.reciver.AboutAction();
    }


}
