
package org.requiem.mods.creatures;

import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.server.skills.Skills;
import com.wurmonline.server.skills.SkillsFactory;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.requiem.mods.creatures.animals.LargeBoar;
import org.requiem.mods.creatures.animals.WarHound;
import org.requiem.mods.creatures.animals.WolfPackmaster;
import org.requiem.mods.creatures.custommounts.Charger;
import org.requiem.mods.creatures.custommounts.HornedPony;
import org.requiem.mods.creatures.monsters.BlackWidow;
import org.requiem.mods.creatures.monsters.ForestSpider;
import org.requiem.mods.creatures.monsters.Giant;
import org.requiem.mods.creatures.monsters.SpiritTroll;
import org.requiem.mods.creatures.npc.Avenger;
import org.requiem.mods.creatures.undead.Skeleton;
import org.requiem.mods.creatures.undead.Zombie;
import org.requiem.mods.creatures.uniques.KingKong;

public class CreatureChanges {

    private static void setCorpseModel(int templateId, String model){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "corpsename"), model);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void setGhost(int templateId){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "ghost"), true);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void setMilkable(int templateId){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "milkable"), true);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void setDomestic(int templateId){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "domestic"), true);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void setLeaderTemplateId(int templateId){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "leaderTemplateId"), true);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void setWorgFields(int templateId) {
        try {
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null) {
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "isVehicle"), true);
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "dominatable"), true);
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "isHorse"), true);
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "isDetectInvis"), false);
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "monster"), true);
                Skills skills = SkillsFactory.createSkills("Worg");
                skills.learnTemp(SkillList.BODY_STRENGTH, 40.0f);
                skills.learnTemp(SkillList.BODY_CONTROL, 25.0f);
                skills.learnTemp(SkillList.BODY_STAMINA, 35.0f);
                skills.learnTemp(SkillList.MIND_LOGICAL, 10.0f);
                skills.learnTemp(SkillList.MIND_SPEED, 15.0f);
                skills.learnTemp(SkillList.SOUL_STRENGTH, 20.0f);
                skills.learnTemp(SkillList.SOUL_DEPTH, 12.0f);
                skills.learnTemp(SkillList.WEAPONLESS_FIGHTING, 50.0f);
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "skills"), skills);
            } // if
        } catch (Exception e) {
            e.printStackTrace();
        } // catch
    } // setWorgFields

    static void setTemplateVariables() {
        try {
            /**
             *  Set corpse models for custom creatures
             *  fieldName is taken from the CreatureTemplate class
             */
            setCorpseModel(Avenger.templateId, "fogspider.");
            setCorpseModel(SpiritTroll.templateId, "fogspider.");
            setCorpseModel(KingKong.templateId, "mountaingorilla.");
            setCorpseModel(WolfPackmaster.templateId, "blackwolf.");
            setCorpseModel(WarHound.templateId, "riftbeast.");
            setCorpseModel(Skeleton.templateId, "skeleton.");
            setCorpseModel(Zombie.templateId, "zombie.butchered.free.");
            setCorpseModel(ForestSpider.templateId, "hugespider.");
            setCorpseModel(Giant.templateId, "forestgiant.");
            setCorpseModel(LargeBoar.templateId, "wildboar.");
            setCorpseModel(HornedPony.templateId, "unicorn.");
            setCorpseModel(BlackWidow.templateId, "fogspider.");
            /**
             *  Apply the ghost modifier
             */
            setGhost(SpiritTroll.templateId);
            setGhost(Avenger.templateId);
            setGhost(Charger.templateId);
            setWorgFields(CreatureTemplate.WORG_CID);
            /**
             *  Apply the milkable modifier
             */
            if (CreatureMod.milkWhales) {
                setMilkable(CreatureTemplateIds.BLUE_WHALE_CID);
            }
            /**
             *  Apply the domestic modifier
             */
            if (CreatureMod.milkWhales) {
                setDomestic(CreatureTemplateIds.BLUE_WHALE_CID);
            }
            /**
             *  Apply the leaderId modifier
             */
            setLeaderTemplateId(WolfPackmaster.templateId);

        } catch ( IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
        }
    }
}