# Everything

![Build](https://github.com/ZimaBlue1995/Everything/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Verify the [pluginGroup](/gradle.properties), [plugin ID](/src/main/resources/META-INF/plugin.xml) and [sources package](/src/main/kotlin).
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
## English version
### Introduction
Just like the name of Everything, this plugin is an all-in-one plugin with many different kinds of functions. The functions of the plugin are very rich, not only a single function;

The vision is to integrate all the functions commonly used in development into this plugin to achieve a true one-stop service.

If the function you want is temporarily missing, please contact me to make it up.

### Function
* Get git commit message
* Kill processes by port
* One-click execution of linux scripts

## Chinese version
### 简介
正如其Everything的名字，本插件是一个拥有很多不同种类功能的多合一插件，插件的功能十分丰富，而不是只有单一的功能；

愿景是把所有开发中常用的功能都集成进本插件中，以做到真正的一站式服务。

如果你想要的功能暂时缺少，请联系我补上。

### 功能
* 获取git提交记录
* 根据端口杀死进程
* 一键执行Linux脚本

<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Everything"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/ZimaBlue1995/Everything/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
