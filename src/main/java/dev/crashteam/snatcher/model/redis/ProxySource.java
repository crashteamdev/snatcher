package dev.crashteam.snatcher.model.redis;

import lombok.Data;

@Data
public class ProxySource {

    private String host;
    private Integer port;
    private String login;
    private String password;
    private Integer socksPort;
}
