buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidAppliation) apply false
    alias(libs.plugins.jetbrainsKotlin)  apply false
    alias(libs.plugins.compose.compiler) apply false
}