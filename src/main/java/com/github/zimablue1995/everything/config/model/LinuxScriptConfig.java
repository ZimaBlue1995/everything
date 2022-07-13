package com.github.zimablue1995.everything.config.model;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/13 22:42
 * @description:
 */
public class LinuxScriptConfig {
    private String scriptKey;
    private String scriptValue;
    private Boolean enabled = true;

    public LinuxScriptConfig() {
    }

    public LinuxScriptConfig(String scriptKey, String scriptValue) {
        this.scriptKey = scriptKey;
        this.scriptValue = scriptValue;
    }

    public String getScriptKey() {
        return scriptKey;
    }

    public void setScriptKey(String scriptKey) {
        this.scriptKey = scriptKey;
    }

    public String getScriptValue() {
        return scriptValue;
    }

    public void setScriptValue(String scriptValue) {
        this.scriptValue = scriptValue;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
