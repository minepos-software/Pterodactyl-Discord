package net.minepos.pterobot;

import com.google.inject.Injector;
import net.minepos.pterobot.guice.BinderModule;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class PteroBotBootstrap {
    private PteroBotBootstrap() {
        Injector injector = new BinderModule(getClass()).createInjector();
        injector.injectMembers(this);

        injector.getInstance(PteroBot.class).start();
    }

    public static void main(String[] args) {
        new PteroBotBootstrap();
    }
}