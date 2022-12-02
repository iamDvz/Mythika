package ru.iamdvz.mythika;

import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.iamdvz.mythika.MS.modifiers.CustomModifiers;
import ru.iamdvz.mythika.MM.conditions.CustomConditions;
import ru.iamdvz.mythika.MM.mechanics.CustomMechanics;

public final class Mythika extends JavaPlugin {
    private static Mythika instance;
    private static MythicBukkit mythic;

    @Override
    public void onEnable() {
        instance = this;
        mythic = MythicBukkit.inst();
        new CustomMechanics();
        new CustomConditions();
        new CustomModifiers();
        Bukkit.getLogger().info("Mythika enabled!");
        //getServer().dispatchCommand(getServer().getConsoleSender(), "ms reload");
        //getServer().dispatchCommand(getServer().getConsoleSender(), "mm reload");

    }

    @Override
    public void onDisable() {
    }

    public static Plugin getInstance() {
        return instance;
    }
    public static MythicBukkit getMythic() {
        return mythic;
    }
}
