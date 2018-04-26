
package org.requiem.mods.creatures.undead;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class Reaper implements ModCreature, CreatureTypes
{
    private static final String MOD_CREATURE_REAPER = "mod.creature.reaper";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                CreatureTypes.C_TYPE_MOVE_LOCAL,
                CreatureTypes.C_TYPE_AGG_HUMAN,
                CreatureTypes.C_TYPE_AGG_WHITIE,
                CreatureTypes.C_TYPE_HUNTING,
                CreatureTypes.C_TYPE_CARNIVORE,
                CreatureTypes.C_TYPE_DETECTINVIS,
                CreatureTypes.C_TYPE_NON_NEWBIE
        };
        final int [] itemsButchered = new int[0];
        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.reaper",
                "Reaper",
                "A Reaper harvests the living, like a farmer harvests the crops.",
                "model.creature.humanoid.human.spirit.wraith",
                types, BodyTemplate.TYPE_HUMAN,
                (short) 30,
                (byte) 0,
                (short) 85, (short) 50, (short) 85,
                "sound.death.spirit.male", "sound.death.spirit.female", "sound.combat.hit.spirit.male", "sound.combat.hit.spirit.female",
                0.05f,
                23f, 0f, 19f, 0.0f, 0.0f,
                2.0f,
                2000,
                itemsButchered,
                20,
                100,
                Materials.MATERIAL_MEAT_HUMANOID
        );

        builder.skill(SkillList.BODY_STRENGTH, 55.0f);
        builder.skill(SkillList.BODY_STAMINA, 65.0f);
        builder.skill(SkillList.BODY_CONTROL, 60.0f);
        builder.skill(SkillList.MIND_LOGICAL, 50.0f);
        builder.skill(SkillList.MIND_SPEED, 50.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 50.0f);
        builder.skill(SkillList.SOUL_DEPTH, 50.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 50.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 40.0f);

        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.handDamString("claw");
        builder.maxAge(200);
        builder.armourType(ArmourTypes.ARMOUR_LEATHER_DRAGON);
        builder.baseCombatRating(25.0f);
        builder.combatDamageType(Wound.TYPE_INFECTION);
        builder.maxPopulationOfCreatures(50);
        builder.maxGroupAttackSize(7);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT);

        templateId = builder.getTemplateId();
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
            new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM_LAWN.id)
                    .addCreatures(templateId, 1)
                    .build(3);
        }
    }
}
