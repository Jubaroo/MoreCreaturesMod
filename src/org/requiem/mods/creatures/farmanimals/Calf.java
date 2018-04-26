
package org.requiem.mods.creatures.farmanimals;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;

public class Calf implements ModCreature
{
    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(50) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(50);
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
                return "blackangus";
            }
            case 28: {
                return "beltedgalloway";
            }
            case 29: {
                return "spottedblack";
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(4) == 0) {
            traitsSetter.setTraitBit(26, true);
        }
        else if (Server.rand.nextInt(4) == 0) {
            traitsSetter.setTraitBit(28, true);
        }
        else if (Server.rand.nextInt(4) == 0) {
            traitsSetter.setTraitBit(29, true);
        }
    }
}
