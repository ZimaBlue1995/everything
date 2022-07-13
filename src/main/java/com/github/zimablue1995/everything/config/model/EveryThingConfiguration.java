package com.github.zimablue1995.everything.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/12 1:39
 * @description:
 */
public class EveryThingConfiguration implements Serializable {
    private ServerConfig serverConfig;
    private List<LinuxScriptConfig> linuxScriptConfigList = new ArrayList<>();

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public List<LinuxScriptConfig> getLinuxScriptConfigList() {
        return linuxScriptConfigList;
    }

    public void setLinuxScriptConfigList(List<LinuxScriptConfig> linuxScriptConfigList) {
        this.linuxScriptConfigList = linuxScriptConfigList;
    }
}
