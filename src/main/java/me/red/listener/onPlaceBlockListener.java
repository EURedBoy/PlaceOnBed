package me.red.listener;

import me.red.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.List;

public class onPlaceBlockListener implements Listener {
    private Main plugin;

    public onPlaceBlockListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent e){
        if (!(plugin.getConfig().getBoolean("enable"))) return;

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.BED_BLOCK)){
            if (e.getItem().getType().isBlock() ){

                Location Bloc = e.getClickedBlock().getLocation().add(0,1,0);

                switch (e.getBlockFace()){
                    case UP:
                        Bloc = e.getClickedBlock().getLocation().add(0,1,0);
                        break;
                    case NORTH:
                        Bloc = e.getClickedBlock().getLocation().add(0,0,-1);
                        break;
                    case SOUTH:
                        Bloc = e.getClickedBlock().getLocation().add(0,0,1);
                        break;
                    case WEST:
                        Bloc = e.getClickedBlock().getLocation().add(-1,0,0);
                        break;
                    case EAST:
                        Bloc = e.getClickedBlock().getLocation().add(1,0,0);
                        break;
                }
                ItemStack item = e.getItem();

                if (Bloc.getBlock().isEmpty() && !(checkPos(Bloc))) {

                    if (item.getType().equals(Material.WOOL)){
                        Bloc.getBlock().setType(Material.WOOL);
                        Bloc.getBlock().setData((byte) item.getDurability());
                    }else{
                        Bloc.getBlock().setType(item.getType());
                    }

                    ItemStack itemHand = e.getPlayer().getItemInHand();

                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                        e.getPlayer().getItemInHand().setAmount(itemHand.getAmount() - 1);
                    }
                }
            }
        }
    }

    private boolean checkPos(Location Bloc){
        for (Player online : Bukkit.getOnlinePlayers()){
            if (Bloc.distance(online.getLocation()) < plugin.getConfig().getDouble("distance-check")){
                return true;
            }
        }
        return false;
    }
}
