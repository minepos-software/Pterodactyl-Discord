package net.minepos.pterobot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.minepos.pterobot.objects.Command;

public class LinkCommand extends Command {

    public LinkCommand(){
        command = "link";
    }

    @Override
    public void onCommand(MessageReceivedEvent e) {
        e.getMessage().delete().queue();
        e.getTextChannel().sendMessage(new MessageBuilder().append("Testing!").build()).queue();
    }
}
