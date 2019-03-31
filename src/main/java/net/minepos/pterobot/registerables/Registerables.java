package net.minepos.pterobot.registerables;

import lombok.Getter;
import net.minepos.pterobot.registerables.implementations.ConsoleRegisterable;
import net.minepos.pterobot.registerables.implementations.FilesRegisterable;
import net.minepos.pterobot.registerables.implementations.JDARegisterable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Registerables {
    FILES(FilesRegisterable.class),
    JDA(JDARegisterable.class),
    CONSOLE(ConsoleRegisterable.class);

    @Getter private final Class<? extends Registerable> clazz;

    Registerables(Class<? extends Registerable> clazz) {
        this.clazz = clazz;
    }
}
