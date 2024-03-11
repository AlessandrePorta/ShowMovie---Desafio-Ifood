import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs.kotlin")
}

val keysPropertiesFile: File = rootProject.file("keys.properties")
val keysProperties: Properties = Properties().apply {
    load(keysPropertiesFile.inputStream())
}

android {
    namespace = "com.example.showmovie"
    compileSdk = 34

    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "com.example.showmovie"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    viewBinding {
        enable = true
    }

    defaultConfig {
        buildConfigField("String", "API_KEY", keysProperties["API_KEY"]?.toString().orEmpty())
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.com.squareup.okhttp3.logging)
    implementation(platform(libs.com.squareup.okhttp3))
    implementation(libs.androidx.pag.runt)
    implementation(libs.androidx.nav.test)
    implementation(libs.androidx.lifecycle.commom)
    implementation(libs.org.kotlinx.serialization)
    implementation(libs.com.github.glide)
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.org.coroutines)
    implementation(libs.io.koin)
    implementation(libs.androidx.nav.frag.ktx)
    implementation(libs.androidx.nav.ui.ktx)
    implementation(libs.androidx.nav.df)
    implementation(libs.androidx.nav.comp)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}