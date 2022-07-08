package com.github.zimablue1995.everything.action;

import com.github.zimablue1995.everything.view.DatePickerDialogWrapper;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/7 1:17
 * @description:
 */
public class GitCommitMsgAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
//        String gitCommitMsg = getGitCommitMsgByDate(e);
//        Messages.showMessageDialog(e.getProject(), gitCommitMsg, "git提交记录", null);
        DatePickerDialogWrapper datePickerDialogWrapper = new DatePickerDialogWrapper(e.getProject());
        datePickerDialogWrapper.showAndGet();
    }


}
