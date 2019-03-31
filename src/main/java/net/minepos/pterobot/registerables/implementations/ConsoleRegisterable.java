package net.minepos.pterobot.registerables.implementations;

import com.google.inject.Inject;
import net.minepos.pterobot.registerables.Registerable;
import net.minepos.pterobot.tasks.Task;

import java.util.Scanner;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleRegisterable extends Registerable {
    @Inject private FilesRegisterable files;

    @Override
    protected void execute() {
        Task.async(r -> {
            Scanner input = new Scanner(System.in);

            while (true) {
                switch(input.nextLine().toLowerCase()) {
                    case "stop": System.exit(0);
                    case "reload": files.execute(); break;
                }
            }
        });
    }
}