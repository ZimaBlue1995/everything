package com.github.zimablue1995.everything.config.model;

import java.io.Serializable;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/13 22:39
 * @description:
 */
public class ServerConfig implements Serializable {
    private String host;
    private String port;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
