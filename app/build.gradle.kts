plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.com.google.devtools.ksp)
}


val releaseStorePassword: String = project.extra["RELEASE_KEY_STORE_PASSWORD"] as String
val releaseKeyAlias: String = project.extra["RELEASE_KEY_ALIAS"] as String
val releaseKeyPassword: String = project.extra["RELEASE_KEY_PASSWORD"] as String

val debugStorePassword: String = project.extra["DEBUG_KEY_STORE_PASSWORD"] as String
val debugKeyAlias: String = project.extra["DEBUG_KEY_ALIAS"] as String
val debugKeyPassword: String = project.extra["DEBUG_KEY_PASSWORD"] as String


android {
    namespace = "com.ui.movie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ui.movie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore/movie_release.keystore")
            storePassword = releaseStorePassword
            keyAlias = releaseKeyAlias
            keyPassword = releaseKeyPassword
        }
        getByName("debug") {
            storeFile = file("../keystore/movie_debug.keystore")
            storePassword = debugStorePassword
            keyAlias = debugKeyAlias
            keyPassword = debugKeyPassword
        }
    }




    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    sourceSets.configureEach {
        kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin/")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":core:design"))
    implementation(project(":core:common"))
    implementation(project(":core:design"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))
    implementation(project(":api:movie"))
    implementation(project(":feature:home"))
    implementation(project(":feature:upcoming"))
    implementation(libs.androidx.profileinstaller)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    "baselineProfile"(project(":baselineprofile"))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.compose.material:material:1.6.5")

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.bundles.bundleComposeNavigation)
}