package net.minepos.pterobot.commands.implementations;

import com.google.inject.Inject;
import com.stanjg.ptero4j.entities.objects.server.PowerState;
import com.stanjg.ptero4j.entities.panel.admin.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.minepos.pterobot.Values;
import net.minepos.pterobot.commands.framework.Command;

import java.awt.*;
import java.net.Socket;
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
                EmbedBuilder embed = null;
                if(state.equals(PowerState.ON)||state.equals(PowerState.STARTING)){
                    embed = getEmbedBuilder("Status for "+server.getName(), "The server is: "+state.getValue(),e.getAuthor(),true);
                    if(state.equals(PowerState.ON)){
                        embed.appendDescription(System.lineSeparator()+server.getAllocationId());
                    }
                }else{
                    embed = getEmbedBuilder("Status for "+server.getName(), "The server is: "+state.getValue(),e.getAuthor(),false);
                }

                if(embed != null){
                    e.getChannel().sendMessage(embed.build()).queue();
                }

            } else {
                MessageEmbed embed = getEmbed("Not Found.", "That server couldn't be found on the panel.",e.getAuthor(),false);
                e.getChannel().sendMessage(embed).queue();
            }
        } else {
            MessageEmbed embed = getEmbed("Not Found.", "You didn't supply a server name.",e.getAuthor(),false);
            e.getChannel().sendMessage(embed).queue();
        }
    }
    public EmbedBuilder getEmbedBuilder(String title, String message, User author, boolean isGood){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title);
        builder.setDescription(message);
        if(!isGood){
            builder.setColor(Color.RED);

        }else{
            builder.setColor(Color.GREEN);
        }
        builder.setFooter("Requested by "+author.getName(),null);
        return builder;
    }
    public MessageEmbed getEmbed(String title, String message, User author, boolean isGood){
        return getEmbedBuilder(title, message, author, isGood).build();
    }
    private boolean isRemotePortInUse(String hostName, int portNumber) {
        try {
            // Socket try to open a REMOTE port
            new Socket(hostName, portNumber).close();
            // remote port can be opened, this is a listening port on remote machine
            // this port is in use on the remote machine !
            return true;
        } catch(Exception e) {
            // remote port is closed, nothing is running on
            return false;
        }
    }
}
