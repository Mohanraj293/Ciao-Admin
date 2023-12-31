plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.gms.google-services")
}

android {
  namespace = "com.lazymohan.ciaoadmin"
  compileSdk = 33

  defaultConfig {
    applicationId = "com.lazymohan.ciaoadmin"
    minSdk = 28
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  buildFeatures {
    viewBinding = true
  }

  buildTypes {
    release {
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  val lifecycleVersion = "2.6.1"
  implementation("androidx.core:core-ktx:1.10.1")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  implementation("com.google.firebase:firebase-database-ktx")
  implementation("androidx.activity:activity-ktx:1.7.2")
  implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
  // LiveData
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
  // Lifecycles only (without ViewModel or LiveData)
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
  // Saved state module for ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
}