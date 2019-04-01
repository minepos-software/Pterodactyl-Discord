package net.minepos.pterobot.commands.framework;

import lombok.Getter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Command {
    @Getter private final String command;
    @Getter private final String description;
    @Getter private final Long[] roles;

    protected Command(final String command) {
        this(command, "null");
    }

    protected Command(final String command, final String description, final Long... roles) {
        this.command = command;
        this.description = description == null ? "null" : description;
        this.roles = roles;
    }

    protected abstract void execute(GuildMessageReceivedEvent e, String[] args);

    public void run(GuildMessageReceivedEvent e, String[] args) {
        execute(e, args);
    }
}
