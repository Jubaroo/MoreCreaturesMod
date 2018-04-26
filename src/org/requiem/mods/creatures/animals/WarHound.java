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

public class WarHound implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_WARHOUND = "mod.creature.warhound";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_AGG_HUMAN, C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_HUNTING, C_TYPE_DOMESTIC, C_TYPE_DOMINATABLE, C_TYPE_OMNIVORE, C_TYPE_SWIMMING};
        final int[] itemsButchered = new int[]{ItemList.tallow, ItemList.paw, ItemList.pelt, ItemList.tooth, ItemList.eye};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.warhound", "War Hound", "This wolf-like creature is unnaturally big and strong. Cousin to the dog, these org.requiem.mods.creatures are battle hardened over centuries of war.", "model.creature.quadraped.beast.rift", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0, (short) 80, (short) 30, (short) 150, "sound.death.dog", "sound.death.dog", "sound.combat.hit.dog", "sound.combat.hit.dog", 0.36f, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 1.2F, 750, itemsButchered, 30, 0);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 25.0F);
        builder.skill(SkillList.BODY_CONTROL, 25.0F);
        builder.skill(SkillList.BODY_STAMINA, 20.0F);
        builder.skill(SkillList.MIND_LOGICAL, 6.0F);
        builder.skill(SkillList.MIND_SPEED, 7.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 15.0F);
        builder.skill(SkillList.SOUL_DEPTH, 10.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 10.0F);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 30.0F);
        builder.handDamString("claw");
        builder.kickDamString("scratch");
        builder.headbuttDamString("bite");
        builder.maxAge(150);
        builder.armourType(ArmourTypes.ARMOUR_CHAIN);
        builder.baseCombatRating(17.0f);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxGroupAttackSize(4);
        builder.maxPopulationOfCreatures(175);
        builder.sizeModifier(30, 30, 30);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_CANINE);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.farmAnimals) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        }
    }
}