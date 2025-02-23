buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    alias(libs.plugins.androidAppliation) apply false
    alias(libs.plugins.jetbrainsKotlin)  apply false
    alias(libs.plugins.compose.compiler) apply false

    id("com.google.dagger.hilt.android") version "2.51.1" apply false

}
