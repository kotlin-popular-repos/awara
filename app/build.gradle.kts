plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    namespace = "me.rerere.awara"
    compileSdk = 33

    defaultConfig {
        applicationId = "me.rerere.awara"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Android KTX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")

    // Compose
    // implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.ui:ui:1.4.0-rc01")
    implementation("androidx.compose.ui:ui-graphics:1.4.0-rc01")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0-rc01")
    implementation("androidx.compose.material3:material3:1.1.0-alpha08")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.0-alpha08")
    implementation("androidx.compose.material:material-icons-extended:1.4.0-rc01")

    // Media
    implementation("androidx.media3:media3-exoplayer:1.0.0")
    implementation("androidx.media3:media3-cast:1.0.0")
    implementation("androidx.media3:media3-session:1.0.0")
    implementation("androidx.media3:media3-ui:1.0.0")

    // Accompanist
    implementation("com.google.accompanist:accompanist-navigation-animation:0.29.2-rc")

    // Splash
    implementation("androidx.core:core-splashscreen:1.0.0")

    // Setting
    implementation("com.github.re-ovo:compose-setting:1.1")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.4.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Lottie
    implementation("com.airbnb.android:lottie-compose:6.0.0")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Markdown
    implementation("org.jetbrains:markdown:0.4.1")

    // Coil
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-svg:2.3.0")

    // Profile Installer
    implementation("androidx.profileinstaller:profileinstaller:1.3.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
            freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
            freeCompilerArgs += "-opt-in=androidx.compose.animation.ExperimentalAnimationApi"
            freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
            freeCompilerArgs += "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi"
            freeCompilerArgs += "-opt-in=coil.annotation.ExperimentalCoilApi"
            freeCompilerArgs += "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi"
        }
    }
}