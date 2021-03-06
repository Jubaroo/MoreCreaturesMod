
package org.requiem.mods.morecreatures.creatures.mounts;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class RainbowUnicorn implements ModCreature, CreatureTypes {

    private static final String UNICORN_RAINBOW = "morecreatures.unicorn.rainbow";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_SWIMMING, C_TYPE_VEHICLE, C_TYPE_DOMESTIC, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_CARNIVORE, C_TYPE_DOMINATABLE, C_TYPE_NO_REBIRTH};
        final int[] itemsButchered = new int[]{ tail, hoof, tallow, animalHide, bladder, eye };
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(UNICORN_RAINBOW, "Rainbow Unicorn", "Rainbow unicorns are rumored to have come through a portal from a magical land.", "model.creature.quadraped.unicorn.rainbow", types, BodyTemplate.TYPE_HORSE, (short) 7, (byte) 0,
                (short) 180, (short) 50, (short) 250, "sound.death.horse", "sound.death.horse", "sound.combat.hit.horse", "sound.combat.hit.horse", 3.0f, 4.0f, 8.0f, 6.0f, 4.0f, 0.0f, 5.0f, 1000, itemsButchered, 10, 0);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 30.0f);
        builder.skill(BODY_CONTROL, 35.0f);
        builder.skill(BODY_STAMINA, 50.0f);
        builder.skill(MIND_LOGICAL, 30.0f);
        builder.skill(MIND_SPEED, 30.0f);
        builder.skill(SOUL_STRENGTH, 30.0f);
        builder.skill(SOUL_DEPTH, 30.0f);
        builder.skill(WEAPONLESS_FIGHTING, 30.0f);
        
        builder.maxAge(300);
        builder.baseCombatRating(12.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.alignment(100.0f);
        builder.handDamString("kick");
        builder.kickDamString("kick");
        builder.headbuttDamString("bite");
        builder.armourType(ArmourTypes.ARMOUR_CHAIN);
        builder.maxPopulationOfCreatures(10);
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
                vehicle.setMaxSpeed(38.0f);
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
        if (CreatureMod.customMounts) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        }
    }
}