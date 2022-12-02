package ru.iamdvz.mythika.MS.modifiers;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.events.ConditionsLoadingEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import ru.iamdvz.mythika.Mythika;

public class CustomModifiers implements Listener {
    public CustomModifiers() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Mythika.getInstance());
    }

    @EventHandler
    public void onConditionLoad(ConditionsLoadingEvent event) {
        MagicSpells.getConditionManager().addCondition("mmauraactive", AuraActive.class);
        Mythika.getInstance().getLogger().info("[MS] mmauraactive was loaded!");
        MagicSpells.getConditionManager().addCondition("mmismob", IsMythicMob.class);
        Mythika.getInstance().getLogger().info("[MS] mmismob was loaded!");
        MagicSpells.getConditionManager().addCondition("mmlvl", MobLvl.class);
        Mythika.getInstance().getLogger().info("[MS] mmlvl was loaded!");
    }
}
