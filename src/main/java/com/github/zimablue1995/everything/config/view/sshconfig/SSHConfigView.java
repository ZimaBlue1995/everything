/*
 * Created by JFormDesigner on Tue Jul 12 21:25:26 CST 2022
 */

package com.github.zimablue1995.everything.config.view.sshconfig;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.github.zimablue1995.everything.config.EveryThingComponent;
import com.github.zimablue1995.everything.config.dialog.LinuxScriptAddView;
import com.github.zimablue1995.everything.config.model.DataMapping;
import com.github.zimablue1995.everything.config.model.EveryThingConfiguration;
import com.github.zimablue1995.everything.config.model.LinuxScriptConfig;
import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

/**
 * @author unknown
 */
public class SSHConfigView extends JPanel {
    private JPanel linuxPanel;
    private JBTable linuxTable;
    private List<LinuxScriptConfig> linuxScriptConfigList;

    public SSHConfigView() {
        initComponents();

        // 初始化table
        EveryThingConfiguration configuration = EveryThingComponent.getInstance().getState();
        linuxScriptConfigList = configuration.getLinuxScriptConfigList();
        linuxTable = createLinuxTable();
        linuxTable.getEmptyText().setText("脚本可以写成[sh /home/xxx/xx.sh], 多个linux命令可以用&&连接");
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(linuxTable);
        toolbarDecorator.setMoveDownAction(null);
        toolbarDecorator.setMoveUpAction(null);

        toolbarDecorator
                .setAddAction(event -> {
                    LinuxScriptAddView linuxScriptAddView = new LinuxScriptAddView();
                    if (linuxScriptAddView.showAndGet()) {
                        LinuxScriptConfig linuxScriptConfig = linuxScriptAddView.getValue();
                        if (linuxScriptConfigList.stream().anyMatch(q -> linuxScriptConfig.getScriptKey().equals(q.getScriptKey()))) {
                            Messages.showMessageDialog("名称已存在", "重复", Messages.getInformationIcon());
                            return;
                        }
                        linuxScriptConfigList.add(linuxScriptConfig);
                        linuxTable.setModel(new ListTableModel<>(getGlobalColumnInfo(), linuxScriptConfigList));
                    }
                })
                .setRemoveAction(event -> {
                    int i = Messages.showOkCancelDialog("确认删除该脚本?", "删除", "删除", "取消", Messages.getInformationIcon());
                    if (i == 0) {
                        int selectedRow = linuxTable.getSelectedRow();
                        linuxScriptConfigList.remove(selectedRow);
                        linuxTable.setModel(new ListTableModel<>(getGlobalColumnInfo(), linuxScriptConfigList));
                    }
                })
                .setToolbarPosition(ActionToolbarPosition.TOP);
        linuxPanel = toolbarDecorator.createPanel();
        add(linuxPanel, "cell 1 7 12 2");
    }

    public JPanel getComponent() {
        return linuxPanel;
    }

    public ColumnInfo<Object, Object>[] getGlobalColumnInfo() {
        ArrayList<String> columnListName = Lists.newArrayList("名称", "脚本");
        ColumnInfo<Object, Object>[] columnArray = new ColumnInfo[columnListName.size()];
        for (int i = 0; i < columnListName.size(); i++) {
            ColumnInfo<Object, Object> envColumn = new ColumnInfo<>(columnListName.get(i)) {
                @Override
                public @Nullable Object valueOf(Object o) {
                    return o;
                }
            };
            columnArray[i] = envColumn;
        }
        return columnArray;
    }

    public JBTable createLinuxTable() {
        ColumnInfo<Object, Object>[] columns = getGlobalColumnInfo();
        ListTableModel<LinuxScriptConfig> model = new ListTableModel<>(columns, linuxScriptConfigList);
        JBTable table = new JBTable(model) {
            @Override
            public Object getValueAt(int row, int column) {
                if (linuxScriptConfigList.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                LinuxScriptConfig linuxScriptConfig = linuxScriptConfigList.get(row);
                if (linuxScriptConfig == null) {
                    return StringUtils.EMPTY;
                }
                if (column == 0) {
                    return linuxScriptConfig.getScriptKey();
                } else {
                    return linuxScriptConfig.getScriptValue();
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                LinuxScriptConfig linuxScriptConfig = linuxScriptConfigList.get(row);
                if (column == 0) {
                    linuxScriptConfig.setScriptKey(aValue.toString());
                } else {
                    linuxScriptConfig.setScriptValue(aValue.toString());
                }
            }

        };
        table.setVisible(true);
        return table;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("properties.SSHConfigView");
        serverLabel = new JLabel();
        separator1 = new JSeparator();
        serverPanel = new JPanel();
        hostLabel = new JLabel();
        host = new JTextField();
        portLabel = new JLabel();
        port = new JTextField();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordLabel = new JLabel();
        password = new JPasswordField();
        linuxLabel = new JLabel();
        separator2 = new JSeparator();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[27,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[144]" +
            "[34]"));

        //---- serverLabel ----
        serverLabel.setText(bundle.getString("SSHConfigView.serverLabel.text"));
        add(serverLabel, "cell 1 1,alignx center,growx 0");
        add(separator1, "cell 1 2 12 1");

        //======== serverPanel ========
        {
            serverPanel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                "[fill]" +
                "[383,fill]",
                // rows
                "[]" +
                "[]" +
                "[]" +
                "[]" +
                "[]"));

            //---- hostLabel ----
            hostLabel.setText(bundle.getString("SSHConfigView.hostLabel.text"));
            serverPanel.add(hostLabel, "cell 0 0,alignx right,growx 0");
            serverPanel.add(host, "cell 2 0");

            //---- portLabel ----
            portLabel.setText(bundle.getString("SSHConfigView.portLabel.text"));
            serverPanel.add(portLabel, "cell 0 1,alignx right,growx 0");
            serverPanel.add(port, "cell 2 1");

            //---- usernameLabel ----
            usernameLabel.setText(bundle.getString("SSHConfigView.usernameLabel.text"));
            serverPanel.add(usernameLabel, "cell 0 2,alignx right,growx 0");
            serverPanel.add(username, "cell 2 2");

            //---- passwordLabel ----
            passwordLabel.setText(bundle.getString("SSHConfigView.passwordLabel.text"));
            serverPanel.add(passwordLabel, "cell 0 3,alignx right,growx 0");
            serverPanel.add(password, "cell 2 3");
        }
        add(serverPanel, "cell 1 3 12 1");

        //---- linuxLabel ----
        linuxLabel.setText(bundle.getString("SSHConfigView.linuxLabel.text"));
        add(linuxLabel, "cell 1 5,alignx center,growx 0");
        add(separator2, "cell 1 6 12 1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel serverLabel;
    private JSeparator separator1;
    private JPanel serverPanel;
    private JLabel hostLabel;
    private JTextField host;
    private JLabel portLabel;
    private JTextField port;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel linuxLabel;
    private JSeparator separator2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JLabel getServerLabel() {
        return serverLabel;
    }

    public void setServerLabel(JLabel serverLabel) {
        this.serverLabel = serverLabel;
    }

    public JSeparator getSeparator1() {
        return separator1;
    }

    public void setSeparator1(JSeparator separator1) {
        this.separator1 = separator1;
    }

    public JPanel getServerPanel() {
        return serverPanel;
    }

    public void setServerPanel(JPanel serverPanel) {
        this.serverPanel = serverPanel;
    }

    public JLabel getHostLabel() {
        return hostLabel;
    }

    public void setHostLabel(JLabel hostLabel) {
        this.hostLabel = hostLabel;
    }

    public JTextField getHost() {
        return host;
    }

    public void setHost(JTextField host) {
        this.host = host;
    }

    public JLabel getPortLabel() {
        return portLabel;
    }

    public void setPortLabel(JLabel portLabel) {
        this.portLabel = portLabel;
    }

    public JTextField getPort() {
        return port;
    }

    public void setPort(JTextField port) {
        this.port = port;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JLabel getLinuxLabel() {
        return linuxLabel;
    }

    public void setLinuxLabel(JLabel linuxLabel) {
        this.linuxLabel = linuxLabel;
    }

    public JSeparator getSeparator2() {
        return separator2;
    }

    public void setSeparator2(JSeparator separator2) {
        this.separator2 = separator2;
    }

    public JPanel getLinuxPanel() {
        return linuxPanel;
    }

    public void setLinuxPanel(JPanel linuxPanel) {
        this.linuxPanel = linuxPanel;
    }

    public JBTable getLinuxTable() {
        return linuxTable;
    }

    public void setLinuxTable(JBTable linuxTable) {
        this.linuxTable = linuxTable;
    }

    public List<LinuxScriptConfig> getLinuxScriptConfigList() {
        return linuxScriptConfigList;
    }

    public void setLinuxScriptConfigList(List<LinuxScriptConfig> linuxScriptConfigList) {
        this.linuxScriptConfigList = linuxScriptConfigList;
    }
}
