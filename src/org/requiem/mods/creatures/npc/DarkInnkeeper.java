package org.requiem.mods.creatures.npc;

import com.wurmonline.server.creatures.CreatureTypes;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

public class DarkInnkeeper implements ModCreature, CreatureTypes {
    private static final String MOD_CREATURE_INNKEEPER_DARK = "mod.creature.innkeeper.dark";
    public static int templateId;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        final int[] types = {C_TYPE_SENTINEL, C_TYPE_TRADER, C_TYPE_INVULNERABLE, C_TYPE_NPC_TRADER, C_TYPE_SWIMMING, C_TYPE_HUMAN, C_TYPE_BARTENDER};
        final int[] itemsButchered = new int [0];
        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.innkeeper.dark", "Dark Innkeeper", "A dark innkeeper eagerly awaits new souls to arrive.", "model.creature.humanoid.jackal.rift", types, (byte) 0, (short) 2, (byte) 0, (short) 180, (short) 20, (short) 35, "sound.death.male", "sound.death.female", "sound.combat.hit.male", "sound.combat.hit.female", 1.0F, 1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0, itemsButchered, 3, 0);
        templateId = builder.getTemplateId();
        builder.skill(SkillList.BODY_STRENGTH, 30.0F);
        builder.skill(SkillList.BODY_CONTROL, 45.0F);
        builder.skill(SkillList.BODY_STAMINA, 35.0F);
        builder.skill(SkillList.MIND_LOGICAL, 40.0F);
        builder.skill(SkillList.MIND_SPEED, 40.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 20.0F);
        builder.skill(SkillList.SOUL_DEPTH, 20.0F);
        builder.skill(SkillList.SOUL_STRENGTH, 20.0F);
        builder.skill(SkillList.WEAPONLESS_FIGHTING, 50.0f);
        builder.hasHands(true);
        builder.baseCombatRating(10.0F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID);
        return builder;
    }

    @Override
    public void addEncounters() {
        return;
    }

}