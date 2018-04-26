package org.requiem.mods.creatures.custommounts;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.Servers;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;
import org.requiem.mods.creatures.CreatureMod;

public class HornedPony implements ModCreature, CreatureTypes {
	public static int templateId;
	
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		int[] types = {
				CreatureTypes.C_TYPE_CARNIVORE,
				CreatureTypes.C_TYPE_MOVE_GLOBAL,
				CreatureTypes.C_TYPE_VEHICLE,
				CreatureTypes.C_TYPE_AGG_HUMAN,
				CreatureTypes.C_TYPE_LEADABLE,
				CreatureTypes.C_TYPE_SWIMMING,
				CreatureTypes.C_TYPE_HUNTING,
				CreatureTypes.C_TYPE_DOMINATABLE,
				CreatureTypes.C_TYPE_MONSTER,
				CreatureTypes.C_TYPE_NON_NEWBIE,
				CreatureTypes.C_TYPE_HORSE,
				CreatureTypes.C_TYPE_ANIMAL
		};
		
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.horned.pony", "Horned pony", "Not a unicorn. Unicorns are fictional.",
				"model.creature.quadraped.unicorn", types, BodyTemplate.TYPE_HORSE, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
				"sound.death.horse", "sound.death.horse", "sound.combat.hit.horse", "sound.combat.hit.horse",
				Servers.localServer.PVPSERVER ? 0.75f : 0.4f, 17f, 13f, 15.0f, 0.0f, 0.0f, 1.4f, 800,
				new int[]{ItemList.heart}, 10, 74, Materials.MATERIAL_MEAT_HORSE);
		
		builder.skill(SkillList.BODY_STRENGTH, Servers.localServer.PVPSERVER ? 40.0f : 60.0f);
		builder.skill(SkillList.BODY_STAMINA, 60.0f);
		builder.skill(SkillList.BODY_CONTROL, 55.0f);
		builder.skill(SkillList.MIND_LOGICAL, 40.0f);
		builder.skill(SkillList.MIND_SPEED, 50.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
		builder.skill(SkillList.SOUL_DEPTH, 30.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 50.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 40.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("horn");
		builder.maxAge(100);
		builder.armourType(ArmourTypes.ARMOUR_SCALE_DRAGON);
		builder.baseCombatRating(Servers.localServer.PVPSERVER ? 15.0f : 20.0f);
		builder.combatDamageType(Wound.TYPE_INTERNAL);
		builder.maxPercentOfCreatures(0.001f);
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
				vehicle.setSeatOffset(0, 0.0f, 0.0f, 0.0f);
				vehicle.setCreature(true);
				vehicle.setSkillNeeded(35.0f);
				vehicle.setName(creature.getName());
				vehicle.setMaxHeightDiff(0.08f);
				vehicle.setMaxDepth(-1.7f);
				vehicle.setMaxSpeed(40.0f);
				vehicle.setCommandType((byte) 3);
				vehicle.setCanHaveEquipment(!Servers.localServer.PVPSERVER);
			}
		};
	}
	
	@Override
	public void addEncounters() {
		if (templateId == 0) {
			return;
		}
		if (CreatureMod.customMounts) {
			new EncounterBuilder(Tiles.Tile.TILE_ENCHANTED_GRASS.id)
					.addCreatures(templateId, 1)
					.build(2);
		}
	}
}
