
package org.requiem.mods.morecreatures;

import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.ServerStartedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviours;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Boolean.parseBoolean;
import static java.lang.String.valueOf;
import static java.lang.System.getProperty;
import static org.requiem.mods.morecreatures.CreatureChanges.setWorgFields;

public class CreatureMod implements WurmServerMod, Initable, Configurable, ServerStartedListener {
    static Logger logger = Logger.getLogger(CreatureMod.class.getName() + " v1.0");

    public static boolean halloweenMobs = false;
    public static boolean customMounts = false;
    public static boolean undead = false;
    public static boolean farmAnimals = false;
    public static boolean monsters = false;
    public static boolean npc = false;
    public static boolean animals = false;
    public static boolean milkWhales = false;
    public static boolean treasureGoblin = false;
    public static boolean wyverns = false;
    private static boolean sendToTwitter;
    static boolean bDebug = false;

    private void Debug(String x) {
        if (this.bDebug) {
            System.out.println(String.valueOf(this.getClass().getSimpleName()) + ": " + x);
            System.out.flush();
            CreatureMod.logger.log(Level.INFO, x);
        }
    }

    @Override
    public void configure(Properties properties) {
        CreatureMod.logger.log(Level.INFO, "configure called");
        this.bDebug = Boolean.parseBoolean(properties.getProperty("debug", valueOf(this.bDebug)));
        try {
            String logsPath = Paths.get("mods", new String[0]) + "/logs/";
            File newDirectory = new File(logsPath);
            if (!newDirectory.exists()) {
                newDirectory.mkdirs();
            }
            FileHandler fh = new FileHandler(String.valueOf(String.valueOf(logsPath)) + this.getClass().getSimpleName() + ".log", 10240000, 200, true);
            if (this.bDebug) {
                fh.setLevel(Level.INFO);
            } else {
                fh.setLevel(Level.WARNING);
            }
            fh.setFormatter(new SimpleFormatter());
            CreatureMod.logger.addHandler(fh);
        }
        catch (IOException ie) {
            System.err.println(String.valueOf(this.getClass().getName()) + ": Unable to add file handler to logger");
        }
        this.Debug("Debugging messages are enabled.");
        try {
            halloweenMobs = Boolean.parseBoolean(properties.getProperty("halloween_mobs", valueOf(halloweenMobs)));
            customMounts = Boolean.parseBoolean(properties.getProperty("custom_mounts", valueOf(customMounts)));
            undead = Boolean.parseBoolean(properties.getProperty("undead", valueOf(undead)));
            farmAnimals = Boolean.parseBoolean(properties.getProperty("farm_animals", valueOf(farmAnimals)));
            monsters = Boolean.parseBoolean(properties.getProperty("monsters", valueOf(monsters)));
            npc = Boolean.parseBoolean(properties.getProperty("npc", valueOf(npc)));
            animals = Boolean.parseBoolean(properties.getProperty("animals", valueOf(animals)));
            milkWhales = Boolean.parseBoolean(properties.getProperty("milk_whales", valueOf(milkWhales)));
            treasureGoblin = parseBoolean(getProperty("treasure_goblin", valueOf(treasureGoblin)));
            wyverns = parseBoolean(getProperty("wyverns", valueOf(wyverns)));
            sendToTwitter = Boolean.parseBoolean(properties.getProperty("sendToTwitter", Boolean.toString(sendToTwitter)));
            if (animals) { CreatureMod.logger.info("Animals: Enabled"); }
            if (!animals) { CreatureMod.logger.info("Animals: Disabled"); }
            if (customMounts) { CreatureMod.logger.info("Mounts: Enabled"); }
            if (!customMounts) { CreatureMod.logger.info("Mounts: Disabled"); }
            if (farmAnimals) { CreatureMod.logger.info("Farm Animals: Enabled"); }
            if (!farmAnimals) { CreatureMod.logger.info("Farm Animals: Disabled"); }
            if (halloweenMobs) { CreatureMod.logger.info("Halloween Mobs: Enabled"); }
            if (!halloweenMobs) { CreatureMod.logger.info("Halloween Mobs: Disabled"); }
            if (milkWhales) { CreatureMod.logger.info("Domesticate Whales For Milking: Enabled"); }
            if (!milkWhales) { CreatureMod.logger.info("Domesticate Whales For Milking: Disabled"); }
            if (monsters) { CreatureMod.logger.info("Monsters: Enabled"); }
            if (!monsters) { CreatureMod.logger.info("Monsters: Disabled"); }
            if (npc) { CreatureMod.logger.info("NPCs: Enabled"); }
            if (!npc) { CreatureMod.logger.info("NPCs: Disabled"); }
            if (undead) { CreatureMod.logger.info("Undead: Enabled"); }
            if (!undead) { CreatureMod.logger.info("Undead: Disabled"); }
            if (treasureGoblin) { CreatureMod.logger.info("Treasure Goblin: Enabled"); }
            if (!treasureGoblin) { CreatureMod.logger.info("Treasure Goblin: Disabled"); }
            if (wyverns) { CreatureMod.logger.info("Wyverns: Enabled"); }
            if (!wyverns) { CreatureMod.logger.info("Wyverns: Disabled"); }
            CreatureMod.logger.log(Level.INFO, "all configure completed");
        } catch (Throwable throwable) {
            CreatureMod.logger.log(Level.SEVERE, "Error in configure setting()", throwable);
        }
    }

    @Override
    public void init() {
        try {
            CreatureMod.logger.log(Level.INFO, "init called");
            ModCreatures.init();
            ModVehicleBehaviours.init();
            CreatureChanges.addNewCreatures();
            // TODO Fix CreatureChanges.init() source error
            //CreatureChanges.init();
            CreatureMod.logger.log(Level.INFO, "all init completed");
        } catch (Throwable throwable) {
            CreatureMod.logger.log(Level.SEVERE, "Error In Initializing", throwable);
        }
    }

    @Override
    public void onServerStarted() {
        try {
            CreatureMod.logger.info("Setting custom creature corpse models, types, etc...");
            CreatureChanges.setTemplateVariables();
            if (animals) { setWorgFields(); }
        } catch (Throwable throwable) {
            CreatureMod.logger.log(Level.SEVERE, "Error In onServerStarted", throwable);
        }
    }

}