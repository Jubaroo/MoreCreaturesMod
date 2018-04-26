
package org.requiem.mods.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.TraitsSetter;
import org.requiem.mods.creatures.CreatureMod;

public class Unicorn implements ModCreature {
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        return new CreatureTemplateBuilder(21) {
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(21);
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
            case 26: {
                return "green";
            }
            case 28: {
                return "blue";
            }
            case 29: {
                return "orange";
            }
            case 30: {
                return "yellow";
            }
            case 31: {
                return "red";
            }
            case 32: {
                return "pink";
            }
            case 33: {
                return "purple";
            }
            default: {
                return null;
            }
        }
    }

    public void assignTraits(final TraitsSetter traitsSetter) {
        if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(26, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(28, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(29, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(30, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(31, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(32, true);
        } else if (Server.rand.nextInt(8) == 0) {
            traitsSetter.setTraitBit(33, true);
        }
    }

    @Override
    public void addEncounters() {
        if (CreatureMod.farmAnimals) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE_OAK.id).addCreatures(21, 3).build(1);
        }
    }
}