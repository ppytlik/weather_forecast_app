plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.interview.weatherforecast.feature.forecast"
    compileSdk = 34

    defaultConfig {
        minSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    implementation(project(":core_forecast"))
    implementation(project(":core_navigation"))

    implementation(project(":lib_domain"))
    implementation(project(":lib_compose"))

    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material)

    implementation(libs.androidx.compose.navigation)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.viewmodel)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
}