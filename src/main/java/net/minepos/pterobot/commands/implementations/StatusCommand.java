package net.minepos.pterobot.commands.implementations;

import com.google.inject.Inject;
import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.entities.objects.server.PowerState;
import com.stanjg.ptero4j.entities.panel.admin.Server;
import com.stanjg.ptero4j.entities.panel.admin.node.Allocation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.minepos.pterobot.Values;
import net.minepos.pterobot.commands.framework.Command;
import net.minepos.pterobot.utils.web.MCAPIUtils;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.stanjg.ptero4j.entities.objects.server.PowerState.ON;
import static com.stanjg.ptero4j.entities.objects.server.PowerState.STARTING;

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
        TextChannel channel = e.getChannel();
        String authorName = e.getAuthor().getName();

        if (args.length >= 1) {
            PteroAdminAPI adminAPI = values.getAdminAPI();
            List<Server> servers = adminAPI.getServersController().getServers(args[0]);

            if (servers.size() >= 1) {
                Server server = servers.get(0);
                PowerState state = values.getUserAPI().getServersController().getPowerState(server.getLongId());

                Optional<Allocation> allocation = adminAPI.getNodesController().getAllocations(server.getNodeId()).stream().filter(a -> a.getId() == server.getAllocationId()).findFirst();
                String ip = "null";

                if (allocation.isPresent()) {
                    Allocation all = allocation.get();
                    ip = all.getIp() + ":" + all.getPort();
                }

                channel.sendMessage(plainEmbed(authorName, state == ON || state == STARTING)
                        .setTitle("Status for " + server.getName())
                        .setDescription("The server is: " + state.getValue() + " and it is " +
                                (MCAPIUtils.isServerReachable(ip) ? "reachable." : "not reachable."))
                        .build()
                ).queue();
            } else {
                channel.sendMessage(error("That server couldn't be found on the panel.", authorName)).queue();
            }
        } else {
            channel.sendMessage(error("You didn't supply a server name", authorName)).queue();
        }
    }

    private MessageEmbed error(String error, String authorName) {
        return plainEmbed(authorName, false)
                .setTitle("Not found.")
                .setDescription(error)
                .build();
    }

    private EmbedBuilder plainEmbed(String authorName, boolean isGood) {
        return new EmbedBuilder()
                .setColor(isGood ? Color.GREEN : Color.RED)
                .setFooter("Requested by " + authorName, null)
                .setTimestamp(ZonedDateTime.now());
    }
}
