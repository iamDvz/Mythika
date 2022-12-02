package ru.iamdvz.mythika.MS.targeted;

import com.nisovin.magicspells.spells.TargetedEntitySpell;
import com.nisovin.magicspells.spells.TargetedSpell;
import com.nisovin.magicspells.util.MagicConfig;
import com.nisovin.magicspells.util.ValidTargetChecker;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.LivingEntity;
import ru.iamdvz.mythika.Mythika;

import java.util.UUID;

public class ThreatSpell extends TargetedSpell implements TargetedEntitySpell {
    private final String threatMethod;
    private final int threatAmount;
    private final ValidTargetChecker checker;
    public ThreatSpell(MagicConfig config, String spellName) {
        super(config, spellName);
        threatMethod = getConfigString("threat-method", "set");
        threatAmount = getConfigInt("threat-amount", 1);
        checker = (LivingEntity entity) -> Mythika.getMythic().getMobManager().isActiveMob(entity.getUniqueId());
    }
    @Override
    public PostCastAction castSpell(LivingEntity caster, SpellCastState state, float power, String[] args) {
        return PostCastAction.HANDLE_NORMALLY;
    }
    @Override
    public boolean castAtEntity(LivingEntity caster, LivingEntity target, float power) {
        if (Mythika.getMythic().getMobManager().isActiveMob(target.getUniqueId())) {
            setThreat(caster.getUniqueId(), target.getUniqueId());
        }
        return false;
    }

    @Override
    public boolean castAtEntity(LivingEntity target, float power) {
        return false;
    }


    private boolean setThreat(UUID casterUUID, UUID mobUUID) {
        AbstractEntity caster = Mythika.getMythic().getBootstrap().getEntity(casterUUID);
        if (Mythika.getMythic().getMobManager().isActiveMob(mobUUID)) {
            ActiveMob.ThreatTable mobThreat = Mythika.getMythic().getMobManager().getActiveMob(mobUUID).get().getThreatTable();
            switch (threatMethod.toUpperCase()) {
                case "SET" -> mobThreat.threatSet(caster, threatAmount);
                case "PLUS" -> mobThreat.threatGain(caster, threatAmount);
                case "MINUS" -> mobThreat.threatLoss(caster, threatAmount);
                case "TOP" -> mobThreat.threatSet(caster, (mobThreat.getTotalThreat()+1));
                case "INIT" -> mobThreat.threatSet(caster, 0.001);
            }
            return true;
        }

        return false;
    }
}
