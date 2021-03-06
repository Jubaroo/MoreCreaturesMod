
package org.requiem.mods.morecreatures.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.behaviours.Vehicle;
import com.wurmonline.server.creatures.*;
import com.wurmonline.server.items.Item;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviour;
import org.gotti.wurmunlimited.modsupport.vehicles.VehicleFacade;
import org.requiem.mods.morecreatures.CreatureMod;

public class Worg implements ModCreature {

	public static int templateId;
	
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		templateId = CreatureTemplateIds.WORG_CID;
		return new CreatureTemplateBuilder(templateId) {
			public CreatureTemplate build() {
				try {
					return CreatureTemplateFactory.getInstance().getTemplate(templateId);
				} catch (NoSuchCreatureTemplateException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	public ModVehicleBehaviour getVehicleBehaviour() {
		return new ModVehicleBehaviour() {

			public void setSettingsForVehicle(Item item, Vehicle vehicle) {
			}

			public void setSettingsForVehicle(Creature creature, Vehicle v) {
				VehicleFacade vehicle = wrap(v);

				vehicle.createPassengerSeats(0);
				vehicle.setSeatFightMod(0, 0.8f, 1.1f);
				vehicle.setSeatOffset(0, -0.2f, 0.0f, -0.1f);
				vehicle.setCreature(true);
				vehicle.setSkillNeeded(30.0f);
				vehicle.setName(creature.getName());
				vehicle.setMaxHeightDiff(0.07f);
				vehicle.setMaxDepth(-1.7f);
				vehicle.setMaxSpeed(50.0f);
				vehicle.setCommandType((byte) 3);
				vehicle.setCanHaveEquipment(false);
			}
		};
	}
	
	@Override
	public void addEncounters() {
		if (templateId == 0)
			return;

		if (CreatureMod.monsters) {
			new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 2).build(1);
			new EncounterBuilder(Tiles.Tile.TILE_STEPPE.id).addCreatures(templateId, 2).build(2);
		}
	}
}
