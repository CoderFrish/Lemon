---
title: regionbar
---

# 配置 - regionbar

本配置位于mint_global.toml，来自于上游Mint

```toml
[misc.regionbar]
    # util_color_list
    utilColors = ["GREEN", "YELLOW", "RED", "PURPLE"]
    # format
    regionBarFormat = "<gray>Util<yellow>:</yellow> <util> Chunks<yellow>:</yellow> <green><chunks></green> Players<yellow>:</yellow> <green><players></green> Entities<yellow>:</yellow> <green><entities></green>"
    # update_interval_ticks
    updateInterval = 15
    # enabled
    regionbarEnabled = true
```

该配置用于配置区域状态Boss栏，该配置是从[Luminol](https://github.com/LuminolMC/Luminol/)（同为Folia衍生出来的）搬过来的

## 字段

 - utilColors - Boss栏颜色，到达某个值会显示特定的颜色（具体是哪个值对于哪个颜色就不知道了，毕竟Mint作者都不知道）
 - regionBarFormat - Boss栏上文件格式
 - updateInterval - 该功能的更新间隔时间，其中20为一秒
 - regionbarEnabled - 启用该功能
