package net.minepos.pterobot.objects;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    public String command;
    public Boolean adminOnly = false;
    public abstract void onCommand(MessageReceivedEvent e);

}
