package org.requiem.mods.morecreatures.creatures.mounts;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.Materials;
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

public class Reindeer implements ModCreature, CreatureTypes, ArmourTypes {
    
    private static final String REINDEER = "morecreatures.reindeer";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_VEHICLE, C_TYPE_DOMESTIC, C_TYPE_ANIMAL, C_TYPE_LEADABLE, C_TYPE_GRAZER, C_TYPE_HERBIVORE, C_TYPE_DOMINATABLE};
        final int[] itemsButchered = new int[]{ tail, hoof, tallow, animalHide, bladder, eye };

        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(REINDEER, "Reindeer", "The reindeer, also known as the caribou in North America, is a species of deer with circumpolar distribution, native to Arctic, sub-Arctic, tundra, boreal and mountainous regions of northern Europe, Siberia and North America.", "model.creature.quadraped.bison.wagoner", types, BodyTemplate.TYPE_HORSE, (short) 5, (byte) 0,
                (short) 70, (short) 50, (short) 50, "sound.death.deer", "sound.death.deer", "sound.combat.hit.deer", "sound.combat.hit.deer", 1.0f, 1.0f, 0.5f, 1.0f, 2.0f, 0.0f, 1.5f, 100, itemsButchered, 10, 10);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 25.0F);
        builder.skill(BODY_CONTROL, 20.0F);
        builder.skill(BODY_STAMINA, 40.0F);
        builder.skill(MIND_LOGICAL, 7.0F);
        builder.skill(MIND_SPEED, 7.0F);
        builder.skill(SOUL_STRENGTH, 22.0F);
        builder.skill(SOUL_DEPTH, 5.0F);
        builder.skill(WEAPONLESS_FIGHTING, 28.0F);
        
        builder.maxAge(100);
        builder.baseCombatRating(12.0F);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.alignment(0.0f);
        builder.denMaterial(Materials.MATERIAL_WOOD_BIRCH);
        builder.denName("reindeer rustle");
        builder.handDamString("kick");
        builder.kickDamString("kick");
        builder.armourType(ARMOUR_NONE);
        builder.isHorse(true);
        builder.maxPercentOfCreatures(0.003f);
        builder.maxGroupAttackSize(6);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_GAME);
        //builder.sizeModifier(60,60,60);
        
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
        if (CreatureMod.animals) {
            new EncounterBuilder(Tiles.Tile.TILE_TUNDRA.id).addCreatures(templateId, 2).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_SNOW.id).addCreatures(templateId, 2).build(2);
        }
    }

}