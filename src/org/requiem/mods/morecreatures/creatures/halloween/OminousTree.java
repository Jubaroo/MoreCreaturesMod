
package org.requiem.mods.morecreatures.creatures.halloween;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.acorn;
import static com.wurmonline.server.skills.SkillList.*;

public class OminousTree implements ModCreature, CreatureTypes {

    private static final String TREE = "morecreatures.tree";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_MONSTER, C_TYPE_AGG_HUMAN, C_TYPE_HUNTING, C_TYPE_CARNIVORE, C_TYPE_DOMINATABLE, C_TYPE_NON_NEWBIE, C_TYPE_MISSION_OK, C_TYPE_MISSION_TRAITOR_OK, C_TYPE_NO_REBIRTH};
        final int[] itemsButchered = new int[]{ acorn };

        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(TREE, "Ominous Tree", "An enchanted tree that have gone berserk from magic and turned hostile.", "model.creature.tree", types, BodyTemplate.TYPE_BEAR, (short) 8, (byte) 0,
                (short) 160, (short) 50, (short) 50, "sound.tree.falling", "sound.tree.falling", "sound.work.woodcutting1", "sound.work.woodcutting1", 0.8f, 4.0f, 0.0f, 11.0f, 0.0f, 0.0f, 1.0f, 500, itemsButchered, 20, 100);

        templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 26.0f);
        builder.skill(BODY_CONTROL, 26.0f);
        builder.skill(BODY_STAMINA, 26.0f);
        builder.skill(MIND_LOGICAL, 4.0f);
        builder.skill(MIND_SPEED, 4.0f);
        builder.skill(SOUL_STRENGTH, 30.0f);
        builder.skill(SOUL_DEPTH, 4.0f);
        builder.skill(WEAPONLESS_FIGHTING, 30.0f);

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
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id).addCreatures(templateId, 1).build(3);
            new EncounterBuilder(Tiles.Tile.TILE_DIRT.id).addCreatures(templateId, 1).build(1);

        }
    }
}