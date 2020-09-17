package com.atherys.proxy.command;

import com.atherys.proxy.command.exception.ProxyCommandException;

public class ErroringProxyCommand implements ProxyCommand {

    private final String error;

    protected ErroringProxyCommand(String error) {
        this.error = error;
    }

    @Override
    public void run() {
        throw new ProxyCommandException("Proxy Command Error: " + error);
    }
}
