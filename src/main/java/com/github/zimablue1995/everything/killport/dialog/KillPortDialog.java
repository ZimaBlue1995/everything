package com.github.zimablue1995.everything.killport.dialog;

import com.github.zimablue1995.everything.killport.entity.DataCenter;
import com.github.zimablue1995.everything.killport.entity.PortEntity;
import com.github.zimablue1995.everything.util.CmdUtil;
import com.github.zimablue1995.everything.util.PortUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/8 15:24
 * @description:
 */
public class KillPortDialog extends DialogWrapper {

    private Project project;
    private JPanel panel, panelTop;
    private JTextField inputTextField;
    private JButton btnSearch;
    private JTable table;

    public KillPortDialog(@Nullable Project project) {
        super(project);
        init();
        this.project = project;
        setTitle("输入端口搜索");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        panel = new JPanel();
        panelTop = new JPanel();

        panel.setLayout(new BorderLayout());

        panelTop.setLayout(new BorderLayout());
        JLabel label = new JLabel("端口");
        label.setPreferredSize(new Dimension(35, 20));
        panelTop.add(label, BorderLayout.WEST);
        inputTextField = new JTextField();
        panelTop.add(inputTextField, BorderLayout.CENTER);
        btnSearch = new JButton("搜索");
        panelTop.add(btnSearch, BorderLayout.EAST);
        panel.add(panelTop, BorderLayout.NORTH);

        table = new JTable();
        table.setModel(DataCenter.TABLE_MODEL);
        table.setEnabled(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 300);
        table.setFillsViewportHeight(true);
        panel.add(scrollPane, BorderLayout.CENTER);

        inputTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    btnSearch.doClick();
                }
            }
        });

        btnSearch.addActionListener((e) -> {
            // TODO: 2022/7/9 进度条跟弹窗不允许同时存在?
            // Task.Backgroundable task = new Task.Backgroundable(project, "根据端口号查询中...") {
            //     @Override
            //     public void run(@NotNull ProgressIndicator indicator) {
            //         indicator.setIndeterminate(false);

                    DataCenter.reset();
                    String portText = inputTextField.getText().trim();
                    if (!PortUtil.verifyPort(portText)) {
                        portText = portText.trim();
                        List<PortEntity> portEntity = CmdUtil.getPortEntity(portText);
                        if (portEntity.isEmpty()) {
                            MessageDialogBuilder.yesNo("操作结果", portText + "端口无进程占用").show();
                        }

                        DataCenter.NOTE_LIST.addAll(portEntity);
                        portEntity.forEach((it) -> DataCenter.TABLE_MODEL.addRow(it.getRow()));
                    }

                // }
            // };

            // ProgressManager.getInstance().runProcessWithProgressAsynchronously(task, new BackgroundableProcessIndicator(task));
        });

        return panel;
    }

    /**
     * 覆盖默认的ok/cancel按钮
     */
    @Override
    protected Action @NotNull [] createActions() {
        DialogWrapperExitAction exitAction = new DialogWrapperExitAction("关闭", CANCEL_EXIT_CODE);
        CustomOKAction okAction = new KillPortDialog.CustomOKAction();
        // 设置默认的焦点按钮
        okAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{okAction, exitAction};
    }

    protected class CustomOKAction extends DialogWrapperAction {

        protected CustomOKAction() {
            super("杀死端口");
        }

        @Override
        protected void doAction(ActionEvent e) {
            int selectedRow = KillPortDialog.this.table.getSelectedRow();
            if (selectedRow >= 0) {
                DataCenter.KILL_PID = KillPortDialog.this.table.getValueAt(selectedRow, 0).toString();
                DataCenter.KILL_IMAGE = KillPortDialog.this.table.getValueAt(selectedRow, 1).toString();
                SecondConfirmKillPortDialog addNoteDialog = new SecondConfirmKillPortDialog(btnSearch);
                addNoteDialog.show();
            }
        }
    }

}
