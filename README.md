<img src="public/logo.png" width="200" align="left"/>

# Traium

*基于Folia的服务端，在修复Bukkit被破坏的API同时，也为轻生电修复被破坏的原版特性。*

![GitHub Repo stars](https://img.shields.io/github/stars/TraiumMC/Traium?style=for-the-badge&logo=github&label=Stars&logoColor=white&color=ffda65)
![GitHub Release Date](https://img.shields.io/github/release-date/TraiumMC/Traium?style=for-the-badge&logo=github&label=Release&logoColor=white&color=06d094)
![GitHub Downloads](https://img.shields.io/github/downloads/TraiumMC/Traium/total?style=for-the-badge&logo=github&label=Downloads&logoColor=white&color=c4a400)
![Created At](https://img.shields.io/github/created-at/TraiumMC/Traium?style=for-the-badge&color=blue)

## 1 | 下载与构建
任何版本都可以在 [Release](https://github.com/TraiumMC/Traium/releases) 中找到，也可以通过以下步骤构建
```shell
# 克隆Traium的代码仓库
git clone https://github.com/TraiumMC/Traium.git
cd Traium

# 应用Traium的补丁并创建Paperclip Jar文件
./gradlew applyAllPatches && ./gradlew createMojmapPaperclipJar
```

## 2 | Development API

Maven

```xml
<dependencies>
    <dependency>
        <groupId>me.coderfrish.traium</groupId>
        <artifactId>traium-api</artifactId>
        <version>1.21.8-R0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## 3 | 最后
> [!TIP]
> 你的每一个免费的 ⭐Star 就是我们每一个前进的动力！

[![Star](https://api.star-history.com/svg?repos=TraiumMC/Traium&type=Date)](https://www.star-history.com/#TraiumMC/Traium&Date) \
如果你想为Traium服务端贡献代码，可以通过Fork此项目并发布Pull Request，如果贡献的代码你觉得激进的话，那么请务必添加可开关的选项。
