package org.requiem.mods.morecreatures.creatures.monsters;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.creatures.AttackAction;
import com.wurmonline.server.creatures.AttackIdentifier;
import com.wurmonline.server.creatures.AttackValues;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class Cyclops implements ModCreature, CreatureTypes {

    private static final String CYCLOPS = "mod.creature.cyclops";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        
        final int[] types = {C_TYPE_MOVE_LOCAL, C_TYPE_AGG_HUMAN, C_TYPE_FENCEBREAKER, C_TYPE_HUNTING, C_TYPE_MONSTER, C_TYPE_REGENERATING, C_TYPE_OMNIVORE, C_TYPE_CLIMBER, C_TYPE_DOMINATABLE, C_TYPE_DETECTINVIS, C_TYPE_NON_NEWBIE, C_TYPE_OPENDOORS, C_TYPE_MISSION_OK, C_TYPE_MISSION_TRAITOR_OK};
        final int[] itemsButchered = new int[]{ tooth, tooth, eye, gland };
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(CYCLOPS, "Cyclops", "This large drooling one-eyed giant is obviously too stupid to feel any mercy. Maybe it is related to the fabled Kyklops!", "model.creature.humanoid.kyklops", types, BodyTemplate.TYPE_CYCLOPS, (short) 5, (byte) 0,
                (short) 270, (short) 60, (short) 60, "sound.death.giant", "sound.death.giant", "sound.combat.hit.giant", "sound.combat.hit.giant", 0.3F, 8.0F, 4.0F, 12.0F, 0.0F, 0.0F, 1.2F, 2000, itemsButchered, 35, 94);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 40.0F);
        builder.skill(BODY_CONTROL, 25.0F);
        builder.skill(BODY_STAMINA, 40.0F);
        builder.skill(MIND_LOGICAL, 10.0F);
        builder.skill(MIND_SPEED, 10.0F);
        builder.skill(SOUL_STRENGTH, 39.0F);
        builder.skill(SOUL_DEPTH, 10.0F);
        builder.skill(WEAPONLESS_FIGHTING, 40.0F);
        builder.skill(CLUB_HUGE, 50.0F);
        
        builder.alignment(0.0F);
        builder.maxAge(110);
        builder.armourType(ArmourTypes.ARMOUR_CLOTH);
        builder.sex((byte) 0);
        builder.baseCombatRating(10.0F);
        builder.combatDamageType(Wound.TYPE_PIERCE);
        builder.maxGroupAttackSize(8);
        builder.hasHands(true);
        builder.denName("cyclops mound");
        builder.denMaterial(ItemMaterials.MATERIAL_STONE);
        builder.sizeModifier(21, 21, 21);
        builder.boundsValues(-0.5F, -0.5F, 0.5F, 0.5F);
        builder.usesNewAttacks(true);
        builder.addPrimaryAttack(new AttackAction("maul", AttackIdentifier.MAUL, new AttackValues(10.0F, 0.1F, 6.0F, 4, 2, Wound.TYPE_CRUSH, true, 3, 1.5F)));
        builder.addPrimaryAttack(new AttackAction("strike", AttackIdentifier.STRIKE, new AttackValues(5.0F, 0.03F, 4.0F, 3, 1, Wound.TYPE_CRUSH, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(3.0F, 0.09F, 5.0F, 3, 1, Wound.TYPE_CRUSH, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(4.0F, 0.03F, 7.0F, 3, 1, Wound.TYPE_BITE, false, 3, 2.0F)));
        builder.maxPopulationOfCreatures(50);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID);
        
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.monsters) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(3);
            new EncounterBuilder(Tiles.Tile.TILE_MARSH.id).addCreatures(templateId, 1).build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CLAY.id).addCreatures(templateId, 1).build(1);
        }
    }
}

