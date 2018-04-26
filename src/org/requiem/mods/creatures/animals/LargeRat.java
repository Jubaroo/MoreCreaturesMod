
package org.requiem.mods.creatures.animals;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;

public class LargeRat implements ModCreature {

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(13) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(13);
                }
                catch (NoSuchCreatureTemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Override
    public boolean hasTraits() {
        return true;
    }

    @Override
    public String getTraitName(final int trait) {
        switch (trait) {
            case 26: {
                return "black";
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(2) == 0) {
            traitsSetter.setTraitBit(26, true);
        }
    }

    @Override
    public void addEncounters() {
        return;
    }

}
