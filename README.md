<img src="./public/LemonMint.png" alt="Logo" align="left" width="220">

# LemonMint

*LemonMint是在Mint的基础上努力让更多的Bukkit插件能够运行，并且在原基础上修复被破坏的特性和改进性能、并添加独特的特性。*

![GitHub Repo stars](https://img.shields.io/github/stars/MenthaMC/LemonMint?style=for-the-badge&logo=github&label=Stars&logoColor=white&color=ffda65)
![GitHub Release Date](https://img.shields.io/github/release-date/MenthaMC/LemonMint?style=for-the-badge&logo=github&label=Release&logoColor=white&color=06d094)
![GitHub Downloads](https://img.shields.io/github/downloads/MenthaMC/LemonMint/total?style=for-the-badge&logo=github&label=Downloads&logoColor=white&color=c4a400)
![Created At](https://img.shields.io/github/created-at/MenthaMC/LemonMint?style=for-the-badge&color=blue)
[![License](https://img.shields.io/github/license/MenthaMC/LemonMint?style=for-the-badge&color=green)](README.md)

## 1 | 下载与构建
任何版本都可以在 [Release](https://github.com/MenthaMC/LemonMint/releases) 中找到，也可以通过以下步骤构建
```shell
# 克隆LemonMint的代码仓库
git clone https://github.com/MenthaMC/LemonMint.git
cd LemonMint

# 应用LemonMint的补丁并创建Paperclip Jar文件
./gradlew applyAllPatches && ./gradlew createMojmapPaperclipJar
```

## 2 | 相关特性

 - 重构中，以前的特性全部废弃...

## 3 | API
```xml
<repositories>
    <repository>
        <id>menthamc</id>
        <url>https://repo.menthamc.org/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.coderfrish.lemonmint</groupId>
        <artifactId>lemonmint-api</artifactId>
        <version>1.21.8-R0.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## 4 | 联系

 - QQ群: [1020403749](https://qm.qq.com/q/RKzZJH4JKW) 
 - Discord群组: [点击加入](https://discord.com/invite/39K7Jz4F) 
 - 邮箱: [3167717663@qq.com](mailto:3167717663@qq.com)

## 5 | BStats
[![bStats Graph Data](https://bstats.org/signatures/server-implementation/LemonMint.svg)](https://bstats.org/plugin/server-implementation/LemonMint)

## 6 | 最后
> [!TIP]
> 你的每一个免费的 ⭐Star 就是我们每一个前进的动力！

[![Star](https://api.star-history.com/svg?repos=MenthaMC/LemonMint&type=Date)](https://star-history.com/#MenthaMC/LemonMint&Date) \
如果你想为LemonMint贡献代码，可以通过Fork此项目并发布Pull Request，如果贡献的代码你觉得激进的话，那么请务必添加可开关的选项。
