package net.minepos.pterobot.registerables.implementations;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.minepos.pterobot.commands.CommandHandler;
import net.minepos.pterobot.commands.framework.Command;
import net.minepos.pterobot.registerables.Registerable;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CommandsRegisterable extends Registerable {
    @Inject private Reflections reflections;
    @Inject private Injector injector;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        reflections.getSubTypesOf(Command.class).stream().map(injector::getInstance).forEach(commandHandler.getCommands()::add);
    }
}
