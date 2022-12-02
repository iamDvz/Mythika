package ru.iamdvz.mythika.MM.mechanics;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.iamdvz.mythika.Mythika;

public class CustomMechanics implements Listener {

    public CustomMechanics() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Mythika.getInstance());
    }

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event) {
        switch (event.getMechanicName().toLowerCase()) {
            case "mscast": {
                event.register(new MagicSpellsCast(event.getConfig()));
                Mythika.getInstance().getLogger().info("[MM] mscast was loaded!");
                break;
            }
        }
    }

}
