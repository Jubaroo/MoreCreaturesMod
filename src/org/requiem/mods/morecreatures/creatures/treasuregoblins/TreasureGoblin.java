
package org.requiem.mods.morecreatures.creatures.treasuregoblins;

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

public class TreasureGoblin implements ModCreature, CreatureTypes {

    private static final String TREASURE_GOBLIN = "morecreatures.goblin.treasure";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        
        final int[] types = { C_TYPE_MOVE_GLOBAL, C_TYPE_MONSTER/*, C_TYPE_UNIQUE*/, C_TYPE_CLIMBER, C_TYPE_FLEEING, C_TYPE_OPENDOORS, C_TYPE_FENCEBREAKER, C_TYPE_NO_REBIRTH };
        final int[] itemsButchered = new int[] {  eye, tooth, gland, eye, bladder };
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(TREASURE_GOBLIN, "Treasure Goblin", "A small creature that steals all of the riches it can find. They are very rare and often carry valuable items.", "model.creature.humanoid.goblin.treasure", types, BodyTemplate.TYPE_HUMAN, (short)100, (byte)0,
                (short)130, (short)30, (short)20, "sound.death.goblin", "sound.death.goblin", "sound.combat.hit.goblin", "sound.combat.hit.goblin", 3.5f, 18.0f, 15.0f, 12.0f, 15.0f, 0.0f, 2.5f, 7500, itemsButchered, 10, 0);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 20.0f);
        builder.skill(BODY_CONTROL, 20.0f);
        builder.skill(BODY_STAMINA, 95.0f);
        builder.skill(MIND_LOGICAL, 20.0f);
        builder.skill(MIND_SPEED, 45.0f);
        builder.skill(SOUL_STRENGTH, 45.0f);
        builder.skill(SOUL_DEPTH, 45.0f);
        builder.skill(WEAPONLESS_FIGHTING, 14.0f);

        builder.handDamString("coin toss");
        builder.kickDamString("silver sweep");
        builder.headbuttDamString("gold tooth");
        //builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.DOUBLE_FIST});
        builder.armourType(ArmourTypes.ARMOUR_CHAIN);
        builder.baseCombatRating(6.0f);
        builder.combatDamageType(Wound.TYPE_INTERNAL);
        builder.maxGroupAttackSize(1);
        builder.hasHands (true);
        builder.color(255,215,0);
        builder.maxPopulationOfCreatures(1);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID);
        
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.treasureGoblin) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_DIRT.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_SAND.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_STEPPE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_TAR.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_TUNDRA.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MOSS.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_PEAT.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_SNOW.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_ROCK.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CLAY.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_BUSH.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM_LAWN.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MARSH.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CAVE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CLIFF.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_COBBLESTONE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_ENCHANTED_TREE.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_ENCHANTED_GRASS.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_DIRT_PACKED.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_ENCHANTED_BUSH.id).addCreatures(templateId, 1).build(1);
        }
    }
}
