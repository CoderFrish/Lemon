<img src="./public/Mint.png" alt="Logo" align="right" width="220">

# LemonMint

> [!IMPORTANT]\
> 该服务端虽然努力让更多的Bukkit插件能够运行，但是并不稳定，不推荐在实际生产环境中应用。如果没打算要Bukkit插件正常运行，要更稳定和更好性能推荐使用[Mint](https://github.com/MenthaMC/Mint)

<h4>LemonMint（原Mint-radical）是在Mint的基础上努力让更多的Bukkit插件能够运行，并且在原基础上修复被破坏的特性和改进性能、并添加独特的特性。</h4>

![GitHub Repo stars](https://img.shields.io/github/stars/MenthaMC/LemonMint?style=for-the-badge&logo=github&label=Stars&logoColor=white&color=ffda65)
![GitHub Release Date](https://img.shields.io/github/release-date/MenthaMC/LemonMint?style=for-the-badge&logo=github&label=Release&logoColor=white&color=06d094)
![GitHub Downloads](https://img.shields.io/github/downloads/MenthaMC/LemonMint/total?style=for-the-badge&logo=github&label=Downloads&logoColor=white&color=c4a400)

## 1 | 📦下载与构建
任何版本都可以在 [Release](https://github.com/MenthaMC/LemonMint/releases) 中找到，也可以通过以下步骤构建
```shell
./gradlew applyAllPatches && ./gradlew createMojmapPaperclipJar
```

## 2 | 🧪API
```xml
<repositories>
    <repository>
        <id>menthamc</id>
        <url>https://repo.menthamc.com/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.coderfrish.lemonmint</groupId>
        <artifactId>lemonmint-api</artifactId>
        <version>${LATEST_VERSION}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## 3 | 📫联系
QQ群: [1020403749](https://qm.qq.com/q/RKzZJH4JKW) | Discord群组: [点击加入](https://discord.com/invite/39K7Jz4F) | 邮箱: [3167717663@qq.com](mailto:3167717663@qq.com)

## 4 | ✨贡献代码
LemonMint欢迎大家来贡献代码，如果贡献的代码和优化有关并且你觉得激进的话，那么请务必添加可开关的选项。
