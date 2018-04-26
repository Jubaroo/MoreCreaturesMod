package org.requiem.mods.creatures.custommounts;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;
import org.requiem.mods.creatures.CreatureMod;

public class Charger implements ModCreature, CreatureTypes {
	public static int templateId;
	
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		int[] types = {
				CreatureTypes.C_TYPE_CARNIVORE,
				CreatureTypes.C_TYPE_MOVE_GLOBAL,
				CreatureTypes.C_TYPE_VEHICLE,
				CreatureTypes.C_TYPE_LEADABLE,
				CreatureTypes.C_TYPE_SWIMMING,
				CreatureTypes.C_TYPE_HUNTING,
				CreatureTypes.C_TYPE_DOMINATABLE,
				CreatureTypes.C_TYPE_MONSTER,
				CreatureTypes.C_TYPE_NON_NEWBIE,
				CreatureTypes.C_TYPE_HORSE,
				CreatureTypes.C_TYPE_ANIMAL
		};
		
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.charger", "Charger", "A blazing-fast stallion.",
				"model.creature.quadraped.horse.hell", types, BodyTemplate.TYPE_HORSE, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
				"sound.death.horse", "sound.death.horse", "sound.combat.hit.horse", "sound.combat.hit.horse",
				1.1f, 12f, 10f, 10.0f, 0.0f, 0.0f, 1.6f, 1000,
				new int[]{}, 10, 74, Materials.MATERIAL_MEAT_HORSE);
		
		builder.skill(SkillList.BODY_STRENGTH, 37.0f);
		builder.skill(SkillList.BODY_STAMINA, 30.0f);
		builder.skill(SkillList.BODY_CONTROL, 25.0f);
		builder.skill(SkillList.MIND_LOGICAL, 30.0f);
		builder.skill(SkillList.MIND_SPEED, 20.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
		builder.skill(SkillList.SOUL_DEPTH, 30.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 25.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 20.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("burn");
		builder.maxAge(100);
		builder.armourType(ArmourTypes.ARMOUR_LEATHER);
		builder.baseCombatRating(13.0f);
		builder.combatDamageType(Wound.TYPE_BURN);
		builder.denMaterial(Materials.MATERIAL_WOOD_BIRCH);
		builder.denName("charger lair");
		builder.maxGroupAttackSize(100);
		
		templateId = builder.getTemplateId();
		return builder;
	}
	
	public ModVehicleBehaviour getVehicleBehaviour() {

		return new ModVehicleBehaviour() {

			@Override
			public void setSettingsForVehicle(Item item, Vehicle vehicle) {
			}

			@Override
			public void setSettingsForVehicle(Creature creature, Vehicle v) {
				VehicleFacade vehicle = wrap(v);

				vehicle.createPassengerSeats(0);
				vehicle.setSeatFightMod(0, 0.8f, 1.1f);
				vehicle.setSeatOffset(0, 0.0f, 0.0f, 0.3f);
				vehicle.setCreature(true);
				vehicle.setSkillNeeded(37.0f);
				vehicle.setName(creature.getName());
				vehicle.setMaxHeightDiff(0.09f);
				vehicle.setMaxDepth(-1.7f);
				vehicle.setMaxSpeed(46.0f);
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
			new EncounterBuilder(Tiles.Tile.TILE_STEPPE.id)
					.addCreatures(templateId, 1)
					.build(3);

			new EncounterBuilder(Tiles.Tile.TILE_STEPPE.id)
					.addCreatures(templateId, 1)
					.build(1);
		}
	}
}
