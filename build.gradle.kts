// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  id("com.android.application") version "8.1.0" apply false
  id("org.jetbrains.kotlin.android") version "1.8.0" apply false
  id("com.google.gms.google-services") version "4.3.15" apply false
}

buildscript {
  dependencies {
    val kotlinVersion = "1.7.20"
    classpath("com.android.tools.build:gradle:8.1.0")
    classpath("com.google.gms:google-services:4.3.10")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
    classpath("com.google.firebase:perf-plugin:1.4.1")
    classpath("org.jacoco:org.jacoco.core:0.8.8")
  }
}

allprojects {
  repositories {
    mavenCentral()
    google()
  }
}

repositories {
  mavenCentral()
  google()
}