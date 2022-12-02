package ru.iamdvz.mythika.MS.modifiers;

import com.nisovin.magicspells.castmodifiers.Condition;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import ru.iamdvz.mythika.Mythika;

public class MobLvl extends Condition {
    private int lvl;
    private String mode;
    @Override
    public boolean initialize(String var) {
        lvl = Integer.parseInt(var.substring(1));
        mode = var.substring(0,1);
        System.out.println("ITS A LVL" + lvl);
        System.out.println("ITS A MODE" + mode);
        return true;
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        return check(livingEntity, livingEntity);
    }

    @Override
    public boolean check(LivingEntity livingEntity, LivingEntity target) {
        boolean bool;
        return switch (mode) {
            case ">" -> (Mythika.getMythic().getMobManager().getActiveMob(target.getUniqueId()).get().getLevel() > lvl);
            case "<" -> (Mythika.getMythic().getMobManager().getActiveMob(target.getUniqueId()).get().getLevel() < lvl);
            case "=" -> (Mythika.getMythic().getMobManager().getActiveMob(target.getUniqueId()).get().getLevel() == lvl);
            default -> false;
        };
    }

    @Override
    public boolean check(LivingEntity livingEntity, Location location) {
        return false;
    }
}
