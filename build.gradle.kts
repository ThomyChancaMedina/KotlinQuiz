// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.1.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath ("com.google.gms:google-services:4.3.4")
        classpath( "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete (rootProject.buildDir)
}