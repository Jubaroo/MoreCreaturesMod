
package org.requiem.mods.morecreatures.creatures.mounts;

import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.items.Item;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;

import static com.wurmonline.server.skills.SkillList.*;

public class GhostHellHorse implements ModCreature, CreatureTypes {

    private static final String GHOST_HELL_HORSE = "morecreatures.horse.ghost.hell";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        
        final int[] types = {C_TYPE_SENTINEL, C_TYPE_SWIMMING, C_TYPE_VEHICLE, C_TYPE_DOMESTIC, C_TYPE_GHOST, C_TYPE_LEADABLE, C_TYPE_DOMINATABLE, C_TYPE_NO_REBIRTH, C_TYPE_HORSE};
        final int[] itemsButchered = new int[0];
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(GHOST_HELL_HORSE, "Ghost Hell Horse", "A ghostly version of a hell horse. These are very fast mounts.", "model.creature.quadraped.horse.hell", types, BodyTemplate.TYPE_HORSE, (short)3, (byte)0,
                (short)180, (short)50, (short)250, "sound.death.horse", "sound.death.horse", "sound.combat.hit.horse", "sound.combat.hit.horse", 0.12f, 1.0f, 2.5f, 1.5f, 2.0f, 0.0f, 3.0f, 100, itemsButchered, 10, 0);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 35.0f);
        builder.skill(BODY_CONTROL, 20.0f);
        builder.skill(BODY_STAMINA, 40.0f);
        builder.skill(MIND_LOGICAL, 7.0f);
        builder.skill(MIND_SPEED, 7.0f);
        builder.skill(SOUL_STRENGTH, 72.0f);
        builder.skill(SOUL_DEPTH, 5.0f);
        builder.skill(WEAPONLESS_FIGHTING, 45.0f);
        
        //builder.maxAge(500);
        builder.baseCombatRating(9.0f);
        builder.combatDamageType(Wound.TYPE_BURN);
        builder.alignment(-40.0f);
        builder.handDamString("kick");
        builder.armourType(ArmourTypes.ARMOUR_LEATHER);
        builder.isHorse(true);
        builder.color(255,255,255);
        builder.onFire(false, (byte)0);
        builder.glowing(true);
        builder.setCombatMoves(new int[]{CombatMove.HELLHORSEFIRE});
        builder.maxGroupAttackSize(4);
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
                vehicle.setMaxSpeed(30.0f);
                vehicle.setCommandType((byte)3);
                vehicle.setCanHaveEquipment(true);
            }
        };
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
    }

}
