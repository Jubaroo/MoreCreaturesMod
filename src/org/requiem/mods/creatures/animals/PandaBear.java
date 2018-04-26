
package org.requiem.mods.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.*;
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

public class PandaBear implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_BEAR_PANDA = "mod.creature.bear.panda";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                C_TYPE_MOVE_LOCAL,
                C_TYPE_ANIMAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_DOMINATABLE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MISSION_OK,
                C_TYPE_MISSION_TRAITOR_OK
        };
        final int[] itemsButchered = new int[]{
                ItemList.tooth,
                ItemList.fur,
                ItemList.eye,
                ItemList.bladder,
                ItemList.tooth
        };
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.bear.panda",
                "Panda bear",
                "The panda bear has large, distinctive black patches around its eyes, over the ears, and across its round body.",
                "model.creature.quadraped.bear.panda",
                types, BodyTemplate.TYPE_BEAR,
                (short) 8,
                (byte) 0,
                (short) 160, (short) 50, (short) 50,
                "sound.death.bear", "sound.death.bear", "sound.combat.hit.bear", "sound.combat.hit.bear",
                0.22f,
                6.0f,
                0.0f, 11.0f, 0.0f, 0.0f,
                0.9f,
                500,
                itemsButchered,
                25,
                60
        );
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 30.0f);
        builder.skill(SkillList.BODY_CONTROL, 30.0f);
        builder.skill(SkillList.BODY_STAMINA, 30.0f);
        builder.skill(SkillList.MIND_LOGICAL, 4.0f);
        builder.skill(SkillList.MIND_SPEED, 4.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
        builder.skill(SkillList.SOUL_DEPTH, 4.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 35.0f);
        builder.handDamString("maul");
        builder.maxAge(120);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.baseCombatRating(9.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.maxGroupAttackSize(4);
        builder.denName("bear cave");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPopulationOfCreatures(60);
        builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
        builder.sizeModifier(110, 110, 110);
        builder.usesNewAttacks(true);
        builder.addPrimaryAttack(new AttackAction("maul", AttackIdentifier.STRIKE, new AttackValues(6.0f, 0.01f, 6.0f, 3, 1, Wound.TYPE_CRUSH, false, 2, 1.0f)));
        builder.addPrimaryAttack(new AttackAction("gnaw", AttackIdentifier.BITE, new AttackValues(6.0f, 0.02f, 8.0f, 3, 1, Wound.TYPE_BITE, false, 4, 1.1f)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(12.0f, 0.05f, 6.0f, 2, 1, Wound.TYPE_BITE, false, 3, 1.1f)));
        builder.addSecondaryAttack(new AttackAction("scratch", AttackIdentifier.STRIKE, new AttackValues(9.0f, 0.05f, 6.0f, 2, 1, Wound.TYPE_SLASH, false, 8, 1.0f)));
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_BEAR);
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
                vehicle.setSeatFightMod(0, 0.8f, 1.1f);
                vehicle.setSeatOffset(0, 0.0f, 0.0f, 0.0f);
                vehicle.setCreature(true);
                vehicle.setSkillNeeded(23.0f);
                vehicle.setName(creature.getName());
                vehicle.setMaxHeightDiff(0.04f);
                vehicle.setMaxDepth(-0.7f);
                vehicle.setMaxSpeed(20.0f);
                vehicle.setCommandType((byte) 3);
            }
        };
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