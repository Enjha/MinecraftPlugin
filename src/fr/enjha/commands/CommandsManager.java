package fr.enjha.commands;

import fr.enjha.Main;

public class CommandsManager {

    private final Main main;

    public CommandsManager(Main main){
        this.main = main;
    }

    public void init(){
        this.main.getCommand("game").setExecutor(new GameCommand(this.main));
    }
}
