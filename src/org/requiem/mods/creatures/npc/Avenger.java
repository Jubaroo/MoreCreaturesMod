package org.requiem.mods.creatures.npc;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class Avenger implements ModCreature, CreatureTypes {
	public static int templateId;
	
	@Override
	public CreatureTemplateBuilder createCreateTemplateBuilder() {
		int[] types = {
			C_TYPE_MOVE_LOCAL,
			C_TYPE_AGG_HUMAN,
			C_TYPE_HUNTING,
			C_TYPE_MONSTER,
			C_TYPE_CARNIVORE,
			C_TYPE_DOMINATABLE,
			C_TYPE_DETECTINVIS,
			C_TYPE_NON_NEWBIE,
			C_TYPE_NO_REBIRTH
		};
		
		CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.avenger",
				"Avenger",
				"Sent from the heavens to purge the unworthy.",
				"model.creature.humanoid.avenger.light",
				types, BodyTemplate.TYPE_HUMAN,
				(short) 5,
				(byte) 0,
				(short) 85, (short) 50, (short) 85,
				"sound.death.giant", "sound.death.giant", "sound.combat.hit.giant", "sound.combat.hit.giant",
				0.3f,
				25f, 0f, 0f, 0.0f, 0.0f,
				1.2f,
				500,
				new int[0],
				15,
				70,
				Materials.MATERIAL_MEAT_HUMANOID
		);
		
		builder.skill(SkillList.BODY_STRENGTH, 45.0f);
		builder.skill(SkillList.BODY_STAMINA, 55.0f);
		builder.skill(SkillList.BODY_CONTROL, 60.0f);
		builder.skill(SkillList.MIND_LOGICAL, 50.0f);
		builder.skill(SkillList.MIND_SPEED, 50.0f);
		builder.skill(SkillList.SOUL_STRENGTH, 50.0f);
		builder.skill(SkillList.SOUL_DEPTH, 50.0f);
		builder.skill(SkillList.WEAPONLESS_FIGHTING, 70.0f);
		builder.skill(SkillList.GROUP_FIGHTING, 50.0f);
		
		builder.boundsValues(-0.5f, -1.0f, 0.5f, 1.42f);
		builder.handDamString("slam");
		builder.maxAge(100);
		builder.armourType(ArmourTypes.ARMOUR_LEATHER);
		builder.baseCombatRating(45.0f);
		builder.combatDamageType(Wound.TYPE_COLD);
		builder.maxPercentOfCreatures(0.005f);
		builder.maxGroupAttackSize(100);
		
		templateId = builder.getTemplateId();
		return builder;
	}
	
	@Override
	public void addEncounters() {
		if (templateId == 0) {
			return;
		}
		if (CreatureMod.npc) {
			new EncounterBuilder(Tiles.Tile.TILE_TUNDRA.id)
					.addCreatures(templateId, 1)
					.build(1);
		}
	}
}
