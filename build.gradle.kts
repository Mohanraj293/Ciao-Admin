// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.2.2" apply false
  id("org.jetbrains.kotlin.android") version "1.9.22" apply false
  id("com.google.gms.google-services") version "4.3.15" apply false
  id("com.google.dagger.hilt.android") version "2.44" apply false
}

allprojects {
  repositories {
    maven(url = "https://maven.pkg.github.com/tarkalabs/tarka-ui-kit-android") {
      credentials {
        username = System.getenv("GITHUB_USER")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
    mavenCentral()
    google()
  }
}

repositories {
  maven(url = "https://maven.pkg.github.com/tarkalabs/tarka-ui-kit-android") {
    credentials {
      username = System.getenv("GITHUB_USER")
      password = System.getenv("GITHUB_TOKEN")
    }
  }
  mavenCentral()
  google()
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}

repositories {
  google()
}
