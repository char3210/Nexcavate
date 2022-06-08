package me.char321.nexcavate.gui.structurescreen;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.char321.nexcavate.gui.NEGUI;
import me.char321.nexcavate.gui.NEGUIInventoryHolder;
import me.char321.nexcavate.structure.Structure;
import me.char321.nexcavate.structure.piece.StructurePiece;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class StructureScreenHandler implements NEGUIInventoryHolder {
    private static final ItemStack BACKGROUND = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");

    private final Inventory inventory;
    private final int size;
    private Structure structure;
    private int layer;

    public StructureScreenHandler(int size) {
        this.size = size;
        this.inventory = Bukkit.createInventory(this, size * 9, "Structure Viewer");
    }

    @Override
    public void click(InventoryClickEvent e) {
        if (e.getRawSlot() == 0) {
            NEGUI.openResearchScreen((Player) e.getWhoClicked());
        }
        if (e.getRawSlot() % 9 == 8) {
            layer = size - (e.getRawSlot() / 9) - 1;
            refresh();
        }
    }

    @Override
    @Nonnull
    public Inventory getInventory() {
        return inventory;
    }

    public void setStructure(Structure structure) {
        layer = 0;
        this.structure = structure;
        refresh();
    }

    public void refresh() {
        inventory.setItem(0, new CustomItemStack(Material.ARROW, "&fBack"));
        inventory.setItem(9, new CustomItemStack(Material.STRUCTURE_BLOCK, "&5Structure", "&7Place all the layers of the structure", "&7in the world as shown."));
        for (int i = 18; i < size * 9; i += 9) {
            inventory.setItem(i, BACKGROUND);
        }
        for (int i = 7; i < size * 9; i += 9) {
            inventory.setItem(i, BACKGROUND);
        }
        int layernum = 1;
        for (int i = size * 9 - 1; i > 0; i -= 9) {
            ItemStack layerItem = new CustomItemStack(Material.SNOW, "&eLayer " + layernum);
            if (layernum - 1 == layer) {
                ItemMeta im = layerItem.getItemMeta();
                im.addEnchant(Enchantment.DURABILITY, 1, true);
                im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                im.setDisplayName(im.getDisplayName() + ChatColor.GRAY + " (selected)");
                layerItem.setItemMeta(im);
            }
            inventory.setItem(i, layerItem);
            layernum++;
        }

        StructurePiece[][] pieceLayer = structure.getPieces()[layer];

        for (int z = 0; z < size; z++) {
            for (int x = 0; x < size; x++) {
                inventory.setItem((z * 9) + (x + 7 - size), pieceLayer[z][x].getDisplay());
            }
        }

        for (int x = 7 - size - 1; x >= 1; x--) {
            for (int y = 0; y < size; y++) {
                inventory.setItem(y*9 + x, BACKGROUND);
            }
        }
    }
}
