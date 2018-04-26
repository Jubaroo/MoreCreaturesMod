package org.requiem.mods.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
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

public class Blob implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_BLOB = "mod.creature.blob";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {
                C_TYPE_MOVE_LOCAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MISSION_OK,
                C_TYPE_MISSION_TRAITOR_OK
        };
        final int[] itemsButchered = new int[]{
                ItemList.heart,
                ItemList.fur,
                ItemList.eye,
                ItemList.eye
        };
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.blob",
                "Blob",
                "A gelatinous mass that was discovered inside of a deep cave long lost. It is resistant to magic",
                "model.blob",
                types, BodyTemplate.TYPE_SPIDER,
                (short) 15,
                (byte) 0,
                (short) 150, (short) 100, (short) 150,
                "sound.death.spider",
                "sound.death.spider",
                "sound.combat.hit.spider",
                "sound.combat.hit.spider",
                0.09f,
                3.8f, 0.0f, 4.0f, 0.0f, 0.0f,
                0.8f,
                500,
                itemsButchered,
                15,
                70
        );
        builder.skill(SkillList.BODY_STRENGTH, 30.0F);
        builder.skill(SkillList.BODY_CONTROL, 50.0F);
        builder.skill(SkillList.BODY_STAMINA, 40.0F);
        builder.skill(SkillList.MIND_LOGICAL, 10.0F);
        builder.skill(SkillList.MIND_SPEED, 20.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 5.0F);
        builder.skill(SkillList.SOUL_DEPTH, 5.0F);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 40.0F);

        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_PLATE);
        builder.combatDamageType(Wound.TYPE_ACID);
        builder.alignment(0.0F);
        builder.baseCombatRating(10.0F);
        builder.maxGroupAttackSize(6);
        builder.denName("goop pit");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.maxPercentOfCreatures(0.00005F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT);
        builder.usesNewAttacks(true);
        builder.addPrimaryAttack(new AttackAction("spit", AttackIdentifier.BITE, new AttackValues(12.0F, 0.08F, 1.0F, 10, 1, Wound.TYPE_ACID, false, 3, 3.0F)));
        builder.addSecondaryAttack(new AttackAction("dissolve", AttackIdentifier.STRIKE, new AttackValues(3.8F, 0.03F, 8.0F, 3, 1, Wound.TYPE_ACID, false, 3, 1.0F)));

        templateId = builder.getTemplateId();
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.monsters) {
            new EncounterBuilder(Tiles.Tile.TILE_TAR.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }
    }
}
