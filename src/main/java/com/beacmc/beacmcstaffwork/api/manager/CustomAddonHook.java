package com.beacmc.beacmcstaffwork.api.manager;

public abstract class CustomAddonHook extends Command {

    private String command;
    private String message;

    public CustomAddonHook(String command, String message) {
        assert command != null;
        assert message != null;
        this.command = command;
        this.message = message;
    }

    public String getCommand() {
        return command;
    }
    public String getMessage() {
        return message;
    }
}
