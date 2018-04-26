
package org.requiem.mods.creatures.halloween;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class ScaryPumpkin implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_PUMPKIN = "mod.creature.pumpkin";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {7, 3, 6, 13, 29, 32, 39, 60, 61};
        final int[] itemsButchered = new int[]{ItemList.pumpkin};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.pumpkin", "Scary Pumpkin", "An enchanted tree that have gone berserk from a stray spell. It has come alive and turned hostile.", "model.creature.pumpkin", types, BodyTemplate.TYPE_BEAR, (short) 5, (byte) 0, (short) 160, (short) 50, (short) 50, "sound.tree.falling", "sound.tree.falling", "sound.work.woodcutting1", "sound.work.woodcutting1", 0.17f, 6.0f, 1.5f, 11.0f, 0.0f, 0.0f, 1.8f, 750, itemsButchered, 30, 100);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 26.0f);
        builder.skill(SkillList.BODY_CONTROL, 26.0f);
        builder.skill(SkillList.BODY_STAMINA, 26.0f);
        builder.skill(SkillList.MIND_LOGICAL, 4.0f);
        builder.skill(SkillList.MIND_SPEED, 4.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
        builder.skill(SkillList.SOUL_DEPTH, 4.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 30.0f);
        builder.handDamString("vine whip");
        builder.kickDamString("vine whip");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.baseCombatRating(11.0f);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxGroupAttackSize(6);
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