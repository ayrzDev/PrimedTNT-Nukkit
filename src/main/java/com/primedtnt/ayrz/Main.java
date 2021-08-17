package com.primedtnt.ayrz;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener {

    public void onEnable(){
        this.getLogger().info("Plugin Enable");
        this.getServer().getPluginManager().registerEvents(new PrimedTNTEvent(), this);
    }
}
