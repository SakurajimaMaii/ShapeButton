// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.0-rc01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://kotlin.bintray.com/kotlinx")
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}