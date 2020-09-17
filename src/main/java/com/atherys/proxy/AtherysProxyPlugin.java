package com.atherys.proxy;

import com.atherys.proxy.command.ProxyCommand;
import com.atherys.proxy.command.ProxyCommandService;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class AtherysProxyPlugin extends Plugin implements Listener {

    private static AtherysProxyPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getProxy().registerChannel("atherysproxyplugin:main");
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent pluginMessageEvent) {
        if ("atherysproxyplugin:main".equals(pluginMessageEvent.getTag())) {
            ProxyCommand command = ProxyCommandService.getInstance().resolve(pluginMessageEvent.getData());
            command.run();
        }
    }

    public static AtherysProxyPlugin getInstance() {
        return instance;
    }
}
