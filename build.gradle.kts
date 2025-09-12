import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java // TODO java launcher tasks
    id("dev.menthamc.lightweight.patcher") version "2.0.4-SNAPSHOT"
}

paperweight {
    upstreams.register("mint") {
        repo = github("MenthaMC", "Mint")
        ref = providers.gradleProperty("mintRef")

        patchFile {
            path = "mint-server/build.gradle.kts"
            outputFile = file("lemonmint-server/build.gradle.kts")
            patchFile = file("lemonmint-server/build.gradle.kts.patch")
        }

        patchFile {
            path = "mint-api/build.gradle.kts"
            outputFile = file("lemonmint-api/build.gradle.kts")
            patchFile = file("lemonmint-api/build.gradle.kts.patch")
        }

        patchRepo("paperApi") {
            upstreamPath = "paper-api"
            patchesDir = file("lemonmint-api/paper-patches")
            outputDir = file("paper-api")
        }

        patchRepo("foliaApi") {
            upstreamPath = "folia-api"
            patchesDir = file("lemonmint-api/folia-patches")
            outputDir = file("folia-api")
        }

        patchDir("mintApi") {
            upstreamPath = "mint-api"
            excludes = listOf("build.gradle.kts", "build.gradle.kts.patch", "paper-patches", "folia-patches")
            patchesDir = file("lemonmint-api/mint-patches")
            outputDir = file("mint-api")
        }
    }
}

val paperMavenPublicUrl = "https://repo.papermc.io/repository/maven-public/"
val menthaMavenPublicUrl = "https://repo.menthamc.org/repository/maven-public/"
val menthaMavenSnapshotsUrl = "https://repo.menthamc.org/repository/maven-snapshots/"

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    extensions.configure<PublishingExtension> {
        repositories {
            maven(menthaMavenSnapshotsUrl) {
                name = "MenthaMC_Snapshots"
                credentials(PasswordCredentials::class) {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }

    repositories {
        mavenCentral()
        maven(paperMavenPublicUrl)
        maven(menthaMavenPublicUrl)
    }

    tasks.withType<AbstractArchiveTask>().configureEach {
        isPreserveFileTimestamps = false
        isReproducibleFileOrder = true
    }
    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release = 21
        options.isFork = true
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }
    tasks.withType<Test> {
        testLogging {
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
            events(TestLogEvent.STANDARD_OUT)
        }
    }

    tasks.withType<Javadoc> {
        options {
            (this as StandardJavadocDocletOptions).apply {
                addStringOption("-add-modules", "jdk.incubator.vector")
                addStringOption("Xdoclint:none", "-quiet")
            }
        }
    }
}
