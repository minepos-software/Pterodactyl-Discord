package net.minepos.pterobot.file;

import com.google.inject.Singleton;
import net.minepos.pterobot.PteroBotBootstrap;
import net.minepos.pterobot.config.FileConfigurationFactory;
import net.minepos.pterobot.config.framework.FileConfiguration;
import net.minepos.pterobot.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GFile {
    private final Map<String, FileConfiguration> configs = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger("GFile");

    public void make(String name, String externalPath, String internalPath) {
        File file = new File(externalPath);

        try {
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.getParentFile().mkdirs();

                if (file.createNewFile()) {
                    if (FileUtils.exportResource(PteroBotBootstrap.class.getResourceAsStream(internalPath), externalPath)) {
                        insertIntoMap(name, file);

                        logger.info(name + " successfully created & loaded.");
                    } else {
                        logger.error(name + " creation failed.");
                    }
                } else {
                    logger.error(name + " creation failed.");
                }
            } else {
                insertIntoMap(name, file);

                logger.info(name + " successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoMap(String name, File file) {
        try {
            configs.put(name, FileConfigurationFactory.get(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getFileConfiguration(String name) {
        return configs.get(name);
    }

    public void clearMap() {
        configs.clear();
    }
}
