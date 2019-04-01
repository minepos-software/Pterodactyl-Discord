package net.minepos.pterobot.commands.implementations;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.minepos.pterobot.commands.framework.Command;

public class LinkCommand extends Command {
    public LinkCommand() {
        super("link");
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, String[] args) {
        e.getMessage().delete().queue();
        e.getChannel().sendMessage("Testing").queue();
    }
}
