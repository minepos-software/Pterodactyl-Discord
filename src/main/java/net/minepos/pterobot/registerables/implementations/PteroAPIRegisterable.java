package net.minepos.pterobot.registerables.implementations;

import com.google.inject.Inject;
import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.PteroUserAPI;
import net.minepos.pterobot.config.framework.FileConfiguration;
import net.minepos.pterobot.file.GFile;
import net.minepos.pterobot.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PteroAPIRegisterable extends Registerable {
    @Inject private GFile gFile;

    @Override
    protected void execute() {
        final FileConfiguration config = gFile.getFileConfiguration("config");
        final String url = config.getString("pterodactyl.url");

        addValue("admin-api",
                new PteroAdminAPI(url, config.getString("pterodactyl.admin-token"))
        );

        addValue("user-api",
                new PteroUserAPI(url, config.getString("pterodactyl.user-token"))
        );
    }
}
