package net.minepos.pterobot.commands;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minepos.pterobot.commands.framework.Command;
import net.minepos.pterobot.file.GFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class CommandHandler extends ListenerAdapter {
    @Inject private GFile gFile;

    @Getter public List<Command> commands = new ArrayList<>();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String prefix = gFile.getFileConfiguration("config").getString("prefix");
        String content = event.getMessage().getContentRaw();

        if (content.toLowerCase().startsWith(prefix)) {
            for (Command c : commands) {
                if (content.startsWith(prefix + c.getCommand())) {
                    // Streams user role ids, then sees if the command's defined roles contains it.
                    if (c.getRoles() == null || event.getMember().getRoles().stream().map(Role::getIdLong).anyMatch(r -> Arrays.asList(c.getRoles()).contains(r))) {
                        c.run(event, split(content, c.getCommand()));
                        event.getMessage().delete().queue();
                    }
                }
            }
        }
    }

    private String[] split(String message, String command) {
        String[] split = message.replace(gFile.getFileConfiguration("config").getString("prefix") + command, "")
                .trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        split = split[0].isEmpty() ? new String[]{} : split;

        for (int i = 0; i < split.length; ++i) {
            split[i] = split[i].replaceFirst("\"", "").replaceFirst("(?s)\"(?!.*?\")", "");
        }

        return split;
    }
}
