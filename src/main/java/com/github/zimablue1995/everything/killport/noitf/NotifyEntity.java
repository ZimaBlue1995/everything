//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.killport.noitf;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.openapi.ui.MessageType;

public class NotifyEntity {
    private String pluginId;
    private String messages;
    private boolean logBuDefault;
    private NotificationDisplayType notificationDisplayType;
    private MessageType messageType;

    public NotifyEntity() {
    }

    public static NotifyEntity success(String pluginId, String messages) {
        NotifyEntity notif = new NotifyEntity();
        notif.setPluginId(pluginId);
        notif.setMessages(messages);
        notif.setLogBuDefault(true);
        notif.setNotificationDisplayType(NotificationDisplayType.BALLOON);
        notif.setMessageType(MessageType.INFO);
        return notif;
    }

    public String getPluginId() {
        return this.pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public String getMessages() {
        return this.messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public void setLogBuDefault(boolean logBuDefault) {
        this.logBuDefault = logBuDefault;
    }

    public boolean isLogBuDefault() {
        return this.logBuDefault;
    }

    public NotificationDisplayType getNotificationDisplayType() {
        return this.notificationDisplayType;
    }

    public void setNotificationDisplayType(NotificationDisplayType notificationDisplayType) {
        this.notificationDisplayType = notificationDisplayType;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
