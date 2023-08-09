plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.kursx.chart"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles += listOf(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro"),
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":analytics-api"))
    implementation(project(":analytics-appsflyer"))
    implementation(project(":analytics-mixpanel"))
    implementation(project(":analytics-adjust"))
    implementation(project(":analytics-logcat"))

    implementation(project(":chart-datasource-api"))
    implementation(project(":chart-datasource-impl"))

    implementation(project(":chart-ui-compose"))
    implementation(project(":chart-presentation"))
    implementation(project(":chart-data"))
    implementation(project(":chart-domain"))

    implementation(project(":common-presentation"))

    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.core.splashscreen)
}
