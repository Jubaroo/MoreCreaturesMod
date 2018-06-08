
package org.requiem.mods.morecreatures;

import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.*;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemFactory;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.server.skills.Skills;
import com.wurmonline.server.skills.SkillsFactory;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.requiem.mods.morecreatures.creatures.animals.*;
import org.requiem.mods.morecreatures.creatures.halloween.OminousTree;
import org.requiem.mods.morecreatures.creatures.halloween.ScaryPumpkin;
import org.requiem.mods.morecreatures.creatures.monsters.*;
import org.requiem.mods.morecreatures.creatures.mounts.*;
import org.requiem.mods.morecreatures.creatures.npc.TombRaider;
import org.requiem.mods.morecreatures.creatures.npc.Wagoner;
import org.requiem.mods.morecreatures.creatures.treasuregoblins.TreasureGoblin;
import org.requiem.mods.morecreatures.creatures.undead.Skeleton;
import org.requiem.mods.morecreatures.creatures.undead.ZombieHulk;
import org.requiem.mods.morecreatures.creatures.undead.ZombieLeader;
import org.requiem.mods.morecreatures.creatures.undead.ZombieWalker;
import org.requiem.mods.morecreatures.util.Util;

import java.util.logging.Level;

import static com.wurmonline.server.creatures.CreatureTemplateIds.BLUE_WHALE_CID;
import static com.wurmonline.server.creatures.CreatureTemplateIds.WOLF_BLACK_CID;
import static org.gotti.wurmunlimited.modsupport.creatures.ModCreatures.addCreature;

public class CreatureChanges {

    public static void addNewCreatures () {
        CreatureMod.logger.info("Registering Animals");
        addCreature(new Anaconda());
        addCreature(new BlackWidow());
        addCreature(new Gorilla());
        addCreature(new Hyena());
        addCreature(new LargeBoar());
        addCreature(new LargeRat());
        addCreature(new Panther());
        addCreature(new WarHound());
        addCreature(new WolfPackmaster());
        CreatureMod.logger.info("Registering Mounts");
        addCreature(new RainbowUnicorn());
        addCreature(new GhostHellHorse());
        addCreature(new GhostHorse());
        addCreature(new Unicorn());
        addCreature(new RainbowUnicorn());
        addCreature(new Reindeer());
        CreatureMod.logger.info("Registering Halloween Creatures");
        addCreature(new OminousTree());
        addCreature(new ScaryPumpkin());
        CreatureMod.logger.info("Registering Monsters");
        addCreature(new Blob());
        addCreature(new Goblin());
        addCreature(new LavaFiend());
        addCreature(new SolDemon());
        addCreature(new Worg());
        CreatureMod.logger.info("Registering NPCs");
        addCreature(new TombRaider());
        addCreature(new Wagoner());
        //CreatureMod.logger.info("Registering Treasure Goblin");
        //addCreature(new TreasureGoblin());
        CreatureMod.logger.info("Registering Undead Creatures");
        addCreature(new Skeleton());
        addCreature(new ZombieWalker());
        addCreature(new ZombieHulk());
        addCreature(new ZombieLeader());
    }

    public static void modifyNewCreature(Creature creature, boolean transmitTwitter){
        try{
            if (creature.getTemplate().getTemplateId() == TreasureGoblin.templateId) {
                Server.getInstance().broadCastAlert("A Treasure Goblin has surfaced. Be quick to kill it before someone else claims all the loot!", transmitTwitter);
                try {
                    final Item i = ItemFactory.createItem(ItemList.treasureChest, 50 + Server.rand.nextInt(30),  Materials.MATERIAL_WOOD_BIRCH, (byte) 1, null);
                    i.setAuxData((byte)Server.rand.nextInt(8));
                    if (i.getAuxData() > 4) {
                        i.setRarity((byte)2);
                    }
                    i.fillTreasureChest();
                }
                catch (Exception fe) {
                    CreatureMod.logger.log(Level.WARNING, "Failed to add treasure chest to Treasure Goblin: " + creature.getWurmId() + fe.getMessage(), fe);
                }
                Item rareBone = ItemFactory.createItem(ItemList.boneCollar, 50.0f + Server.rand.nextInt(49), Materials.MATERIAL_BONE, (byte) (1 + Server.rand.nextInt(2)), null);
                creature.getInventory().insertItem(rareBone);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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

    private static void setleaderTemplateId(int templateId, int value){
        try{
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(templateId);
            if(template != null){
                ReflectionUtil.setPrivateField(template, ReflectionUtil.getField(template.getClass(), "leaderTemplateId"), value);
            }
        } catch (NoSuchCreatureTemplateException | IllegalArgumentException | IllegalAccessException | ClassCastException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    static void setWorgFields() {
        try {
            CreatureTemplate template = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.WORG_CID);
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

            // Set corpse models
            if (CreatureMod.animals) {
                setCorpseModel(WolfPackmaster.templateId, "blackwolf.");
                setCorpseModel(WarHound.templateId, "riftbeast.");
                setCorpseModel(LargeBoar.templateId, "wildboar.");
                setCorpseModel(BlackWidow.templateId, "fogspider.");
            }
            if (CreatureMod.undead) {
                setCorpseModel(Skeleton.templateId, "skeleton.");
                setCorpseModel(ZombieWalker.templateId, "zombie.butchered.free.");
                setCorpseModel(ZombieHulk.templateId, "zombie.butchered.free.");
                setCorpseModel(ZombieLeader.templateId, "zombie.butchered.free.");
            }
            if (CreatureMod.treasureGoblin) { setCorpseModel(TreasureGoblin.templateId, "goblin."); }
            // Domesticate whales and enable milking
            if (CreatureMod.milkWhales) {
                setMilkable(BLUE_WHALE_CID);
                setDomestic(BLUE_WHALE_CID);
            }

            // Set leaderTemplateId
            if (CreatureMod.animals) { setleaderTemplateId(WOLF_BLACK_CID, WolfPackmaster.templateId); }

        } catch ( IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            final Class<CreatureChanges> thisClass = CreatureChanges.class;
            CtClass ctCreature = classPool.get("com.wurmonline.server.creatures.Creature");
            String replace;

            // Debugging to show all new creatures created.
            CtClass[] params = {
                    CtClass.intType,
                    CtClass.booleanType,
                    CtClass.floatType,
                    CtClass.floatType,
                    CtClass.floatType,
                    CtClass.intType,
                    classPool.get("java.lang.String"),
                    CtClass.byteType,
                    CtClass.byteType,
                    CtClass.byteType,
                    CtClass.booleanType,
                    CtClass.byteType,
                    CtClass.intType
            };

            String desc = Descriptor.ofMethod(ctCreature, params);
            Util.insertBeforeDescribed(thisClass, ctCreature, "doNew", desc, "logger.info(\"Creating new creature: \"+templateid+\" - \"+(aPosX/4)+\", \"+(aPosY/4)+\" [\"+com.wurmonline.server.creatures.CreatureTemplateFactory.getInstance().getTemplate(templateid).getName()+\"]\");");

            // Modify new creatures
            replace = "$_ = $proceed($$);"
                    + CreatureChanges.class.getName()+".modifyNewCreature($1);";
            Util.instrumentDescribed(thisClass, ctCreature, "doNew", desc, "sendToWorld", replace);
        }

        catch (NotFoundException e) {
            throw new HookException(e);
        }
    }

}