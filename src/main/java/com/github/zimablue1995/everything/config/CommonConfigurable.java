package com.github.zimablue1995.everything.config;

import com.github.zimablue1995.everything.config.model.EveryThingConfiguration;
import com.github.zimablue1995.everything.config.model.LinuxScriptConfig;
import com.github.zimablue1995.everything.config.model.ServerConfig;
import com.github.zimablue1995.everything.config.view.sshconfig.SSHConfigView;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class CommonConfigurable implements SearchableConfigurable {
    protected EveryThingConfiguration config;
    private SSHConfigView view;

    public CommonConfigurable() {
        config = EveryThingComponent.getInstance().getState();
        view = new SSHConfigView();
    }

    @Override
    public @NotNull @NonNls String getId() {
        return null;
    }

    // 展示名称
    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Everything";
    }

    // 创建界面
    @Override
    public @Nullable JComponent createComponent() {
        config = EveryThingComponent.getInstance().getState();
        SSHConfigView form = getForm();

        // 服务器
        ServerConfig serverConfig = config.getServerConfig();
        if (serverConfig != null) {
            form.getHost().setText(serverConfig.getHost());
            form.getPort().setText(serverConfig.getPort());
            form.getUsername().setText(serverConfig.getUsername());
            form.getPassword().setText(serverConfig.getPassword());
        }

        // 脚本
        List<LinuxScriptConfig> linuxScriptConfigList = config.getLinuxScriptConfigList();
        form.setLinuxScriptConfigList(linuxScriptConfigList);

        return form;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    // 设置数据
    @Override
    public void apply() {
        SSHConfigView form = getForm();
        // 服务器
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setHost(form.getHost().getText().trim());
        serverConfig.setPort(form.getPort().getText().trim());
        serverConfig.setUsername(form.getUsername().getText().trim());
        serverConfig.setPassword(String.valueOf(form.getPassword().getPassword()));



        config.setServerConfig(serverConfig);
        // 脚本
        List<LinuxScriptConfig> linuxScriptConfigList = form.getLinuxScriptConfigList();
        config.setLinuxScriptConfigList(linuxScriptConfigList);
    }

    // @Override
    // public void apply() throws ConfigurationException {
    //
    // }

    @NotNull
    private SSHConfigView getForm() {
        if (view == null) {
            view = new SSHConfigView();
        }
        return view;
    }
}
