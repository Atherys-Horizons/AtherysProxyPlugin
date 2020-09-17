package com.atherys.proxy.command;

import com.atherys.dto.UnregisterServerDTO;
import com.atherys.proxy.AtherysProxyPlugin;
import net.md_5.bungee.api.ProxyServer;

public class UnregisterServerCommand implements ProxyCommand {

    private final UnregisterServerDTO dto;

    protected UnregisterServerCommand(UnregisterServerDTO dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        ProxyServer proxyServer = AtherysProxyPlugin.getInstance().getProxy();
        proxyServer.getServers().remove(dto.getKey());
    }
}
