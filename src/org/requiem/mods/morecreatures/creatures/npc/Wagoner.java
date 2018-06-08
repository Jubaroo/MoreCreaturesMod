package org.requiem.mods.morecreatures.creatures.npc;

import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import static com.wurmonline.server.skills.SkillList.*;

public class Wagoner implements ModCreature, CreatureTypes {

    private static final String WAGONER = "morecreatures.wagoner";
    public static int templateId;

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        final int[] types = {C_TYPE_SENTINEL, C_TYPE_INVULNERABLE, C_TYPE_SWIMMING, C_TYPE_HUMAN};
        final int[] itemsButchered = new int[0];

        final CreatureTemplateBuilder builder = new CreatureTemplateBuilder(WAGONER, "Wagoner", "An NPC that looks like Indiana Jones!", "model.creature.humanoid.human.wagoner", types, BodyTemplate.TYPE_HUMAN, (short) 5, (byte) 0, (short) 180, (short) 20, (short) 35, "sound.death.male", "sound.death.female", "sound.combat.hit.male", "sound.combat.hit.female", 1.0F, 1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0, itemsButchered, 1, 0);

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
        builder.baseCombatRating(10.0F);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMAN);

        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
    }

}