package ru.iamdvz.mythika.MS.targeted;

import com.nisovin.magicspells.spells.TargetedEntitySpell;
import com.nisovin.magicspells.spells.TargetedSpell;
import com.nisovin.magicspells.util.MagicConfig;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.entity.LivingEntity;
import ru.iamdvz.mythika.Mythika;

import java.util.UUID;

public class GotoCasterSpell extends TargetedSpell implements TargetedEntitySpell {
    private final int maxDistance;
    private final boolean overrideThreat;

    public GotoCasterSpell(MagicConfig config, String spellName) {
        super(config, spellName);
        maxDistance = getConfigInt("max-distance", 1);
        overrideThreat = getConfigBoolean("override-threat", false);
    }
    @Override
    public PostCastAction castSpell(LivingEntity caster, SpellCastState state, float power, String[] args) {
        return null;
    }
    @Override
    public boolean castAtEntity(LivingEntity caster, LivingEntity target, float power) {
        if (Mythika.getMythic().getMobManager().isActiveMob(target.getUniqueId())) {
            gotoCaster(caster.getUniqueId(), target.getUniqueId());
            return true;
        }
        return false;
    }

    @Override
    public boolean castAtEntity(LivingEntity target, float power) {
        return false;
    }


    private boolean gotoCaster(UUID casterUUID, UUID mobUUID) {
        MythicBukkit Mythic = Mythika.getMythic();
        AbstractEntity caster = Mythic.getBootstrap().getEntity(casterUUID);
        AbstractEntity mob = Mythic.getBootstrap().getEntity(mobUUID);
        if (Mythic.getMobManager().isActiveMob(mobUUID)) {
            if (!(Mythic.getMobManager().getActiveMob(mobUUID).get().getThreatTable().inCombat())) {
                Mythic.getVolatileCodeHandler().getAIHandler().navigateToLocation(mob, caster.getLocation(), maxDistance);
            }
            if (Mythic.getMobManager().getActiveMob(mobUUID).get().getThreatTable().inCombat() && overrideThreat) {
                Mythic.getVolatileCodeHandler().getAIHandler().navigateToLocation(mob, caster.getLocation(), maxDistance);
                Mythic.getMobManager().getActiveMob(mobUUID).get().getThreatTable().threatSet(caster, (Mythic.getMobManager().getActiveMob(mobUUID).get().getThreatTable().getTotalThreat()+1));
            }
            return true;
            }
        return false;
        }
}
