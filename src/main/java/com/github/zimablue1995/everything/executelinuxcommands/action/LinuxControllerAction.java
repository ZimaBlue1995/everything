package com.github.zimablue1995.everything.executelinuxcommands.action;

import com.github.zimablue1995.everything.executelinuxcommands.view.linuxcontroller.LinuxControllerView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/13 1:26
 * @description:
 */
public class LinuxControllerAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        LinuxControllerView linuxController = new LinuxControllerView(e.getProject());
        linuxController.show();
    }
}
