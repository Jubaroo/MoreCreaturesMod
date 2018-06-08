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

public class Panther implements ModCreature, CreatureTypes {
    
    private static final String PANTHER = "morecreatures.panther";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = { C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_AGG_HUMAN, C_TYPE_HUNTING, C_TYPE_CLIMBER, C_TYPE_CARNIVORE, C_TYPE_MISSION_OK, C_TYPE_MISSION_TRAITOR_OK, C_TYPE_STEALTH };
        final int[] itemsButchered = new int[]{ paw, pelt, eye, tooth, tail, tallow };
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(PANTHER, "Panther", "A black panther is the melanistic color variant of any Panthera species. Black panthers in Asia and Africa are leopards and black panthers in the Americas are black jaguars.", "model.creature.quadraped.lion.panther", types, BodyTemplate.TYPE_DOG, (short) 10, (byte) 0,
                (short) 60, (short) 30, (short) 90, "sound.death.lion", "sound.death.lion", "sound.combat.hit.lion", "sound.combat.hit.lion", 0.09f, 9.0f, 0.0f, 5.0f, 0.0f, 0.0f, 2.5f, 750, itemsButchered, 30, 50);
        
        templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 25.0f);
        builder.skill(BODY_CONTROL, 13.0f);
        builder.skill(BODY_STAMINA, 15.0f);
        builder.skill(MIND_LOGICAL, 7.0f);
        builder.skill(MIND_SPEED, 8.0f);
        builder.skill(SOUL_STRENGTH, 5.0f);
        builder.skill(SOUL_DEPTH, 4.0f);
        builder.skill(WEAPONLESS_FIGHTING, 20.0f);

        builder.handDamString("claw");
        builder.kickDamString("scratch");
        builder.maxAge(100);
        builder.alignment(0.0f);
        builder.armourType(ArmourTypes.ARMOUR_CLOTH);
        builder.baseCombatRating(3.0f);
        builder.combatDamageType(Wound.TYPE_SLASH);
        builder.maxGroupAttackSize(2);
        builder.denName("panther hideout");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPercentOfCreatures(0.001F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_CAT);

        return builder;
    }

    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.animals) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id).addCreatures(templateId, 3).build(1);
        }
    }
}