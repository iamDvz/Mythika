package ru.iamdvz.mythika.MM.conditions;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;
import com.nisovin.magicspells.spells.BuffSpell;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import org.bukkit.entity.LivingEntity;

public class MagicSpellsBuffactive implements IEntityCondition {
    protected final String buffName;

    public MagicSpellsBuffactive(MythicLineConfig config) {
        buffName = config.getString(new String[]{"buff", "b"}, "dummy");
    }

    @Override
    public boolean check(AbstractEntity abstractEntity) {
        Spell spell = MagicSpells.getSpellByInternalName(buffName);
        if (spell instanceof BuffSpell buff) {
            return buff.isActiveAndNotExpired((LivingEntity) BukkitAdapter.adapt(abstractEntity));
        }
        return false;
    }
}
