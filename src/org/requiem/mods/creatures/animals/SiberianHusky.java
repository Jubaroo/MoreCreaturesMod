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

public class SiberianHusky implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_HUSKY = "mod.creature.husky";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_ANIMAL, C_TYPE_HUNTING, C_TYPE_DOMESTIC, C_TYPE_DOMINATABLE, C_TYPE_CARNIVORE, C_TYPE_SWIMMING};
        final int[] itemsButchered = new int[]{ItemList.tallow, ItemList.paw, ItemList.fur, ItemList.tooth, ItemList.tail};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.husky", "Siberian Husky", "The Siberian Husky is a medium size working dog breed that originated in north-eastern Siberia, Russia. The breed belongs to the Spitz genetic family.", "model.creature.quadraped.wolf.husky", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0, (short) 80, (short) 30, (short) 150, "sound.death.dog", "sound.death.dog", "sound.combat.hit.dog", "sound.combat.hit.dog", 0.18f, 2.0f, 0.0f, 3.0f, 0.0f, 0.0f, 1.2f, 100, itemsButchered, 25, 10);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 15.0f);
        builder.skill(SkillList.BODY_CONTROL, 15.0f);
        builder.skill(SkillList.BODY_STAMINA, 18.0f);
        builder.skill(SkillList.MIND_LOGICAL, 7.0f);
        builder.skill(SkillList.MIND_SPEED, 8.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 25.0f);
        builder.skill(SkillList.SOUL_DEPTH, 4.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 20.0f);
        builder.handDamString("scratch");
        builder.kickDamString("claw");
        builder.headbuttDamString("bite");
        builder.maxAge(150);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.baseCombatRating(6.0f);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxGroupAttackSize(4);
        builder.denName("husky den");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPercentOfCreatures(0.001F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_CANINE);
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.farmAnimals) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 2).build(1);
        }
    }
}