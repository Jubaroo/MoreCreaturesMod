
package org.requiem.mods.morecreatures.creatures.mounts;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.ModTraits;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;

public class Unicorn implements ModCreature {

    private static final int COLOR_GREEN = 33;
    private static final int COLOR_BLUE = 32;
    private static final int COLOR_ORANGE = 30;
    private static final int COLOR_YELLOW = 34;
    private static final int COLOR_RED = 35;
    private static final int COLOR_PINK = 26;
    private static final int COLOR_PURPLE = 31;

    private static final int COLOR_TRAITS =
            1 << COLOR_GREEN |
            1 << COLOR_BLUE |
            1 << COLOR_ORANGE |
            1 << COLOR_YELLOW |
            1 << COLOR_RED |
            1 << COLOR_PINK |
            1 << COLOR_PURPLE;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(CreatureTemplateIds.UNICORN_CID) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.UNICORN_CID);
                } catch (NoSuchCreatureTemplateException e) {
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
            case COLOR_GREEN:
                return "green";
            case COLOR_BLUE:
                return "blue";
            case COLOR_ORANGE:
                return "orange";
            case COLOR_YELLOW:
                return "yellow";
            case COLOR_RED:
                return "red";
            case COLOR_PINK:
                return "pink";
            case COLOR_PURPLE:
                return "purple";
            default:
                return null;
        }
    }

    //@Override
    //public String getColourName(final int trait) {
    //    switch (trait) {
    //        case COLOR_GREEN:
    //            return "green";
    //        case COLOR_BLUE:
    //            return "blue";
    //        case COLOR_ORANGE:
    //            return "orange";
    //        case COLOR_YELLOW:
    //            return "yellow";
    //        case COLOR_RED:
    //            return "red";
    //        case COLOR_PINK:
    //            return "pink";
    //        case COLOR_PURPLE:
    //            return "purple";
    //        default:
    //            return null;
    //    }
    //}

    @Override
    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_GREEN, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_BLUE, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_ORANGE, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_YELLOW, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_PURPLE, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_PINK, true);
        }
        else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(COLOR_RED, true);
        }
    }

    @Override
    public long calcNewTraits(double breederSkill, boolean inbred, long mothertraits, long fathertraits) {
        return ModTraits.calcNewTraits(breederSkill, inbred, mothertraits, fathertraits, ModTraits.REGULAR_TRAITS, COLOR_TRAITS);
    }

}
