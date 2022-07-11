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
### Introduction
Every day after work to fill the workload will think about a question: **what I did today**. The answer is all in git's commit log, which keeps a detailed record of what we did during the day. 
All we needed was a tool that could pull git records and format them for us with one click, and there we had it; 

Development often need to use some system commands, but often can not remember always go to the document or google, so this kind of function is also integrated into the plug-in; 

As its grocery store name suggests, _more tools will follow_, as long as they are useful.

### Function
* Get git commit message
* Kill processes by port

### 简介
每天下班填工作量的时候都会想一个问题：**我今天到底干了什么**。答案都在git的commit记录里，git详细记录了我们这一天干了哪些事。那只要有个能一键拉取git记录并帮我们格式化好的工具就行了，于是就有了这个插件;

开发时经常要用到一些系统的命令，但是经常记不住总得去翻文档/百度，于是这类功能也被集成进插件中；

正如其【杂货铺】的名字，_后续会推出更多工具_，只要是实用的都会集成进来。

### 功能
* 获取git提交记录
* 根据端口杀死进程

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
