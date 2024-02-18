import java.io.FileInputStream
import java.util.Properties

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.gms.google-services")
  id("com.google.dagger.hilt.android")
  kotlin("kapt")
}

android {
  val signFile = rootProject.file("keystore.properties")
  if (signFile.exists()) {
    val properties = Properties()
    properties.load(FileInputStream(signFile))
    signingConfigs {
      create("release") {
        storeFile = file(properties.getProperty("debugKeystorePath"))
        storePassword = properties.getProperty("debugStorePassword")
        keyAlias = properties.getProperty("debugKeyAlias")
        keyPassword = properties.getProperty("debugKeyPassword")
      }
    }
  }

  namespace = "com.lazymohan.ciaoadmin"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.lazymohan.ciaoadmin"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("debug")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    viewBinding = true
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.9"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.11.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.activity:activity-ktx:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2023.08.00"))
  implementation("androidx.compose.material:material:1.6.1")
  implementation("androidx.compose.material3:material3:1.2.0")


  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

  //firebase
  implementation("com.google.firebase:firebase-database-ktx")
  implementation(platform("com.google.firebase:firebase-bom:32.2.2"))


  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

  //Tarka UI Kit
  implementation("com.tarkalabs:tarkaui:1.0.8")
  implementation("com.tarkalabs:tarkaui-icons:1.0.0")

  //jetpack compose
  implementation("androidx.compose.material3:material3")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.activity:activity-compose:1.8.2")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
  debugImplementation("androidx.compose.ui:ui-tooling")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-test-manifest")

  implementation("com.microsoft.design:fluent-system-icons:1.1.201@aar")

  //hilt dagger
  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")
}

kapt {
  correctErrorTypes = true
}