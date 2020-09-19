package com.atherys.proxy.command;

import com.atherys.dto.AtherysGsonBuilder;
import com.atherys.dto.RedirectPlayersDTO;
import com.atherys.dto.RegisterServerDTO;
import com.atherys.dto.UnregisterServerDTO;
import com.google.gson.Gson;

import java.util.Arrays;

public class ProxyCommandService {

    private static ProxyCommandService instance;

    private Gson gson;

    private ProxyCommandService() {
        gson = AtherysGsonBuilder.getGson();
    }

    public static ProxyCommandService getInstance() {
        if (instance == null) {
            instance = new ProxyCommandService();
        }

        return instance;
    }

    public ProxyCommand resolve(byte[] data) {
        String commandKey = new String(new byte[]{data[1], data[2]});
        String jsonData = new String(Arrays.copyOfRange(data, 3, data.length));


        switch (commandKey) {
            case RedirectPlayersDTO.KEY:
                return new RedirectPlayersCommand(gson.fromJson(jsonData, RedirectPlayersDTO.class));
            case RegisterServerDTO.KEY:
                return new RegisterServerCommand(gson.fromJson(jsonData, RegisterServerDTO.class));
            case UnregisterServerDTO.KEY:
                return new UnregisterServerCommand(gson.fromJson(jsonData, UnregisterServerDTO.class));
            default:
                return new ErroringProxyCommand("Could not identify command, invalid key: " + new String(data));
        }
    }
}
