package com.github.zimablue1995.everything.killport.action;

import com.github.zimablue1995.everything.killport.dialog.KillPortDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/8 14:43
 * @description:
 */
public class KillPortAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        KillPortDialog searchPortDialog = new KillPortDialog(e.getProject());
        searchPortDialog.showAndGet();
    }
}
