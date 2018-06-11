
package org.requiem.mods.morecreatures.creatures.npc;

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

public class TombRaider implements ModCreature, CreatureTypes {

    private static final String TOMB_RAIDER = "morecreatures.tombraider";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = {C_TYPE_MOVE_GLOBAL, C_TYPE_AGG_HUMAN, C_TYPE_SWIMMING, C_TYPE_HUNTING, C_TYPE_HUMAN};
        final int[] itemsButchered = {leather, arrowHunting, cookedMeat, riftCrystal, goldBar, goldBar};
        
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(TOMB_RAIDER, "Tomb Raider", "Tomb raiders travel the world looking for treasures. They are formidable opponents to fight as they can stun with their whips.", "model.creature.humanoid.human.wagoner", types, BodyTemplate.TYPE_HUMAN, (short) 2, (byte) 0,
                (short) 180, (short) 20, (short) 35, "sound.death.male", "sound.death.female", "sound.combat.hit.male", "sound.combat.hit.female", 0.11F, 5.0f, 5.0f, 0.0f, 0.0f, 0.0f, 1.25f, 2500, itemsButchered, 35, 5);
        
        templateId = builder.getTemplateId();
        
        builder.skill(BODY_STRENGTH, 30.0F);
        builder.skill(BODY_CONTROL, 45.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 40.0F);
        builder.skill(MIND_SPEED, 40.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(SOUL_DEPTH, 20.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0f);
        
        builder.hasHands(true);
        builder.baseCombatRating(25.0F);
        builder.maxAge(100);
        builder.armourType(ArmourTypes.ARMOUR_STUDDED);
        builder.alignment(0.0f);
        builder.combatDamageType(Wound.TYPE_SLASH);
        builder.handDamString("whip");
        builder.handDamString("strike");
        builder.kickDamString("kick");
        builder.setCombatMoves(new int[]{CombatMove.STUN});
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMAN);
        
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0) {
            return;
        }
        if (CreatureMod.aggressiveNPC) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        }
    }
}