package net.choco.getdownpro.command;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.base.CommandBase;
import net.choco.getdown.GetDownPro;
import net.choco.getdown.objects.arena.GDArena;
import net.choco.getdownpro.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("map")
public class MapCommand extends CommandBase {

    @Default
    public void defaultCommand(CommandSender commandSender) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            GDArena a = GetDownPro.getInstance().getPlayerManager().getPlayerArena(player);

            if (a != null) {
                    player.sendMessage(Message.PLAYING_MAP.getMessageWithPrefix().replace("{map}", a.getName()));
                    return;
                } else {
                    player.sendMessage(net.choco.getdown.objects.language.Message.NOT_IN_ARENA.getMessage());
                }
            }
        }
}