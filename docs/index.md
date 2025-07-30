---
title: LemonMint概述以及文件结构
---

# LemonMint概述以及文件结构

> 该文档尚未完成，只完成了LemonMint大部分配置解释以及Mint小部分配置解释

## 概述

LemonMint 是Mint服务端衍生出来的一个服务端，其目的是为了保证有着Mint一致的性能优化和特性，并且努力让Bukkit API正常调用。

### 目前相对于Mint，LemonMint独有的特性

 - Bukkit Teleport的调用正常化（不完全）
 - Bukkit Scoreboard API和原本Scoreboard命令的正常化（但是Bukkit API创建的Scoreboard并不受原版的Scoreboard指令控制）
 - 从Purpur搬过来的6格末影箱（某位用户提起的，其实在1.21.1还是1.21.5就开始提的具体忘了，1.21.8才正式搬过来）
 - 对于Bukkit Scheduler API的简单恢复（并不代表全部恢复）

### 作者正在考虑的
 - 给LemonMint添加新的插件系统（用Lua开发插件的那种）

### 更多信息
 - 关于配置的更多信息可以看本网站，其他的可以去仓库自行了解：[GitHub](https://github.com/ManthaMC/Lemint)

## 文件结构

LemonMint 的文件结构与Bukkit、Folia、Paper等服务端相似，相比他们，我们多了一个`mint`文件夹，专门存放面对服务端的配置（Mint服务端一样，如果熟悉Mint服务端，那么你一定可以了解LemonMint大部分操作以及配置）。

Mint 文件夹文件结构
```
mint/
└── mint_global.toml
```

其中的mint_global.toml 是存放绝大多数配置的文件，其格式是toml。
