package net.minepos.pterobot.hooks;

import com.google.inject.Inject;
import net.minepos.pterobot.Values;
import net.minepos.pterobot.tasks.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ShutdownHandler extends Thread {
    @Inject private Values values;

    @Override
    public void run() {
        Task.shutdown();
        values.getJDA().shutdownNow();
    }
}
