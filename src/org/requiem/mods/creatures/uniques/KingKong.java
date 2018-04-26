
package org.requiem.mods.creatures.uniques;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.AttackAction;
import com.wurmonline.server.creatures.AttackIdentifier;
import com.wurmonline.server.creatures.AttackValues;
import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.creatures.CreatureMod;

public class KingKong implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_KONG = "mod.creature.kong";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_AGG_WHITIE,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CLIMBER,
                C_TYPE_OMNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_FENCEBREAKER,
                C_TYPE_OPENDOORS,
                C_TYPE_NO_REBIRTH,
                C_TYPE_SWIMMING,
                C_TYPE_GRAZER
        };
        final int[] itemsButchered = new int[]{
                ItemList.animalHide,
                ItemList.eye,
                ItemList.eye,
                ItemList.appleGreen,
                ItemList.lettuce,
                ItemList.tooth,
                ItemList.tooth
        };
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.kong",
                "Kong",
                "A massive gorilla that has escaped from Skull Island only to land here...",
                "model.creature.humanoid.gorilla.mountain",
                types, BodyTemplate.TYPE_HUMAN,
                (short) 10,
                (byte) 0,
                (short) 210, (short) 50, (short) 50,
                "sound.death.gorilla", "sound.death.gorilla", "sound.combat.hit.gorilla", "sound.combat.hit.gorilla",
                0.6f,
                14.0f, 8.0f, 10.0f, 9.0f, 0.0f,
                1.0f,
                600,
                itemsButchered,
                40,
                100
        );
        builder.skill(SkillList.BODY_STRENGTH, 50.0f);
        builder.skill(SkillList.BODY_CONTROL, 35.0f);
        builder.skill(SkillList.BODY_STAMINA, 60.0f);
        builder.skill(SkillList.MIND_LOGICAL, 58.0f);
        builder.skill(SkillList.MIND_SPEED, 10.0f);
        builder.skill(SkillList.SOUL_STRENGTH, 20.0f);
        builder.skill(SkillList.SOUL_DEPTH, 10.0f);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 75.0f);
        builder.skill(SkillList.GROUP_FIGHTING, 50.0f);

        builder.handDamString("punch");
        builder.armourType(ArmourTypes.ARMOUR_SCALE_DRAGON);
        builder.baseCombatRating(64.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.maxGroupAttackSize(15);
        builder.hasHands(true);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID);
        builder.maxPopulationOfCreatures(1);
        builder.sizeModifier(200, 200, 200);
        builder.setCombatMoves(new int[]{CombatMove.EARTHSHAKE, CombatMove.THROW});
        builder.usesNewAttacks(true);
        builder.addPrimaryAttack(new AttackAction("thunderstomp", AttackIdentifier.STRIKE, new AttackValues(12.0f, 0.1f, 6.0f, 4, 1, Wound.TYPE_CRUSH, false, 2, 1.0f)));
        builder.addPrimaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(15.0f, 0.1f, 8.0f, 3, 1, Wound.TYPE_BITE, false, 4, 1.0f)));
        builder.addSecondaryAttack(new AttackAction("slap", AttackIdentifier.BITE, new AttackValues(13.0f, 0.1f, 6.0f, 2, 1, Wound.TYPE_BITE, false, 3, 1.0f)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.STRIKE, new AttackValues(12.0f, 0.1f, 6.0f, 2, 1, Wound.TYPE_SLASH, false, 8, 1.0f)));

        templateId = builder.getTemplateId();
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.uniques) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }
    }
}