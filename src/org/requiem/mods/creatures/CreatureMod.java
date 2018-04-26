
package org.requiem.mods.creatures;

import org.gotti.wurmunlimited.modloader.interfaces.*;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.vehicles.ModVehicleBehaviours;
import org.requiem.mods.creatures.animals.*;
import org.requiem.mods.creatures.custommounts.Charger;
import org.requiem.mods.creatures.custommounts.HornedPony;
import org.requiem.mods.creatures.custommounts.RainbowUnicorn;
import org.requiem.mods.creatures.custommounts.RainbowZebra;
import org.requiem.mods.creatures.farmanimals.*;
import org.requiem.mods.creatures.halloween.OminousTree;
import org.requiem.mods.creatures.halloween.ScaryPumpkin;
import org.requiem.mods.creatures.monsters.*;
import org.requiem.mods.creatures.npc.Avenger;
import org.requiem.mods.creatures.npc.DarkInnkeeper;
import org.requiem.mods.creatures.npc.Innkeeper;
import org.requiem.mods.creatures.undead.Reaper;
import org.requiem.mods.creatures.undead.Skeleton;
import org.requiem.mods.creatures.undead.Zombie;
import org.requiem.mods.creatures.uniques.KingKong;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreatureMod implements WurmServerMod, PreInitable, Initable, Configurable, ServerStartedListener {
    private static Logger logger = Logger.getLogger(CreatureMod.class.getName() + "v1.0");
    public static boolean halloweenMobs;
    public static boolean uniques;
    public static boolean customMounts;
    public static boolean undead;
    public static boolean farmAnimals;
    public static boolean monsters;
    public static boolean npc;
    public static boolean animals;
    public static boolean milkWhales;

    private static void logInfo(String msg) {
        if (logger != null) {
            logger.log(Level.INFO, msg);
        }
    }

    @Override
    public void preInit() {
        if (animals) {
            logInfo("Animals: Enabled");
        }
        if (!animals) {
            logInfo("Animals: Disabled");
        }
        if (customMounts) {
            logInfo("Custom Mounts: Enabled");
        }
        if (!customMounts) {
            logInfo("Custom Mounts: Disabled");
        }
        if (farmAnimals) {
            logInfo("Farm Animals: Enabled");
        }
        if (!farmAnimals) {
            logInfo("Farm Animals: Disabled");
        }
        if (halloweenMobs) {
            logInfo("Halloween Mobs: Enabled");
        }
        if (!halloweenMobs) {
            logInfo("Halloween Mobs: Disabled");
        }
        if (milkWhales) {
            logInfo("Domesticate Whales For Milking: Enabled");
        }
        if (!milkWhales) {
            logInfo("Domesticate Whales For Milking: Disabled");
        }
        if (monsters) {
            logInfo("Monsters: Enabled");
        }
        if (!monsters) {
            logInfo("Monsters: Disabled");
        }
        if (npc) {
            logInfo("NPCs: Enabled");
        }
        if (!npc) {
            logInfo("NPCs: Disabled");
        }
        if (uniques) {
            logInfo("Uniques: Enabled");
        }
        if (!uniques) {
            logInfo("Uniques: Disabled");
        }
        if (undead) {
            logInfo("Undead: Enabled");
        }
        if (!undead) {
            logInfo("Undead: Disabled");
        }
    }

    @Override
    public void init() {
        try {
            logger.log(Level.INFO, "Initializing...()");
            ModCreatures.init();
            ModVehicleBehaviours.init();
            logger.info("Registering Animals.");
            ModCreatures.addCreature(new Anaconda());
            ModCreatures.addCreature(new Gorilla());
            ModCreatures.addCreature(new LargeBoar());
            ModCreatures.addCreature(new LargeRat());
            ModCreatures.addCreature(new Ocelot());
            ModCreatures.addCreature(new PandaBear());
            ModCreatures.addCreature(new SiberianHusky());
            ModCreatures.addCreature(new Unicorn());
            ModCreatures.addCreature(new WarHound());
            ModCreatures.addCreature(new WolfPackmaster());
            ModCreatures.addCreature(new Zebra());
            logger.info("Registering Custom Mounts.");
            ModCreatures.addCreature(new Charger());
            ModCreatures.addCreature(new HornedPony());
            ModCreatures.addCreature(new RainbowUnicorn());
            ModCreatures.addCreature(new RainbowZebra());
            logger.info("Registering Farm Animals.");
            ModCreatures.addCreature(new Bull());
            ModCreatures.addCreature(new Calf());
            ModCreatures.addCreature(new Cat());
            ModCreatures.addCreature(new Chicken());
            ModCreatures.addCreature(new Cow());
            ModCreatures.addCreature(new Dog());
            ModCreatures.addCreature(new Foal());
            ModCreatures.addCreature(new Horse());
            ModCreatures.addCreature(new Pig());
            ModCreatures.addCreature(new Rooster());
            logger.info("Registering Halloween Creatures.");
            ModCreatures.addCreature(new OminousTree());
            ModCreatures.addCreature(new ScaryPumpkin());
            logger.info("Registering Monsters.");
            ModCreatures.addCreature(new BlackWidow());
            ModCreatures.addCreature(new Blob());
            ModCreatures.addCreature(new ForestSpider());
            ModCreatures.addCreature(new Giant());
            ModCreatures.addCreature(new Goblin());
            ModCreatures.addCreature(new LavaFiend());
            ModCreatures.addCreature(new SolDemon());
            ModCreatures.addCreature(new SpiritTroll());
            ModCreatures.addCreature(new Worg());
            logger.info("Registering NPCs.");
            ModCreatures.addCreature(new Avenger());
            ModCreatures.addCreature(new DarkInnkeeper());
            ModCreatures.addCreature(new Innkeeper());
            logger.info("Registering Undead Creatures.");
            ModCreatures.addCreature(new Reaper());
            ModCreatures.addCreature(new Skeleton());
            ModCreatures.addCreature(new Zombie());
            logger.info("Registering Uniques Creatures.");
            ModCreatures.addCreature(new KingKong());
            logger.info("Registering Creatures Changes.");
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Error In Initializing", throwable);
        }
    }

    @Override
    public void configure(Properties properties) {
        try {
            uniques = Boolean.parseBoolean(properties.getProperty("uniques", Boolean.toString(uniques)));
            halloweenMobs = Boolean.parseBoolean(properties.getProperty("halloween_mobs", Boolean.toString(halloweenMobs)));
            customMounts = Boolean.parseBoolean(properties.getProperty("custom_mounts", Boolean.toString(customMounts)));
            undead = Boolean.parseBoolean(properties.getProperty("undead", Boolean.toString(undead)));
            farmAnimals = Boolean.parseBoolean(properties.getProperty("farm_animals", Boolean.toString(farmAnimals)));
            monsters = Boolean.parseBoolean(properties.getProperty("monsters", Boolean.toString(monsters)));
            npc = Boolean.parseBoolean(properties.getProperty("npc", Boolean.toString(npc)));
            animals = Boolean.parseBoolean(properties.getProperty("animals", Boolean.toString(animals)));
            milkWhales = Boolean.parseBoolean(properties.getProperty("milk_whales", Boolean.toString(milkWhales)));
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Error in configure setting()", throwable);
        }
    }

    @Override
    public void onServerStarted() {
        try {
            logger.info("Setting custom creature corpse models, types, etc...");
            CreatureChanges.setTemplateVariables();
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Error In onServerStarted", throwable);
        }
    }

}