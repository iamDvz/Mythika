package ru.iamdvz.mythika.MM.mechanics;

import com.nisovin.magicspells.MagicSpells;
import com.nisovin.magicspells.Spell;
import com.nisovin.magicspells.spells.TargetedEntitySpell;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import org.bukkit.entity.LivingEntity;
import ru.iamdvz.mythika.Mythika;

public class MagicSpellsCast implements ITargetedEntitySkill {

    protected final String spellName;
    protected final String castMode;
    protected final float power;
    protected String[] args;

    public MagicSpellsCast(MythicLineConfig config) {
        spellName = config.getString(new String[]{"spell","s"}, "dummy");
        castMode = config.getString(new String[]{"mode","m"}, "as");
        power = config.getFloat(new String[] {"power", "p"}, 1);
        if (config.getString(new String[]{"args", "a"}) != null) {
            args = config.getString(new String[]{"args", "a"}).split(",");
        }

    }

    @Override
    public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        if (!MagicSpells.getSpellNames().containsKey(spellName)) {
            Mythika.getInstance().getLogger().info("there are no such spell as "+ spellName + "!");
            return SkillResult.ERROR;
        }
        Spell spell = MagicSpells.getSpellByInternalName(spellName);
        switch (castMode.toLowerCase()) {
            case "as": {
                spell.cast(bukkitTarget, power, args);
                break;
            }
            case "on": {
                if (!(spell instanceof TargetedEntitySpell newSpell)) {
                    break;
                }
                newSpell.castAtEntity(bukkitTarget, power, args);
                break;
            }
        }
        return SkillResult.SUCCESS;
    }
}
