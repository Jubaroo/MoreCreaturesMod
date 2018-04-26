
package org.requiem.mods.creatures.farmanimals;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;

public class Horse implements ModCreature
{

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(64) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(64);
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
                return "regularbay";
            }
            case 28: {
                return "mangalargamarchador";
            }
            case 29: {
                return "skewbald";
            }
            case 30: {
                return "piebald";
            }
            case 31: {
                return "dappledgrey";
            }
            case 32: {
                return "knabstrupperxappaloosa";
            }
            case 33: {
                return "knabstrupperxappaloosa2";
            }
            case 34: {
                return "creamypalamino";
            }
            case 35: {
                return "knabstrupper";
            }
            case 36: {
                return "knabstrupper2";
            }
            case 37: {
                return "rockymountain";
            }
            case 38: {
                return "dun";
            }
            case 39: {
                return "dummytrait";
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(26, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(28, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(29, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(30, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(31, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(32, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(33, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(34, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(35, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(36, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(37, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(38, true);
        }
        else if (Server.rand.nextInt(19) == 0) {
            traitsSetter.setTraitBit(39, true);
        }
    }
}
