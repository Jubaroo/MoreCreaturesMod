
package org.requiem.mods.creatures.animals;

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

public class Ocelot implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_OCELOT = "mod.creature.ocelot";
    private int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_AGG_HUMAN, C_TYPE_HUNTING, C_TYPE_CLIMBER, C_TYPE_DOMINATABLE, C_TYPE_CARNIVORE, C_TYPE_MISSION_OK, C_TYPE_MISSION_TRAITOR_OK, C_TYPE_CAREFUL};
        final int[] itemsButchered = new int[]{ItemList.paw, ItemList.pelt, ItemList.eye, ItemList.eye};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.ocelot", "Ocelot", "The ocelot, also known as the dwarf leopard, is a wild cat distributed extensively within South America, including the islands of Trinidad and Margarita, Central America, and Mexico.", "model.creature.quadraped.lion.ocelot", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0, (short) 60, (short) 30, (short) 90, "sound.death.lion", "sound.death.lion", "sound.combat.hit.lion", "sound.combat.hit.lion", 0.1f, 3.0f, 0.0f, 5.0f, 0.0f, 0.0f, 1.0f, 1200, itemsButchered, 25, 40);
        this.templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 15.0f);
        builder.skill(SkillList.BODY_CONTROL, 3.0f);
        builder.skill(SkillList.BODY_STAMINA, 15.0f);
        builder.skill(SkillList.MIND_LOGICAL, 7.0f);
        builder.skill(SkillList.MIND_SPEED, 8.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 25.0f);
        builder.skill(SkillList.SOUL_DEPTH, 4.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 6.0f);
        builder.handDamString("claw");
        builder.kickDamString("scratch");
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_CLOTH);
        builder.baseCombatRating(4.0f);
        builder.combatDamageType(Wound.TYPE_SLASH);
        builder.maxGroupAttackSize(2);
        builder.denName("ocelot hideout");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPercentOfCreatures(0.01f);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_CAT);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (this.templateId == 0) {
            return;
        }
        if (CreatureMod.farmAnimals) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(this.templateId, 3).build(1);
        }
    }
}