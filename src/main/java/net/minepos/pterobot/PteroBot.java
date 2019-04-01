package net.minepos.pterobot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import net.minepos.pterobot.registerables.Registerables;

import java.util.stream.Stream;

import static net.minepos.pterobot.registerables.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PteroBot {
    @Inject private Injector injector;
    @Inject private Values values;

    void start() {
        Stream.of(FILES, JDA, COMMANDS, PTERO_API, CONSOLE).map(Registerables::getClazz).map(injector::getInstance).forEach(r -> {
            r.run();
            values.putAll(r.getValues());
        });
    }
}
