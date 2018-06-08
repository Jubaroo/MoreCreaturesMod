
package org.requiem.mods.morecreatures.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class WolfPackmaster implements ModCreature, CreatureTypes {

    private static final String WOLF_PACKMASTER = "morecreatures.wolf.packmaster";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = {C_TYPE_AGG_HUMAN, C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_HUNTING, C_TYPE_DOMINATABLE, C_TYPE_CARNIVORE};
        final int[] itemsButchered = new int[]{  paw, pelt, tooth, eye, tail };

        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(WOLF_PACKMASTER, "Wolf Packmaster", "An alpha wolf that is in charge of the pack. Weak alone, but fierce in a pack", "model.creature.quadraped.wolf.black", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0, (short) 80, (short) 30, (short) 150, "sound.death.wolf", "sound.death.wolf", "sound.combat.hit.wolf", "sound.combat.hit.wolf", 0.52f, 4.0F, 0.0F, 6.0F, 0.0F, 0.0F, 1.3F, 1500, itemsButchered, 35, 75);

        templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 45.0F);
        builder.skill(BODY_CONTROL, 25.0F);
        builder.skill(BODY_STAMINA, 30.0F);
        builder.skill(MIND_LOGICAL, 8.0F);
        builder.skill(MIND_SPEED, 8.0F);
        builder.skill(SOUL_STRENGTH, 25.0F);
        builder.skill(SOUL_DEPTH, 30.0F);
        builder.skill(SOUL_STRENGTH, 30.0F);
        builder.skill(WEAPONLESS_FIGHTING, 28.0F);

        builder.handDamString("claw");
        builder.kickDamString("scratch");
        builder.headbuttDamString("bite");
        builder.maxAge(90);
        builder.armourType(ArmourTypes.ARMOUR_CHAIN);
        builder.baseCombatRating(8.0f);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxGroupAttackSize(6);
        builder.maxPopulationOfCreatures(30);
        builder.sizeModifier(120, 120, 120);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_CANINE);
        return builder;
    }

    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.animals) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(2);
        }
    }
}