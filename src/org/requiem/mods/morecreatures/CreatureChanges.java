
package org.requiem.mods.morecreatures;

import com.wurmonline.server.Server;
import com.wurmonline.server.behaviours.AutoEquipMethods;
import com.wurmonline.server.combat.Archery;
import com.wurmonline.server.creatures.*;
import com.wurmonline.server.items.Item;
import com.wurmonline.server.items.ItemFactory;
import com.wurmonline.server.items.ItemList;
import com.wurmonline.server.items.Materials;
import com.wurmonline.server.players.Player;
import com.wurmonline.server.skills.SkillList;
import com.wurmonline.server.skills.Skills;
import com.wurmonline.server.skills.SkillsFactory;
import com.wurmonline.server.zones.NoSuchZoneException;
import com.wurmonline.server.zones.Zone;
import com.wurmonline.server.zones.Zones;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Descriptor;
import mod.sin.lib.Util;
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

import java.util.logging.Level;

import static com.wurmonline.server.creatures.CreatureTemplateIds.BLUE_WHALE_CID;
import static com.wurmonline.server.creatures.CreatureTemplateIds.WOLF_BLACK_CID;
import static javassist.CtClass.*;
import static mod.sin.lib.Util.*;
import static org.gotti.wurmunlimited.modsupport.creatures.ModCreatures.addCreature;
import static org.requiem.mods.morecreatures.CreatureMod.*;

public class CreatureChanges {

    public static void addNewCreatures () {
        if (logging) { CreatureMod.logger.info("Registering Animals"); }
        addCreature(new Anaconda());
        addCreature(new BlackWidow());
        addCreature(new Gorilla());
        addCreature(new Hyena());
        addCreature(new LargeBoar());
        addCreature(new LargeRat());
        addCreature(new Panther());
        addCreature(new WarHound());
        addCreature(new WolfPackmaster());
        if (logging) { CreatureMod.logger.info("Registering Mounts"); }
        addCreature(new RainbowUnicorn());
        addCreature(new GhostHellHorse());
        addCreature(new GhostHorse());
        addCreature(new Unicorn());
        addCreature(new RainbowUnicorn());
        addCreature(new Reindeer());
        if (logging) { CreatureMod.logger.info("Registering Halloween Creatures"); }
        addCreature(new OminousTree());
        addCreature(new ScaryPumpkin());
        if (logging) { CreatureMod.logger.info("Registering Monsters"); }
        addCreature(new Blob());
        addCreature(new Goblin());
        addCreature(new LavaFiend());
        addCreature(new SolDemon());
        addCreature(new Worg());
        addCreature(new Cyclops());
        if (logging) { CreatureMod.logger.info("Registering NPCs"); }
        addCreature(new TombRaider());
        addCreature(new Wagoner());
        if (logging) { CreatureMod.logger.info("Registering Treasure Goblin"); }
        addCreature(new TreasureGoblin());
        if (logging) { CreatureMod.logger.info("Registering Undead Creatures"); }
        addCreature(new Skeleton());
        addCreature(new ZombieWalker());
        addCreature(new ZombieHulk());
        addCreature(new ZombieLeader());
    }

    public static void modifyNewCreature(Creature creature,/* Item item,*/ boolean transmitTwitter){
        try{
            if (creature.getTemplate().getTemplateId() == TreasureGoblin.templateId) {
                if (goblinAnnouncement) { Server.getInstance().broadCastAlert("A Treasure Goblin has surfaced. Be quick to kill it before someone else claims all the loot!", transmitTwitter); }
                try {
                    final Item i = ItemFactory.createItem(ItemList.treasureChest, 50 + Server.rand.nextInt(30),  Materials.MATERIAL_WOOD_BIRCH, (byte) 1, null);
                    i.setAuxData((byte)Server.rand.nextInt(8));
                    if (i.getAuxData() > 4) {
                        i.setRarity((byte) 2);
                    }
                    i.fillTreasureChest();
                    creature.getInventory().insertItem(i);
                    if (creatureLogging) { CreatureMod.logger.info("===== Treasure Chest added to Treasure Goblin ===="); }
                }
                catch (Exception fe) {
                    if (logging) { CreatureMod.logger.log(Level.WARNING, "===== Failed to add treasure chest to Treasure Goblin: " + creature.getWurmId() + fe.getMessage() + " =====", fe); }
                }
                final Item rareBone = ItemFactory.createItem(ItemList.boneCollar, 50.0f + Server.rand.nextInt(49), Materials.MATERIAL_BONE, (byte) (1 + Server.rand.nextInt(rarityLimit)), null);
                creature.getInventory().insertItem(rareBone);
                if (creatureLogging) { CreatureMod.logger.info("===== Rare bone added to Treasure Goblin ===="); }
                final Item source = ItemFactory.createItem(ItemList.sourceSalt, 50.0f + Server.rand.nextInt(49), Materials.MATERIAL_MAGIC, (byte) 0, null);
                // x5 source salt
                creature.getInventory().insertItem(source);creature.getInventory().insertItem(source);creature.getInventory().insertItem(source);creature.getInventory().insertItem(source);creature.getInventory().insertItem(source);
                if (creatureLogging) { CreatureMod.logger.info("===== Source salt added to Treasure Goblin ===="); }
                final Item coin = ItemFactory.createItem(ItemList.coinSilverFive, 50.0f + Server.rand.nextInt(49), Materials.MATERIAL_SILVER, (byte) Server.rand.nextInt(rarityLimit), null);
                creature.getInventory().insertItem(coin);
                if (creatureLogging) { CreatureMod.logger.info("===== Silver five coin added to Treasure Goblin ===="); }
            }
            if (creature.getTemplate().getTemplateId() == Cyclops.templateId) {
                final Item club = ItemFactory.createItem(ItemList.clubHuge, 50.0f + Server.rand.nextInt(49), Materials.MATERIAL_WOOD_BIRCH, (byte) 0, "cyclops");
                creature.getInventory().insertItem(club);
                // TODO make Cyclops equip club
                //try {
                //    AutoEquipMethods.autoEquip(item.getTemplateId(), cyclops, 0, null);
                //}catch(Exception e){
                //    if (logging) { CreatureMod.logger.log(Level.WARNING, "===== Failed to equip shod club on Cyclops: " + creature.getWurmId() + e.getMessage() + " =====", e); }
                //    e.printStackTrace();
                //}
                if (creatureLogging) { CreatureMod.logger.info("===== Shod club added to Cyclops ===="); }
            }
            if (creature.getTemplate().getTemplateId() == ScaryPumpkin.templateId) {
                final Item pumpkin = ItemFactory.createItem(ItemList.pumpkin, 10.0f + Server.rand.nextInt(89), Materials.MATERIAL_VEGETARIAN, (byte) 0, null);
                creature.getInventory().insertItem(pumpkin);
                if (creatureLogging) { CreatureMod.logger.info("===== Pumpkin added to Scary Pumpkin ===="); }
            }
            if (creature.getTemplate().getTemplateId() == OminousTree.templateId) {
                final Item log = ItemFactory.createItem(ItemList.log, 10.0f + Server.rand.nextInt(89), Materials.MATERIAL_WOOD_OAK, (byte) 0, null);
                creature.getInventory().insertItem(log);
                if (creatureLogging) { CreatureMod.logger.info("===== Log added to Ominous Tree ===="); }
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

            // =============== Set corpse models ================
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

            if (CreatureMod.monsters) { setCorpseModel(Cyclops.templateId, "kyklops."); }

            // =============== Domesticate whales and enable milking =================
            if (CreatureMod.milkWhales) {
                setMilkable(BLUE_WHALE_CID);
                setDomestic(BLUE_WHALE_CID);
            }

            // ============== Set leaderTemplateId ================
            if (CreatureMod.animals) { setleaderTemplateId(WOLF_BLACK_CID, WolfPackmaster.templateId); }
            if (CreatureMod.undead) {
                setleaderTemplateId(ZombieWalker.templateId, ZombieLeader.templateId);
                setleaderTemplateId(ZombieHulk.templateId, ZombieLeader.templateId);
            }

        } catch ( IllegalArgumentException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasCustomCorpseSize(Creature creature){
        int templateId = creature.getTemplate().getTemplateId();
        if(templateId == Cyclops.templateId){
            return true;
        } else if(templateId == ZombieLeader.templateId){
            return true;
        } else if(templateId == ZombieHulk.templateId){
            return true;
        } else if(templateId == WarHound.templateId){
            return true;
        } else if(templateId == Reindeer.templateId){
            return true;
        } else if(templateId == WolfPackmaster.templateId){
            return true;
        }
        else{
            return false;
        }
    }

    public static void setCorpseSizes(Creature creature, Item corpse){
        if(corpse.getTemplateId() != ItemList.corpse){
            return;
        }
        int templateId = creature.getTemplate().getTemplateId();
        boolean sendStatus = false;
        int size = 50000;
        if(templateId == Cyclops.templateId){
            size *= 0.21;
            corpse.setSizes(size);
            sendStatus = true;
        }else if(templateId == WarHound.templateId){
            size *= 0.3;
            corpse.setSizes(size);
            sendStatus = true;
        }else if(templateId == ZombieLeader.templateId){
            size *= 2.0;
            corpse.setSizes(size);
            sendStatus = true;
        }else if(templateId == ZombieHulk.templateId){
            size *= 1.5;
            corpse.setSizes(size);
            sendStatus = true;
        }else if(templateId == Reindeer.templateId){
            size *= 0.6;
            corpse.setSizes(size);
            sendStatus = true;
        }else if(templateId == WolfPackmaster.templateId){
            size *= 1.2;
            corpse.setSizes(size);
            sendStatus = true;
        }else{
            corpse.setSizes((int)((float)(corpse.getSizeX() * (creature.getSizeModX() & 255)) / 64.0f), (int)((float)(corpse.getSizeY() * (creature.getSizeModY() & 255)) / 64.0f), (int)((float)(corpse.getSizeZ() * (creature.getSizeModZ() & 255)) / 64.0f));
        }
        if(sendStatus){
            try {
                Zone zone = Zones.getZone((int)corpse.getPosX() >> 2, (int)corpse.getPosY() >> 2, corpse.isOnSurface());
                zone.removeItem(corpse, true, true);
                zone.addItem(corpse, true, false, false);
            } catch (NoSuchZoneException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void sendParticleEffect(Communicator comm, long creatureId, Creature creature, String particle, float duration){
        comm.sendAddEffect(creatureId, (short) 27, creature.getPosX(), creature.getPosY(), creature.getPositionZ(), (byte) creature.getLayer(), particle, duration, 0);
    }

    protected static void sendAddEffect(Communicator comm, long creatureId, byte effectNum){
        comm.sendAttachEffect(creatureId, effectNum, (byte) 1, (byte) -1, (byte) -1, (byte) 1);
    }

    // - Good Effects -
    // rift01 [large], rift02 [small]
    // treasureP [light bubbles]
    // spawneffect2 [sparkle fireworks]
    // reindeer [light sparkles]
    // iceBall_1_1 [clean ice effect]
    // acidBall_1_1 [clean green ball effect]
    // byte 1 [fire]
    // - Bad Effects -
    // spawneffect [sparkle eye cancer]
    // snow1emitter [disappears instantly}, snow2emitter [disappears instantly]
    // spawneffectshort [disappears instantly]
    // iceWispSpark [tiny and unnoticeable]
    // iceBolt1 [very flickery]
    // iceTail1 [small and points downwards]
    // acidWispSpark [basically invisible]
    // acidTail1 [inconsistent trail]
    // acidBolt1 [very flickery]
    // lightningTail1 [weird effect]
    // TODO Creatures not getting effects - FIX ME
    public static void addCreatureSpecialEffect(long creatureId, Communicator comm, Creature creature){
        int templateId = creature.getTemplate().getTemplateId();
        if(templateId == TreasureGoblin.templateId){
            String particle = "treasureP";
            sendParticleEffect(comm, creatureId, creature, particle, Float.MAX_VALUE);
        } else if (templateId == Blob.templateId){
            String particle = "acidBall_1_1";
            sendParticleEffect(comm, creatureId, creature, particle, Float.MAX_VALUE);
        }
    }
// TODO Fix ME
    public static boolean isArcheryImmune(Creature performer, Creature defender, Creature creature){
        int templateId = creature.getTemplate().getTemplateId();
        if(templateId == TreasureGoblin.templateId) {
            performer.getCommunicator().sendCombatNormalMessage("You cannot archer "+defender.getName()+", as it is protected from ranged attacks.");
            return true;
        } else if (templateId == Blob.templateId) {
            performer.getCommunicator().sendCombatNormalMessage("You cannot archer "+defender.getName()+", as it is protected from ranged attacks.");
            return true;
        }
        String message = "The "+defender.getName()+" would be unaffected by your arrows.";
        boolean immune = false;
        Item arrow = Archery.getArrow(performer);
        if(arrow == null){ // Copied directly from the attack() method in Archery.
            performer.getCommunicator().sendCombatNormalMessage("You have no arrows left to shoot!");
            return true;
        }
        //int defenderTemplateId = defender.getTemplate().getTemplateId();
        if(immune){
            performer.getCommunicator().sendCombatNormalMessage(message);
        }
        return immune;
    }

    public static boolean isSacrificeImmune(Creature creature){
        int templateId = creature.getTemplate().getTemplateId();
        return templateId == TreasureGoblin.templateId;
    }

    public static boolean denyPathingOverride(Creature creature) {
        if (creature.getTemplate().getTemplateId() == GhostHellHorse.templateId) {
            return true;
        } else if (creature.getTemplate().getTemplateId() == GhostHorse.templateId) {
            return true;
            }
        return false;
    }

    public static boolean isGhostCorpse(Creature creature){
        int templateId = creature.getTemplate().getTemplateId();
        if(templateId == GhostHorse.templateId){
            return true;
        }else if(templateId == GhostHellHorse.templateId){
            return true;
        }else if(templateId == BlackWidow.templateId){
            return true;
        }
        return false;
    }

    public static void preInit(){
        try{
            ClassPool classPool = HookManager.getInstance().getClassPool();
            final Class<CreatureChanges> thisClass = CreatureChanges.class;
            String replace;

            setReason("Disable sacrificing strong creatures.");
            CtClass ctCreature = classPool.get("com.wurmonline.server.creatures.Creature");
            CtClass ctItem = classPool.get("com.wurmonline.server.items.Item");
            CtClass ctAction = classPool.get("com.wurmonline.server.behaviours.Action");
            CtClass ctMethodsReligion = classPool.get("com.wurmonline.server.behaviours.MethodsReligion");
            CtClass[] params1 = {
                    ctCreature,
                    ctCreature,
                    ctItem,
                    ctAction,
                    floatType
            };
            String desc1 = Descriptor.ofMethod(booleanType, params1);
            replace = "if("+CreatureChanges.class.getName()+".isSacrificeImmune($2)){" +
                    "  performer.getCommunicator().sendNormalServerMessage(\"This creature cannot be sacrificed.\");" +
                    "  return true;" +
                    "}";
            insertBeforeDescribed(thisClass, ctMethodsReligion, "sacrifice", desc1, replace);

            setReason("Deny ghost animals walking through walls.");
            CtClass ctPathFinder = classPool.get("com.wurmonline.server.creatures.ai.PathFinder");
            replace = "if("+CreatureChanges.class.getName()+".denyPathingOverride($0)){" +
                    "  $_ = false;" +
                    "}else{" +
                    "  $_ = $proceed($$);" +
                    "}";
            instrumentDeclared(thisClass, ctPathFinder, "canPass", "isGhost", replace);
            instrumentDeclared(thisClass, ctCreature, "setPathing", "isGhost", replace);
            instrumentDeclared(thisClass, ctCreature, "startPathingToTile", "isGhost", replace);
            instrumentDeclared(thisClass, ctCreature, "moveAlongPath", "isGhost", replace);
            instrumentDeclared(thisClass, ctCreature, "takeSimpleStep", "isGhost", replace);
            instrumentDeclared(thisClass, ctCreature, "die", "isGhost", replace);

            setReason("Disable archery altogether against certain creatures.");
            CtClass ctArchery = classPool.get("com.wurmonline.server.combat.Archery");
            CtClass[] params3 = {
                    ctCreature,
                    ctCreature,
                    ctItem,
                    floatType,
                    ctAction
            };
            String desc3 = Descriptor.ofMethod(booleanType, params3);
            replace = "if("+CreatureChanges.class.getName()+".isArcheryImmune($1, $2)){"
                    + "  return true;"
                    + "}";
            insertBeforeDescribed(thisClass, ctArchery, "attack", desc3, replace);

            setReason("Set custom corpse sizes.");
            replace = "$_ = $proceed($$);"
                    + "if("+CreatureChanges.class.getName()+".hasCustomCorpseSize(this)){"
                    + "  "+CreatureChanges.class.getName()+".setCorpseSizes(this, corpse);"
                    + "}";
            instrumentDeclared(thisClass, ctCreature, "die", "addItem", replace);

            setReason("Allow ghost creatures to drop corpses.");
            replace = "if("+CreatureChanges.class.getName()+".isGhostCorpse(this)){"
                    + "  $_ = false;"
                    + "}else{"
                    + "  $_ = $proceed($$);"
                    + "}";
            instrumentDeclared(thisClass, ctCreature, "die", "isGhost", replace);

            setReason("Attach special effects to creatures.");
            CtClass ctVirtualZone = classPool.get("com.wurmonline.server.zones.VirtualZone");
            CtClass[] params4 = {
                    longType,
                    booleanType,
                    longType,
                    floatType,
                    floatType,
                    floatType
            };
            String desc4 = Descriptor.ofMethod(booleanType, params4);
            replace = "$_ = $proceed($$);" +
                    CreatureChanges.class.getName()+".addCreatureSpecialEffect(copyId != -10 ? copyId : creatureId, $0, creature);";
            instrumentDescribed(thisClass, ctVirtualZone, "addCreature", desc4, "sendNewCreature", replace);

        } catch (  NotFoundException | IllegalArgumentException | ClassCastException e) {
            throw new HookException(e);
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
            if (creatureLogging) { Util.insertBeforeDescribed(thisClass, ctCreature, "doNew", desc, "logger.info(\"Creating new creature: \"+templateid+\" - \"+(aPosX/4)+\", \"+(aPosY/4)+\" [\"+com.wurmonline.server.creatures.CreatureTemplateFactory.getInstance().getTemplate(templateid).getName()+\"]\");"); }

            // Modify new creatures
            replace = "$_ = $proceed($$);"
                    + CreatureChanges.class.getName()+".modifyNewCreature($1," + CreatureMod.sendToTwitter + ");";
            Util.instrumentDescribed(thisClass, ctCreature, "doNew", desc, "sendToWorld", replace);
        }
        catch (NotFoundException e) {
            throw new HookException(e);
        }
    }

}