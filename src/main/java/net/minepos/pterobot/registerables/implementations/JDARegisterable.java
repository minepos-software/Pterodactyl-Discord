package net.minepos.pterobot.registerables.implementations;

import com.google.inject.Inject;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.minepos.pterobot.commands.CommandHandler;
import net.minepos.pterobot.file.GFile;
import net.minepos.pterobot.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class JDARegisterable extends Registerable {
    @Inject private GFile gFile;
    @Inject private CommandHandler commandHandler;

    @Override
    protected void execute() {
        try {
            addValue("JDA", new JDABuilder(gFile.getFileConfiguration("config").getString("token"))
                    .setActivity(Activity.playing("test"))
                    .addEventListeners(commandHandler)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
