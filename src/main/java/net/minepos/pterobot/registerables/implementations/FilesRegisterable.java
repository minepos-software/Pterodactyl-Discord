package net.minepos.pterobot.registerables.implementations;

import com.google.inject.Inject;
import net.minepos.pterobot.file.GFile;
import net.minepos.pterobot.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class FilesRegisterable extends Registerable {
    @Inject private GFile gFile;

    @Override
    protected void execute() {
        gFile.clearMap();
        gFile.make("config", "./config.json", "/config.json");
    }
}
