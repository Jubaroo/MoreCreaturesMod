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

public class Giant implements ModCreature, CreatureTypes {
	public static int templateId;
	
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		int[] types = {
			CreatureTypes.C_TYPE_MOVE_LOCAL,
			CreatureTypes.C_TYPE_AGG_HUMAN,
			CreatureTypes.C_TYPE_HUNTING,
			CreatureTypes.C_TYPE_MONSTER,
			CreatureTypes.C_TYPE_CARNIVORE,
			CreatureTypes.C_TYPE_REGENERATING,
			CreatureTypes.C_TYPE_DOMINATABLE,
			CreatureTypes.C_TYPE_DETECTINVIS,
			CreatureTypes.C_TYPE_NON_NEWBIE
		};
		
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.giant", "Giant", "A lumbering hulk.",
				"model.creature.humanoid.giant.forest", types, BodyTemplate.TYPE_ETTIN, (short) 5, (byte) 0, (short) 85, (short) 50, (short) 85,
				"sound.death.giant", "sound.death.giant", "sound.combat.hit.giant", "sound.combat.hit.giant",
				0.3f, 19f, 15f, 0f, 0.0f, 0.0f, 0.7f, 300,
				new int[]{ItemList.heart, ItemList.eye, ItemList.gland, ItemList.tooth}, 15, 70, Materials.MATERIAL_MEAT_TOUGH);
		
		builder.skill(SkillList.BODY_STRENGTH, 55.0f);
		builder.skill(SkillList.BODY_STAMINA, 65.0f);
		builder.skill(SkillList.BODY_CONTROL, 30.0f);
		builder.skill(SkillList.MIND_LOGICAL, 10.0f);
		builder.skill(SkillList.MIND_SPEED, 10.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 10.0f);
		builder.skill(SkillList.SOUL_DEPTH, 10.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 60.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 40.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("maul");
		builder.maxAge(100);
		builder.hasHands(true);
		builder.armourType(ArmourTypes.ARMOUR_LEATHER);
		builder.baseCombatRating(32.0f);
		builder.combatDamageType(Wound.TYPE_ACID);
		builder.denMaterial(Materials.MATERIAL_WOOD_BIRCH);
		builder.denName("giant lair");
		builder.maxPercentOfCreatures(0.001f);
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
			new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
					.addCreatures(templateId, 1)
					.build(1);

			new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
					.addCreatures(templateId, 1)
					.build(1);
		}
	}
}
