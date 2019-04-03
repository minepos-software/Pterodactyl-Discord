package net.minepos.pterobot.commands.implementations;

import com.google.inject.Inject;
import com.stanjg.ptero4j.entities.objects.server.PowerState;
import com.stanjg.ptero4j.entities.panel.admin.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.minepos.pterobot.Values;
import net.minepos.pterobot.commands.framework.Command;

import java.awt.*;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class StatusCommand extends Command {
    @Inject private Values values;

    public StatusCommand() {
        super("status", "Get the status of a server by name", 322698583377707008L, 322698695458160640L, 531612139748851712L);
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, String[] args) {
        if (args.length >= 1) {
            List<Server> servers = values.getAdminAPI().getServersController().getServers(args[0]);

            if (servers.size() >= 1) {
                Server server = servers.get(0);
                PowerState state = values.getUserAPI().getServersController().getPowerState(server.getLongId());
                MessageEmbed embed = null;
                if(state.equals(PowerState.ON)||state.equals(PowerState.STARTING)){
                    embed = getEmbed("Status for "+server.getName(), "The server is: "+state.getValue(),e.getAuthor(),Color.GREEN);
                }else{
                    embed = getEmbed("Status for "+server.getName(), "The server is: "+state.getValue(),e.getAuthor(),Color.RED);
                }

                if(embed != null){
                    e.getChannel().sendMessage(embed).queue();
                }

            } else {
                MessageEmbed embed = getEmbed("Not Found.", "That server couldn't be found on the panel.",e.getAuthor(),Color.RED);
                e.getChannel().sendMessage(embed).queue();
            }
        } else {
            MessageEmbed embed = getEmbed("Not Found.", "You didn't supply a server name.",e.getAuthor(),Color.RED);
            e.getChannel().sendMessage(embed).queue();
        }
    }

    public MessageEmbed getEmbed(String title, String message, User author, Color color){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(message);
        builder.setColor(color);
        builder.setFooter("Requested by "+author.getName(),null);
        return builder.build();
    }
}
