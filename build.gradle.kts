import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenLocal()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
    maven(url = "https://mvn.lumine.io/repository/maven-public/")
    maven(url = "https://repo.maven.apache.org/maven2/")
}

dependencies {
    compileOnly("io.lumine:Mythic-Dist:5.0.1-SNAPSHOT")
    compileOnly("com.github.iamDvz:DivizionCore:8fb78d5f")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("com.github.TheComputerGeek2.MagicSpells:core:main-SNAPSHOT:dev")
}

group = "ru.iamdvz.mythika"
version = "1.0-SNAPSHOT"
description = "Mythika"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<ShadowJar> {
    archiveFileName.set("Mythika-${version}.jar")
}
tasks.processResources {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}
publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
