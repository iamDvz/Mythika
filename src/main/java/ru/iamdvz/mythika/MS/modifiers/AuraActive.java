package ru.iamdvz.mythika.MS.modifiers;

import com.nisovin.magicspells.castmodifiers.Condition;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import ru.iamdvz.mythika.Mythika;

public class AuraActive extends Condition {
    private String aura;

    @Override
    public boolean initialize(String var) {
        aura = var;
        return true;
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        return check(livingEntity, livingEntity);
    }

    @Override
    public boolean check(LivingEntity livingEntity, LivingEntity target) {
        return Mythika.getMythic().getSkillManager().getAuraManager().getAuraRegistry(target.getUniqueId()).hasAura(aura);
    }

    @Override
    public boolean check(LivingEntity livingEntity, Location location) {
        return false;
    }
}
