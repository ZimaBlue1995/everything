package com.github.zimablue1995.everything.config.dialog;

import com.github.zimablue1995.everything.config.model.DataMapping;
import com.github.zimablue1995.everything.config.model.LinuxScriptConfig;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.util.ui.UI;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LinuxScriptAddView extends DialogWrapper {
    private JTextField keyTextField;
    private JTextField valueTextField;

    public LinuxScriptAddView() {
        super(false);
        init();
        setSize(500, 100);
        setTitle("新增linux脚本");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        keyTextField = new JTextField();
        valueTextField = new JTextField();
        return UI.PanelFactory.grid().splitColumns()
                .add(UI.PanelFactory.panel(keyTextField).withLabel("名称"))
                .add(UI.PanelFactory.panel(valueTextField).withLabel("脚本"))
                .createPanel();
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(keyTextField.getText())) {
            return new ValidationInfo("请填入名称");
        }
        if (StringUtils.isEmpty(valueTextField.getText())) {
            return new ValidationInfo("请填入脚本");
        }
        return super.doValidate();
    }

    public LinuxScriptConfig getValue() {
        return new LinuxScriptConfig(keyTextField.getText(), valueTextField.getText());
    }
}