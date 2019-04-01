package net.minepos.pterobot.registerables;

import lombok.Getter;
import net.minepos.pterobot.registerables.implementations.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Registerables {
    FILES(FilesRegisterable.class),
    JDA(JDARegisterable.class),
    COMMANDS(CommandsRegisterable.class),
    PTERO_API(PteroAPIRegisterable.class),
    CONSOLE(ConsoleRegisterable.class);

    @Getter private final Class<? extends Registerable> clazz;

    Registerables(Class<? extends Registerable> clazz) {
        this.clazz = clazz;
    }
}
