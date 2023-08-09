plugins {
    id("com.android.application") version "8.1.1" apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
