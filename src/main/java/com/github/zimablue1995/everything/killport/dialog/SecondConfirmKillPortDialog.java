//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.killport.dialog;

import com.github.zimablue1995.everything.killport.entity.DataCenter;
import com.github.zimablue1995.everything.killport.noitf.NotifyEntity;
import com.github.zimablue1995.everything.killport.noitf.PluginNotify;
import com.github.zimablue1995.everything.util.CmdUtil;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jetbrains.annotations.Nullable;

public class SecondConfirmKillPortDialog extends DialogWrapper {
    EditorTextField tfPID;
    private JButton btnSearch;

    public SecondConfirmKillPortDialog(JButton btnSearch) {
        super(true);
        this.setTitle("核对端口");
        this.init();
        this.btnSearch = btnSearch;
    }

    @Nullable
    protected JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel(DataCenter.KILL_IMAGE);
        jPanel.add(imageLabel, "North");
        this.tfPID = new EditorTextField(DataCenter.KILL_PID);
        JLabel pidLabel = new JLabel("PID:");
        jPanel.add(pidLabel, "West");
        jPanel.add(this.tfPID, "Center");
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel jPanel = new JPanel();
        JButton jButton = new JButton("确认杀死");
        jButton.addActionListener((e) -> {
            String pidText = this.tfPID.getText();
            System.out.println("pidText = " + pidText);
            String killMessage = CmdUtil.killPid(pidText);
            PluginNotify.notification(NotifyEntity.success("success_killport_id", "kill " + pidText + "====>" + killMessage));
            this.dispose();
            btnSearch.doClick();
        });
        jPanel.add(jButton);
        return jPanel;
    }
}
