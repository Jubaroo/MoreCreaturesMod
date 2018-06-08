
package org.requiem.mods.morecreatures.creatures.animals;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.ModTraits;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;

public class Anaconda implements ModCreature {
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(CreatureTemplateIds.ANACONDA_CID) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.ANACONDA_CID);
                } catch (NoSuchCreatureTemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public boolean hasTraits() {
        return true;
    }

    public String getTraitName(final int trait) {
        switch (trait) {
            case 24: {
                return "red";
            }
            default: {
                return null;
            }
        }
    }

    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(3) == 0) {
            traitsSetter.setTraitBit(24, true);
        }
    }

    @Override
    public long calcNewTraits(double breederSkill, boolean inbred, long mothertraits, long fathertraits) {
        return ModTraits.calcNewTraits(breederSkill, inbred, mothertraits, fathertraits, ModTraits.REGULAR_TRAITS, 1 << 24);
    }
}
