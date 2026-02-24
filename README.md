<img src="public/logo.png" width="200" align="left"/>

# Lirael Project

~~*基于Folia的服务端，在修复Bukkit被破坏的API同时，也为轻生电修复被破坏的原版特性。*~~

*暂时未找到自己的方向，现在只剩下空桥，至于后面干什么看作者 :)*

![GitHub Repo stars](https://img.shields.io/github/stars/TraiumMC/Traium?style=for-the-badge&logo=github&label=Stars&logoColor=white&color=ffda65)
![GitHub Release Date](https://img.shields.io/github/release-date/TraiumMC/Traium?style=for-the-badge&logo=github&label=Release&logoColor=white&color=06d094)
![GitHub Downloads](https://img.shields.io/github/downloads/TraiumMC/Traium/total?style=for-the-badge&logo=github&label=Downloads&logoColor=white&color=c4a400)
![Created At](https://img.shields.io/github/created-at/TraiumMC/Traium?style=for-the-badge&color=blue)

## 1 | 下载与构建
任何版本都可以在 [Release](https://github.com/TraiumMC/Traium/releases) 中找到，也可以通过以下步骤构建
```shell
# 克隆Traium的代码仓库
git clone https://github.com/TraiumMC/Lirael.git
cd Lirael

# 应用Lirael的补丁并创建Paperclip Jar文件
./gradlew applyAllPatches && ./gradlew createMojmapPaperclipJar
```

## 2 | Plugin API

Maven

```xml
<dependencies>
    <dependency>
        <groupId>me.coderfrish.lirael</groupId>
        <artifactId>traium-api</artifactId>
        <version>1.21.11-R0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## 3 | 配置文件

(该端正在大量重构中...)

~~服务端的配置文件位于根目录的traium_config里面~~
```
traium_config
├─ traium_global.toml
└─ traium_world.toml
```

## 4 | Bstats

[![bStats](https://bstats.org/signatures/server-implementation/Traium.svg)](https://bstats.org/plugin/server-implementation/Traium/)

## 5 | 最后
> [!TIP]
> 你的每一个免费的 ⭐Star 就是我们每一个前进的动力！

[![Star](https://api.star-history.com/svg?repos=TraiumMC/Traium&type=Date)](https://www.star-history.com/#TraiumMC/Traium&Date) \
如果你想为Traium服务端贡献代码，可以通过Fork此项目并发布Pull Request，如果贡献的代码你觉得激进的话，那么请务必添加可开关的选项。
