package org.requiem.mods.morecreatures.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.items.Materials;
import com.wurmonline.shared.constants.CreatureTypes;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class Gorilla implements ModCreature, CreatureTypes {

    private static final String GORILLA = "morecreatures.gorilla";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = {  C_TYPE_MOVE_LOCAL, C_TYPE_DEFEND_KINGDOM, C_TYPE_HUNTING, C_TYPE_ANIMAL, C_TYPE_CLIMBER, C_TYPE_OMNIVORE, C_TYPE_DETECTINVIS, C_TYPE_NON_NEWBIE, C_TYPE_OPENDOORS };
        final int[] itemsButchered = new int[] { fur, heart, gland, eye, tooth };

       final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(GORILLA, "Gorilla", "This normally calm mountain gorilla may suddenly become a very fierce and dangerous foe if annoyed.", "model.creature.humanoid.gorilla.mountain", types, BodyTemplate.TYPE_HUMAN, (short) 10, (byte) 0,
                (short) 210, (short) 50, (short) 50, "sound.death.gorilla", "sound.death.gorilla", "sound.combat.hit.gorilla", "sound.combat.hit.gorilla", 0.6F, 6.0F, 0.0F, 10.0F, 0.0F, 0.0f, 1.2f, 300, itemsButchered, 10, 94, Materials.MATERIAL_MEAT_GAME);

        templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 40.0F);
        builder.skill(BODY_STAMINA, 25.0F);
        builder.skill(BODY_CONTROL, 40.0F);
        builder.skill(MIND_LOGICAL, 8.0F);
        builder.skill(MIND_SPEED, 10.0f);
        builder.skill(SOUL_STRENGTH, 30.0F);
        builder.skill(SOUL_DEPTH, 7.0F);
        builder.skill(WEAPONLESS_FIGHTING, 40.0F);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_CLOTH);
        builder.baseCombatRating(16.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.denMaterial(Materials.MATERIAL_STONE);
        builder.denName("gorilla cave");
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.01F);
        builder.maxGroupAttackSize(6);

        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.animals) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 2).build(2);
            new EncounterBuilder(Tiles.Tile.TILE_SAND.id).addCreatures(templateId, 2).build(1);
        }
    }
}
