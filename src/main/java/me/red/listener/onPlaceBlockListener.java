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
    protected List<Location> locations = new ArrayList<>();

    public onPlaceBlockListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent e){
        if (!(plugin.getConfig().getBoolean("enable"))) return;

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.BED_BLOCK)){
            if (e.getItem().getType().isBlock() && e.getBlockFace().equals(BlockFace.UP)){

                Location Bloc = e.getClickedBlock().getLocation().add(0,1,0);
                Material material = e.getItem().getType();

                if (Bloc.getBlock().isEmpty() && !(checkPos(Bloc))) {

                    if (material.equals(Material.WOOL)){
                        DyeColor color = DyeColor.getByWoolData(material.);
                        Wool wool = new Wool(color);
                        Bloc.getBlock().setType(wool.getItemType());
                    }else{
                        Bloc.getBlock().setType(block.getType());
                    }

                    ItemStack item = e.getPlayer().getItemInHand();
                    locations.add(Bloc);

                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                        e.getPlayer().getItemInHand().setAmount(item.getAmount() - 1);
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
