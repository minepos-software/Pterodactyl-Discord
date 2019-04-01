package net.minepos.pterobot.commands;

import com.google.inject.Inject;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minepos.pterobot.file.GFile;
import net.minepos.pterobot.objects.Command;

import java.util.ArrayList;

public class CommandHandler extends ListenerAdapter {
    private static CommandHandler ourInstance = new CommandHandler();
    public static CommandHandler getInstance() {
        return ourInstance;
    }

    @Inject private GFile gFile;
    public ArrayList<Command> commands;

    private CommandHandler() {
        commands = new ArrayList<Command>();
        commands.add(new LinkCommand());
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {

        String prefix = gFile.getFileConfiguration("config").getString("prefix");
        if(event.getMessage().getContentRaw().startsWith(prefix)){
            for(Command c : commands){
                if(event.getMessage().getContentRaw().startsWith(prefix+c.command)){
                    if((c.adminOnly && (event.getMember().hasPermission(Permission.ADMINISTRATOR))) || !c.adminOnly){
                        c.onCommand(event);
                        break;
                    }
                }
            }
        }
    }
}
