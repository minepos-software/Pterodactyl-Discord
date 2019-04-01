package net.minepos.pterobot.commands.implementations;

import com.google.inject.Inject;
import com.stanjg.ptero4j.entities.panel.admin.Server;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.minepos.pterobot.Values;
import net.minepos.pterobot.commands.framework.Command;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class StatusCommand extends Command {
    @Inject private Values values;

    public StatusCommand() {
        super("status", "Get the status of a server by name", 322698583377707008L);
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, String[] args) {
        if (args.length >= 1) {
            List<Server> servers = values.getAdminAPI().getServersController().getServers(args[0]);

            if (servers.size() >= 1) {
                Server server = servers.get(0);

                e.getChannel().sendMessage(
                        server.getName() + " is currently " + values.getUserAPI().getServersController().getPowerState(server.getLongId()).getValue()
                ).queue();
            } else {
                e.getChannel().sendMessage("That server couldn't be found on the panel.").queue();
            }
        } else {
            e.getChannel().sendMessage("You didn't supply a server name").queue();
        }
    }
}
