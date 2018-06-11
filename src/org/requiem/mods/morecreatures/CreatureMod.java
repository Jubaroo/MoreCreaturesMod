
package org.requiem.mods.morecreatures;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviours;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.valueOf;
import static org.requiem.mods.morecreatures.CreatureChanges.setWorgFields;

public class CreatureMod implements WurmServerMod, Initable, Configurable, ServerStartedListener {

    static Logger logger = Logger.getLogger(CreatureMod.class.getName() + " v1.0");
    public static boolean halloweenMobs = false;
    public static boolean customMounts = false;
    public static boolean undead = false;
    public static boolean monsters = false;
    public static boolean aggressiveNPC = false;
    public static boolean animals = false;
    static boolean milkWhales = false;
    public static boolean treasureGoblin = false;
    static boolean sendToTwitter = false;
    static boolean goblinAnnouncement = false;
    static boolean creatureLogging = false;
    static int rarityLimit = 3;
    public static boolean logging = false;

    @Override
    public void configure(Properties properties) {
        if (logging) { CreatureMod.logger.log(Level.INFO, "Configure called"); }
        try {
            halloweenMobs = Boolean.parseBoolean(properties.getProperty("halloween_mobs", valueOf(halloweenMobs)));
            customMounts = Boolean.parseBoolean(properties.getProperty("custom_mounts", valueOf(customMounts)));
            undead = Boolean.parseBoolean(properties.getProperty("undead", valueOf(undead)));
            monsters = Boolean.parseBoolean(properties.getProperty("monsters", valueOf(monsters)));
            aggressiveNPC = Boolean.parseBoolean(properties.getProperty("aggressive_NPCs", valueOf(aggressiveNPC)));
            animals = Boolean.parseBoolean(properties.getProperty("animals", valueOf(animals)));
            milkWhales = Boolean.parseBoolean(properties.getProperty("milk_whales", valueOf(milkWhales)));
            treasureGoblin = Boolean.parseBoolean(properties.getProperty("treasure_goblin", valueOf(treasureGoblin)));
            sendToTwitter = Boolean.parseBoolean(properties.getProperty("send_to_twitter", valueOf(sendToTwitter)));
            goblinAnnouncement = Boolean.parseBoolean(properties.getProperty("goblin_announcement_message", valueOf(goblinAnnouncement)));
            rarityLimit = Integer.parseInt(properties.getProperty("rarity_limit", valueOf(rarityLimit)));
            creatureLogging = Boolean.parseBoolean(properties.getProperty("creature_logging", valueOf(creatureLogging)));
            logging = Boolean.parseBoolean(properties.getProperty("mod_logging", valueOf(logging)));
            if (logging) {
                if (animals) { CreatureMod.logger.info("Animals: Enabled"); }
                if (!animals) { CreatureMod.logger.info("Animals: Disabled"); }
                if (customMounts) { CreatureMod.logger.info("Mounts: Enabled"); }
                if (!customMounts) { CreatureMod.logger.info("Mounts: Disabled"); }
                if (halloweenMobs) { CreatureMod.logger.info("Halloween Mobs: Enabled"); }
                if (!halloweenMobs) { CreatureMod.logger.info("Halloween Mobs: Disabled"); }
                if (milkWhales) { CreatureMod.logger.info("Domesticate Whales For Milking: Enabled"); }
                if (!milkWhales) { CreatureMod.logger.info("Domesticate Whales For Milking: Disabled"); }
                if (monsters) { CreatureMod.logger.info("Monsters: Enabled"); }
                if (!monsters) { CreatureMod.logger.info("Monsters: Disabled"); }
                if (aggressiveNPC) { CreatureMod.logger.info("NPCs: Enabled"); }
                if (!aggressiveNPC) { CreatureMod.logger.info("NPCs: Disabled"); }
                if (undead) { CreatureMod.logger.info("Undead: Enabled"); }
                if (!undead) { CreatureMod.logger.info("Undead: Disabled"); }
                if (treasureGoblin) { CreatureMod.logger.info("Treasure Goblin: Enabled"); }
                if (!treasureGoblin) { CreatureMod.logger.info("Treasure Goblin: Disabled"); }
                if (goblinAnnouncement) { CreatureMod.logger.info("Treasure Goblin Announcement: Enabled"); }
                if (!goblinAnnouncement) { CreatureMod.logger.info("Treasure Goblin Announcement: Disabled"); }
                CreatureMod.logger.info("Rarity Limit For Bone And Coin = " + String.valueOf(rarityLimit));
                if (creatureLogging) { CreatureMod.logger.info("Creature Logging: Enabled"); }
                if (!creatureLogging) { CreatureMod.logger.info("Creature Logging: Disabled"); }
                if (logging) { CreatureMod.logger.info("Mod Logging: Enabled"); }
                if (!logging) { CreatureMod.logger.info("Mod Logging: Disabled"); }
            }
        } catch (Throwable throwable) {
            if (logging) { CreatureMod.logger.log(Level.SEVERE, "Error in configure", throwable); }
        }
        CreatureMod.logger.log(Level.INFO, "All configure completed");
    }

    public void preInit() {
        logger.info("preInit called.");
        try {
            CreatureChanges.preInit();
        } catch (IllegalArgumentException | ClassCastException e) {
            throw new HookException(e);
        }
        if (logging) { CreatureMod.logger.log(Level.INFO, "All preInit completed"); }
    }

    @Override
    public void init() {
        if (logging) { CreatureMod.logger.log(Level.INFO, "init called"); }
        try {
            ModCreatures.init();
            ModVehicleBehaviours.init();
            CreatureChanges.addNewCreatures();
            CreatureChanges.init();
        } catch (Throwable throwable) {
            if (logging) { CreatureMod.logger.log(Level.SEVERE, "Error In Initializing", throwable); }
        }
        if (logging) { CreatureMod.logger.log(Level.INFO, "All init completed"); }
    }

    @Override
    public void onServerStarted() {
        if (logging) { CreatureMod.logger.info("onServerStarted called"); }
        try {
            CreatureChanges.setTemplateVariables();
            if (animals) { setWorgFields(); }
        } catch (Throwable throwable) {
            if (logging) { CreatureMod.logger.log(Level.SEVERE, "Error In onServerStarted", throwable); }
        }
        if (logging) { CreatureMod.logger.info(" All onServerStarted finished"); }
    }

}