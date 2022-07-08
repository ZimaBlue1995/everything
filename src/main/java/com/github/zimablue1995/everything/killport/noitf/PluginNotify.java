//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.killport.noitf;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.Notifications.Bus;

public class PluginNotify {
    public PluginNotify() {
    }

    public static void notification(NotifyEntity notify) {
        NotificationGroup notificationGroup = new NotificationGroup(notify.getPluginId(), notify.getNotificationDisplayType(), notify.isLogBuDefault());
        Notification notification = notificationGroup.createNotification(notify.getMessages(), notify.getMessageType());
        Bus.notify(notification);
    }
}
