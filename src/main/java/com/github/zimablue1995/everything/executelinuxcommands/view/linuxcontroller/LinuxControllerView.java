/*
 * Created by JFormDesigner on Wed Jul 13 01:32:27 CST 2022
 */

package com.github.zimablue1995.everything.executelinuxcommands.view.linuxcontroller;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.ssh.JschUtil;
import com.github.zimablue1995.everything.config.EveryThingComponent;
import com.github.zimablue1995.everything.config.model.EveryThingConfiguration;
import com.github.zimablue1995.everything.config.model.LinuxScriptConfig;
import com.github.zimablue1995.everything.config.model.ServerConfig;
import com.github.zimablue1995.everything.util.BasicUtil;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.impl.BackgroundableProcessIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.jcraft.jsch.Session;
import net.miginfocom.swing.*;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author unknown
 */
public class LinuxControllerView extends JFrame {
    Vector<String> columnNames;
    Vector<Vector<String>> rowData;
    private Project project;

    EveryThingConfiguration config;

    public LinuxControllerView(Project project) {
        this.project = project;
        config = EveryThingComponent.getInstance().getState();
        initComponents();

        initTable();

        // 不允许编辑和选中
        // table.setEnabled(false);

        // TABLE_MODEL.setDataVector(rowData, columnNames);
    }

    private void initTable() {
        // 设置列名
        columnNames = new Vector<>();
        columnNames.add("名称");
        columnNames.add("脚本");

        // 设置行信息
        rowData = new Vector<>();
        List<LinuxScriptConfig> linuxScriptConfigList = config.getLinuxScriptConfigList();

        Vector<Vector<String>> collect = linuxScriptConfigList.stream()
                .map(a -> {
                    Vector<String> row = new Vector<>();
                    row.add(a.getScriptKey());
                    row.add(a.getScriptValue());
                    return row;
                }).collect(Collectors.toCollection(Vector::new));

        // 加入到rowData
        rowData.addAll(collect);

        // 初始化Jtable
        DefaultTableModel TABLE_MODEL = new DefaultTableModel(rowData, columnNames);

        table.setModel(TABLE_MODEL);

        // 一次只能选择一行
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void execLinux(ActionEvent e) {
        // TODO add your code here
        int rowCount = table.getRowCount();
        if (rowCount == 0) {
            MessageDialogBuilder.yesNo("缺失脚本配置", "请先点击【服务器及脚本配置】按钮配置好脚本").show();
            return;
        }

        int row = table.getSelectedRow();//获取选中行
        if (row < 0) {
            MessageDialogBuilder.yesNo("提示", "请先选中脚本").show();
            return;
        }

        ServerConfig serverConfig = config.getServerConfig();


        String host = serverConfig.getHost();
        String port = serverConfig.getPort();
        String username = serverConfig.getUsername();
        String password = serverConfig.getPassword();

        if (BasicUtil.isEmpyt(host) || BasicUtil.isEmpyt(port) || BasicUtil.isEmpyt(username) || BasicUtil.isEmpyt(password)) {
            MessageDialogBuilder.yesNo("缺失服务器配置", "请先点击【服务器及脚本配置】按钮配置好服务器").show();
            return;
        }

        Session session = JschUtil.getSession(host, Integer.parseInt(port), username, password);

        Object scriptName = table.getValueAt(row, 0);
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "执行脚本[" + scriptName + "]中...") {
            String exec;

            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                exec = JschUtil.exec(session, (String) table.getValueAt(row, 1), CharsetUtil.CHARSET_UTF_8);
            }

            @Override
            public void onFinished() {
                MessageDialogBuilder.yesNo("脚本[" + scriptName + "]执行完成", exec).show();
            }
        });

        // 关闭窗口
        this.dispose();
    }

    private void configMouseClicked(MouseEvent e) {
        // TODO add your code here
        ShowSettingsUtil.getInstance().showSettingsDialog(project, "Everything");
    }

    private void refreshButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        initTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("properties.LinuxControllerView");
        execConfig = new JButton();
        refreshButton = new JButton();
        scrollPane = new JScrollPane();
        table = new JTable() {
            // 表格不允许被编辑. 每次修改ui时都会覆盖这边代码, 故每次修改后都得加上这边代码
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        execButton = new JButton();

        //======== this ========
        setTitle("\u4e00\u952e\u6267\u884cLinux\u811a\u672c");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[599,fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[30]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //---- execConfig ----
        execConfig.setText(bundle.getString("LinuxControllerView.execConfig.text"));
        execConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                configMouseClicked(e);
            }
        });
        contentPane.add(execConfig, "cell 3 0,alignx left,growx 0");

        //---- refreshButton ----
        refreshButton.setText(bundle.getString("LinuxControllerView.refreshButton.text"));
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshButtonMouseClicked(e);
            }
        });
        contentPane.add(refreshButton, "cell 4 0");

        //======== scrollPane ========
        {
            scrollPane.setViewportView(table);
        }
        contentPane.add(scrollPane, "cell 3 1 3 1");

        //---- execButton ----
        execButton.setText(bundle.getString("LinuxControllerView.execButton.text"));
        execButton.setPreferredSize(new Dimension(120, 30));
        execButton.setMinimumSize(new Dimension(82, 30));
        execButton.setRolloverEnabled(false);
        execButton.addActionListener(e -> execLinux(e));
        contentPane.add(execButton, "cell 5 3,alignx trailing,growx 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton execConfig;
    private JButton refreshButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton execButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
