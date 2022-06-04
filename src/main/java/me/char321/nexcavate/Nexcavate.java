package me.char321.nexcavate;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class Nexcavate extends JavaPlugin implements SlimefunAddon {
    private static Nexcavate instance;

    public Nexcavate() {
        instance = this;
    }

    public static Nexcavate instance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/qwertyuioplkjhgfd/Nexcavate/issues";
    }
}
