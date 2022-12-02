package ru.iamdvz.mythika.MM.conditions;

import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.iamdvz.mythika.Mythika;

public class CustomConditions implements Listener {

    public CustomConditions() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Mythika.getInstance());
    }

    @EventHandler
    public void onMythicConditionLoadEvent(MythicConditionLoadEvent event) {
        switch (event.getConditionName().toLowerCase()) {
            case "msbuffactive": {
                event.register(new MagicSpellsBuffactive(event.getConfig()));
                Mythika.getInstance().getLogger().info("[MM] msbuffactive was loaded!");
                break;
            }
        }
    }
}
