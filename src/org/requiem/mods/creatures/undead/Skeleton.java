package org.requiem.mods.creatures.undead;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class Skeleton implements ModCreature, CreatureTypes {

    private static final String MOD_CREATURE_Skeleton = "mod.creature.skeleton";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = { CreatureTypes.C_TYPE_MOVE_LOCAL, CreatureTypes.C_TYPE_AGG_HUMAN, CreatureTypes.C_TYPE_AGG_WHITIE, CreatureTypes.C_TYPE_HUNTING, CreatureTypes.C_TYPE_MONSTER, CreatureTypes.C_TYPE_NON_NEWBIE, CreatureTypes.C_TYPE_MISSION_OK, CreatureTypes.C_TYPE_MISSION_TRAITOR_OK };
        final int[] itemsButchered = new int[0];
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.skeleton",
                "Reanimated Skeleton",
                "This abomination has been animated by powerful magic.",
                "model.creature.humanoid.human.skeleton",
                types, BodyTemplate.TYPE_HUMAN, (short)20, (byte)0, (short)180, (short)20, (short)35,
                "sound.combat.death.skeleton", "sound.combat.death.skeleton", "sound.combat.hit.skeleton", "sound.combat.hit.skeleton",
                1.0f, 3.0f, 3.0f, 7.0f, 0.0f, 0.0f, 0.7f, 1500, itemsButchered, 25, 100);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 10.0F);
        builder.skill(SkillList.BODY_CONTROL, 20.0F);
        builder.skill(SkillList.BODY_STAMINA, 20.0F);
        builder.skill(SkillList.MIND_LOGICAL, 5.0F);
        builder.skill(SkillList.MIND_SPEED, 10.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 10.0F);
        builder.skill(SkillList.SOUL_DEPTH, 10.0F);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 70.0F);
        builder.maxAge(100);
        builder.hasHands(true);
        builder.armourType(ArmourTypes.ARMOUR_CHAIN);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.alignment(-20.0F);
        builder.baseCombatRating(9.0F);
        builder.maxGroupAttackSize(3);
        builder.maxPercentOfCreatures(0.00007F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.undead) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM.id)
                    .addCreatures(templateId, 1)
                    .build(3);

        }
    }
}
