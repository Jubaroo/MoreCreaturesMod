
package org.requiem.mods.creatures.halloween;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class OminousTree implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_TREE = "mod.creature.tree";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_AGG_HUMAN, C_TYPE_HUNTING, C_TYPE_CARNIVORE, C_TYPE_DOMINATABLE, C_TYPE_NON_NEWBIE, C_TYPE_MISSION_OK, C_TYPE_MISSION_TRAITOR_OK, C_TYPE_NO_REBIRTH};
        final int[] itemsButchered = new int[]{ItemList.acorn};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.tree", "Ominous Tree", "An enchanted tree that have gone berserk from magic and turned hostile.", "model.creature.tree", types, BodyTemplate.TYPE_BEAR, (short) 8, (byte) 0, (short) 160, (short) 50, (short) 50, "sound.tree.falling", "sound.tree.falling", "sound.work.woodcutting1", "sound.work.woodcutting1", 0.8f, 4.0f, 0.0f, 11.0f, 0.0f, 0.0f, 1.0f, 500, itemsButchered, 20, 100);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 26.0f);
        builder.skill(SkillList.BODY_CONTROL, 26.0f);
        builder.skill(SkillList.BODY_STAMINA, 26.0f);
        builder.skill(SkillList.MIND_LOGICAL, 4.0f);
        builder.skill(SkillList.MIND_SPEED, 4.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
        builder.skill(SkillList.SOUL_DEPTH, 4.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 30.0f);
        builder.handDamString("branch whip");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.baseCombatRating(8.0f);
        builder.combatDamageType((byte) 0);
        builder.maxGroupAttackSize(3);
        builder.maxPercentOfCreatures(0.05f);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.halloweenMobs) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        }
    }
}