package com.atherys.proxy.command;

import com.atherys.dto.RegisterServerDTO;
import com.atherys.proxy.AtherysProxyPlugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

public class RegisterServerCommand implements ProxyCommand {

    private final RegisterServerDTO dto;

    protected RegisterServerCommand(RegisterServerDTO dto) {
        this.dto = dto;
    }

    @Override
    public void run() {
        ProxyServer proxyServer = AtherysProxyPlugin.getInstance().getProxy();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(
                dto.getIpAddress(),
                dto.getPort()
        );

        ServerInfo serverInfo = proxyServer.constructServerInfo(
                dto.getName(),
                inetSocketAddress,
                dto.getMotd(),
                true
        );

        proxyServer.getServers().put(dto.getKey(), serverInfo);
    }
}
