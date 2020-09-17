package com.atherys.proxy.command;

import com.atherys.dto.RedirectPlayersDTO;
import com.atherys.proxy.AtherysProxyPlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;

public class RedirectPlayersCommand implements ProxyCommand {

    private final RedirectPlayersDTO dto;

    protected RedirectPlayersCommand(RedirectPlayersDTO dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        ProxyServer proxyServer = AtherysProxyPlugin.getInstance().getProxy();
        ServerInfo destinationServer = proxyServer.getServerInfo(dto.getDestination());

        dto.getPlayers().forEach(playerUUID -> {
            ProxiedPlayer proxiedPlayer = proxyServer.getPlayer(playerUUID);
            proxiedPlayer.connect(destinationServer, ServerConnectEvent.Reason.PLUGIN);
        });
    }
}
