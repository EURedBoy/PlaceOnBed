package me.red.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onBlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Block block = e.getBlock();

        if (block.getType().equals(Material.BED_BLOCK)) return;

        if (block.getLocation().add(0,-1,0).getBlock().getType().equals(Material.BED_BLOCK)
                || block.getLocation().add(1,0,0).getBlock().getType().equals(Material.BED_BLOCK)
                || block.getLocation().add(-1,0,0).getBlock().getType().equals(Material.BED_BLOCK)
                || block.getLocation().add(0,0,1).getBlock().getType().equals(Material.BED_BLOCK)
                || block.getLocation().add(0,0,-1).getBlock().getType().equals(Material.BED_BLOCK))
            e.setCancelled(false);
    }
}
