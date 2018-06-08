
package org.requiem.mods.morecreatures.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

public class SolDemon implements ModCreature {

	public static int templateId;
	
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		templateId = CreatureTemplateIds.DEMON_SOL_CID;
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
	
	@Override
	public void addEncounters() {
		if (templateId == 0)
			return;

		if (CreatureMod.monsters) {
			new EncounterBuilder(Tiles.Tile.TILE_SAND.id).addCreatures(templateId, 1).build(1);
			new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM.id).addCreatures(templateId, 1).build(3);
		}
	}
}
