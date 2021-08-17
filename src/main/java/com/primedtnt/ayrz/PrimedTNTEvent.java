package com.primedtnt.ayrz;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockTNT;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityPrimedTNT;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.level.Sound;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

public class PrimedTNTEvent implements Listener {

    @EventHandler
    public void Event(BlockPlaceEvent ev){
        Player player = ev.getPlayer();
        Block block = ev.getBlock();
        Level level = block.getLevel();
        if (block instanceof BlockTNT) {
            CompoundTag nbt = new CompoundTag()
                    .putList(new ListTag<DoubleTag>("Pos")
                            .add(new DoubleTag("", block.x + 0.5))
                            .add(new DoubleTag("", block.y))
                            .add(new DoubleTag("", block.z + 0.5)))
                    .putList(new ListTag<DoubleTag>("Motion")
                            .add(new DoubleTag("", 0))
                            .add(new DoubleTag("", 0))
                            .add(new DoubleTag("", 0)))
                    .putList(new ListTag<FloatTag>("Rotation")
                            .add(new FloatTag("", 0))
                            .add(new FloatTag("", 0)))
                    .putShort("Fuse", 80);
            Entity tnt = new EntityPrimedTNT(level.getChunk(block.getFloorX() >> 4, block.getFloorZ() >> 4), nbt);
            tnt.spawnToAll();
            block.level.addSound(block, Sound.RANDOM_FUSE);
            ev.setCancelled(true);
            if (!player.isCreative()) player.getInventory().removeItem(Item.get(Item.TNT, 0, 1));
        }
    }
}
