package org.requiem.mods.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class ForestSpider implements ModCreature, CreatureTypes {
	public static int templateId;
	
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		int[] types = {
			CreatureTypes.C_TYPE_MOVE_LOCAL,
			CreatureTypes.C_TYPE_AGG_HUMAN,
			CreatureTypes.C_TYPE_HUNTING,
			CreatureTypes.C_TYPE_ANIMAL,
			CreatureTypes.C_TYPE_CARNIVORE,
			CreatureTypes.C_TYPE_NON_NEWBIE
		};
		
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.forest.spider", "Forest spider", "Found amongst trees, these spiders are dangerous and poisonous.",
				"model.creature.multiped.spider.huge", types, BodyTemplate.TYPE_SPIDER, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
				"sound.death.spider", "sound.death.spider", "sound.combat.hit.spider", "sound.combat.hit.spider", 
				0.6f, 10f, 0f, 13.0f, 0.0f, 0.0f, 1.2f, 500,
				new int[]{ItemList.heart}, 10, 74, Materials.MATERIAL_MEAT_INSECT);
		
		builder.skill(SkillList.BODY_STRENGTH, 20.0f);
		builder.skill(SkillList.BODY_STAMINA, 30.0f);
		builder.skill(SkillList.BODY_CONTROL, 35.0f);
		builder.skill(SkillList.MIND_LOGICAL, 30.0f);
		builder.skill(SkillList.MIND_SPEED, 30.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 30.0f);
		builder.skill(SkillList.SOUL_DEPTH, 30.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 50.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 30.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("pierce");
		builder.maxAge(100);
		builder.armourType(ArmourTypes.ARMOUR_CLOTH);
		builder.baseCombatRating(13.0f);
		builder.combatDamageType(Wound.TYPE_POISON);
		builder.denMaterial(Materials.MATERIAL_STONE);
		builder.denName("forest spider lair");
		builder.maxPercentOfCreatures(0.05f);
		builder.maxGroupAttackSize(100);
		
		templateId = builder.getTemplateId();
		return builder;
	}
	
	@Override
	public void addEncounters() {
		if (templateId == 0) {
			return;
		}
		if (CreatureMod.monsters) {
			new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
					.addCreatures(templateId, 1)
					.build(2);

			new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
					.addCreatures(templateId, 1)
					.build(2);
		}
	}
}
