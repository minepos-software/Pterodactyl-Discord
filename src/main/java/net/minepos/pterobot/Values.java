package net.minepos.pterobot;

import com.google.inject.Singleton;
import com.stanjg.ptero4j.PteroAdminAPI;
import com.stanjg.ptero4j.PteroUserAPI;
import net.dv8tion.jda.api.JDA;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class Values {
    private Map<String, Object> values = new ConcurrentHashMap<>();

    void putAll(Map<String, Object> values) {
        this.values.putAll(values);
    }

    public JDA getJDA() {
        return (JDA) values.get("JDA");
    }

    public PteroAdminAPI getAdminAPI() {
        return (PteroAdminAPI) values.get("admin-api");
    }

    public PteroUserAPI getUserAPI() {
        return (PteroUserAPI) values.get("user-api");
    }
}
