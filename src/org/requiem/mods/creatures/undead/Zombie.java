package org.requiem.mods.creatures.undead;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class Zombie implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_ZOMBIE_WALKER = "mod.creature.zombie.walker";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                C_TYPE_HUNTING,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_AGG_WHITIE,
                C_TYPE_CARNIVORE,
                C_TYPE_HUMAN,
                C_TYPE_OPENDOORS,
                C_TYPE_HERD,
                C_TYPE_NO_REBIRTH
        };
        final int[] itemsButchered = new int[] {
                ItemList.tooth,
                ItemList.tooth,
                ItemList.eye,
                ItemList.bladder,
                ItemList.gland
        };
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.zombie.walker",
                "Zombie Walker",
                "A very bleak human-like creature stands here, looking absent-minded. These ones are slow and weak by themselves",
                "model.creature.humanoid.human.player.zombie",
                types, BodyTemplate.TYPE_HUMAN,
                (short)30,
                (byte)0,
                (short)180, (short)20, (short)35,
                "sound.death.zombie", "sound.death.zombie", "sound.combat.hit.zombie", "sound.combat.hit.zombie",
                1.0f,
                5.0f, 3.0f, 7.0f, 0.0f, 0.0f,
                0.6f,
                50,
                itemsButchered,
                25,
                100
        );
        builder.skill(SkillList.BODY_STRENGTH, 30.0f);
        builder.skill(SkillList.BODY_CONTROL, 20.0f);
        builder.skill(SkillList.BODY_STAMINA, 40.0f);
        builder.skill(SkillList.MIND_LOGICAL, 20.0f);
        builder.skill(SkillList.MIND_SPEED, 20.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 20.0f);
        builder.skill(SkillList.SOUL_DEPTH, 20.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 70.0f);

        builder.armourType(ArmourTypes.ARMOUR_LEATHER);
        builder.baseCombatRating(8.0f);
        builder.combatDamageType(Wound.TYPE_INFECTION);
        builder.maxGroupAttackSize(10);
        builder.maxAge(56);
        builder.alignment(-100.0f);
        builder.handDamString("scratch");
        builder.headbuttDamString("bite");
        builder.hasHands (true);
        builder.maxPercentOfCreatures(0.0005F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMAN);
        builder.paintMode(1);

        templateId = builder.getTemplateId();
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.undead) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MYCELIUM.id)
                    .addCreatures(templateId, 3)
                    .build(3);
        }
    }
}
