package org.requiem.mods.morecreatures.creatures.undead;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTypes;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.requiem.mods.morecreatures.CreatureMod;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class ZombieHulk implements ModCreature, CreatureTypes {

    private static final String ZOMBIE_HULK = "morecreatures.zombie.hulk";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = { C_TYPE_HUNTING, C_TYPE_MOVE_LOCAL, C_TYPE_AGG_HUMAN, C_TYPE_AGG_WHITIE, C_TYPE_CARNIVORE, C_TYPE_MONSTER, C_TYPE_OPENDOORS, C_TYPE_NO_REBIRTH };
        final int[] itemsButchered = new int[] {tooth, tooth, eye, bladder, gland };

        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(ZOMBIE_HULK, "Zombie Hulk", "A very bleak human-like creature stands here, looking absent-minded. These ones are slow and weak by themselves", "model.creature.humanoid.human.player.zombie", types, BodyTemplate.TYPE_HUMAN, (short)25, (byte)0,
                (short)180, (short)20, (short)35, "sound.death.zombie", "sound.death.zombie", "sound.combat.hit.zombie", "sound.combat.hit.zombie", 0.2f, 16.0f, 0.0f, 17.0f, 0.0f, 0.0f, 0.3f, 500, itemsButchered, 15, 100);

        templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 50.0f);
        builder.skill(BODY_CONTROL, 50.0f);
        builder.skill(BODY_STAMINA, 90.0f);
        builder.skill(MIND_LOGICAL, 20.0f);
        builder.skill(MIND_SPEED, 20.0f);
        builder.skill(SOUL_STRENGTH, 20.0f);
        builder.skill(SOUL_DEPTH, 20.0f);
        builder.skill(WEAPONLESS_FIGHTING, 70.0f);

        builder.armourType(ArmourTypes.ARMOUR_LEATHER);
        builder.baseCombatRating(40.0f);
        builder.combatDamageType(Wound.TYPE_CRUSH);
        builder.maxGroupAttackSize(5);
        builder.maxAge(56);
        builder.alignment(-100.0f);
        builder.handDamString("smash");
        builder.kickDamString("slam");
        builder.headbuttDamString("bite");
        builder.setCombatMoves(new int[]{CombatMove.EARTHSHAKE});
        builder.hasHands (true);
        builder.maxPercentOfCreatures(0.001f);
        builder.sizeModifier(150,150,150);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMAN);
        builder.leaderTemplateId(ZombieLeader.templateId);

        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.undead) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        }
    }
}
