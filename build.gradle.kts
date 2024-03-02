plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "com.akkih"
version = "0.5.1-beta"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

publishing {
    repositories {
        maven {
            name = "lyax-repo"
            url = uri("https://repo.ly.ax/releases")

            credentials {
                username = System.getenv("MAVEN_NAME")
                password = System.getenv("MAVEN_SECRET")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "dusk"
            version = version.toString()

            from(components["java"])
        }
    }
}