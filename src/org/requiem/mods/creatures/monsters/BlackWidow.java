package org.requiem.mods.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class BlackWidow implements ModCreature, CreatureTypes
{
    private static final String MOD_CREATURE_BLACK_WIDOW = "mod.creature.blackWidow";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_ANIMAL,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MISSION_OK,
                C_TYPE_MISSION_TRAITOR_OK
        };
        final int[] itemsButchered = new int[] {
                ItemList.heart,
                ItemList.fur,
                ItemList.eye,
                ItemList.eye
        };
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.blackWidow",
                "Black Widow",
                "Looking like a huge cat, with a dappled coat.",
                "model.creature.multiped.spider.fog",
                types, BodyTemplate.TYPE_SPIDER,
                (short)8,
                (byte)0,
                (short)150, (short)100, (short)150,
                "sound.death.spider", "sound.death.spider", "sound.combat.hit.spider", "sound.combat.hit.spider",
                0.18f,
                5.5f, 0.0f, 12.0f, 0.0f, 0.0f,
                1.0f,
                500,
                itemsButchered,
                20,
                80
        );
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 30.0F);
        builder.skill(SkillList.BODY_CONTROL, 50.0F);
        builder.skill(SkillList.BODY_STAMINA, 40.0F);
        builder.skill(SkillList.MIND_LOGICAL, 10.0F);
        builder.skill(SkillList.MIND_SPEED, 20.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 5.0F);
        builder.skill(SkillList.SOUL_DEPTH, 5.0F);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 40.0F);
        builder.handDamString("bite");
        builder.maxAge(50);
        builder.armourType(ArmourTypes.ARMOUR_PLATE);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.alignment(0.0F);
        builder.baseCombatRating(15.0F);
        builder.maxGroupAttackSize(10);
        builder.denName("widow lair");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPercentOfCreatures(0.0003F);
        builder.setCombatMoves(new int[]{CombatMove.DOUBLE_FIST});
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_INSECT);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.monsters) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MOSS.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_PEAT.id).addCreatures(templateId, 1).build(1);
        }
    }
}
