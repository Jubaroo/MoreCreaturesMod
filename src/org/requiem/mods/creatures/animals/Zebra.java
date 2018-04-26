
package org.requiem.mods.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;
import org.requiem.mods.creatures.CreatureMod;

public class Zebra implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_ZEBRA = "mod.creature.zebra";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_SWIMMING, C_TYPE_VEHICLE, C_TYPE_DOMESTIC, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_HERBIVORE, C_TYPE_DOMINATABLE, C_TYPE_HERD};
        final int[] itemsButchered = new int[]{ItemList.tail, ItemList.hoof, ItemList.tallow, ItemList.animalHide, ItemList.bladder, ItemList.eye};
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.zebra", "Zebra", "Zebras like this one have many stripes.", "model.creature.quadraped.zebra", types, BodyTemplate.TYPE_HORSE, (short) 5, (byte) 0, (short) 180, (short) 50, (short) 250, "sound.death.horse", "sound.death.horse", "sound.combat.hit.horse", "sound.combat.hit.horse", 0.13f, 1.0f, 2.5f, 1.5f, 2.0f, 0.0f, 1.5f, 100, itemsButchered, 10, 0);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 20.0f);
        builder.skill(SkillList.BODY_CONTROL, 20.0f);
        builder.skill(SkillList.BODY_STAMINA, 20.0f);
        builder.skill(SkillList.MIND_LOGICAL, 20.0f);
        builder.skill(SkillList.MIND_SPEED, 20.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 20.0f);
        builder.skill(SkillList.SOUL_DEPTH, 20.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 20.0f);
        builder.maxAge(120);
        builder.baseCombatRating(6.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.alignment(0.0f);
        builder.handDamString("kick");
        builder.kickDamString("kick");
        builder.armourType(ArmourTypes.ARMOUR_LEATHER);
        builder.isHorse(true);
        builder.maxPercentOfCreatures(0.002f);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HORSE);
        return builder;
    }

    @Override
    public ModVehicleBehaviour getVehicleBehaviour() {
        return new ModVehicleBehaviour() {
            @Override
            public void setSettingsForVehicle(final Item item, final Vehicle vehicle) {
            }

            @Override
            public void setSettingsForVehicle(final Creature creature, final Vehicle v) {
                final VehicleFacade vehicle = this.wrap(v);
                vehicle.createPassengerSeats(0);
                vehicle.setSeatFightMod(0, 0.7f, 0.9f);
                vehicle.setSeatOffset(0, 0.0f, 0.0f, 0.0f);
                vehicle.setCreature(true);
                vehicle.setSkillNeeded(21.0f);
                vehicle.setName(creature.getName());
                vehicle.setMaxDepth(-0.7f);
                vehicle.setMaxHeightDiff(0.04f);
                vehicle.setMaxSpeed(26.0f);
                vehicle.setCommandType((byte) 3);
                vehicle.setCanHaveEquipment(true);
            }
        };
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.farmAnimals) {
            new EncounterBuilder(Tiles.Tile.TILE_STEPPE.id).addCreatures(templateId, 1).build(1);
        }
    }
}