package org.requiem.mods.morecreatures.creatures.animals;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.items.Materials;
import com.wurmonline.shared.constants.CreatureTypes;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class LargeBoar implements ModCreature, CreatureTypes {

	private static final String LARGE_BOAR = "morecreatures.large.boar";
	public static int templateId;
	
	public CreatureTemplateBuilder createCreateTemplateBuilder() {

		int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_AGG_HUMAN, C_TYPE_HUNTING, C_TYPE_ANIMAL, C_TYPE_CARNIVORE, C_TYPE_NON_NEWBIE};
		final int[] itemsButchered = new int[] {  gland, bladder, eye, tail, tallow };

		CreatureTemplateBuilder builder = new CreatureTemplateBuilder(LARGE_BOAR, "Large boar", "A large and strong boar.", "model.creature.quadraped.boar.wild", types, BodyTemplate.TYPE_DOG, (short) 5, (byte) 0,
				(short) 85, (short) 50, (short) 85, "sound.death.pig", "sound.death.pig", "sound.combat.hit.pig", "sound.combat.hit.pig", 0.5f, 8f, 0f, 10.0f, 14.0f, 0.0f, 1.2f, 500, itemsButchered, 10, 74, Materials.MATERIAL_MEAT_PORK);

		templateId = builder.getTemplateId();

		builder.skill(BODY_STRENGTH, 30.0f);
		builder.skill(BODY_STAMINA, 35.0f);
		builder.skill(BODY_CONTROL, 45.0f);
		builder.skill(MIND_LOGICAL, 5.0f);
		builder.skill(MIND_SPEED, 10.0f);
		builder.skill(SOUL_STRENGTH, 34.0f);
		builder.skill(SOUL_DEPTH, 3.0f);
		builder.skill(WEAPONLESS_FIGHTING, 40.0f);
		builder.skill(GROUP_FIGHTING, 30.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("kick");
		builder.maxAge(100);
		builder.armourType(ArmourTypes.ARMOUR_CLOTH);
		builder.baseCombatRating(18.0f);
		builder.combatDamageType(Wound.TYPE_CRUSH);
		builder.denMaterial(Materials.MATERIAL_WOOD_BIRCH);
		builder.denName("boar lair");
		builder.maxPercentOfCreatures(0.01f);
		builder.maxGroupAttackSize(100);
		
		return builder;
	}
	
	public void addEncounters() {
		if (templateId == 0)
			return;

		if (CreatureMod.animals) {
			new EncounterBuilder(Tiles.Tile.TILE_SAND.id).addCreatures(templateId, 1).build(1);
			new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(3);
		}
	}
}
