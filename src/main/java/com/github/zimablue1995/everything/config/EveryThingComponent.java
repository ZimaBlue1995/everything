package com.github.zimablue1995.everything.config;

import com.github.zimablue1995.everything.config.model.EveryThingConfiguration;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/12 1:38
 * @description:
 */
@State(name = "EveryThingComponent", storages = {@Storage(value = "EveryThingComponent.xml")})
public class EveryThingComponent implements PersistentStateComponent<EveryThingConfiguration> {

    private EveryThingConfiguration config;

    @Override
    public @Nullable EveryThingConfiguration getState() {
        if (config == null) {
            config = new EveryThingConfiguration();
        }
        return config;
    }

    @Override
    public void loadState(@NotNull EveryThingConfiguration state) {
        XmlSerializerUtil.copyBean(state, Objects.requireNonNull(getState()));
    }

    public static EveryThingComponent getInstance() {
        return ServiceManager.getService(EveryThingComponent.class);
    }
}
