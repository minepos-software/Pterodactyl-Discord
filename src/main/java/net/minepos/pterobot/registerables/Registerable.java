package net.minepos.pterobot.registerables;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Registerable {
    @Getter private final Map<String, Object> values = new HashMap<>();

    protected abstract void execute();

    protected Registerable addValue(String key, Object value) {
        values.put(key, value);
        return this;
    }

    public void run() {
        execute();
    }
}
