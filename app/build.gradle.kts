import com.gcode.plugin.version.AndroidX
import com.gcode.plugin.version.Libraries
import com.gcode.plugin.version.Version

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.gcode.plugin.version")
}

android {
    compileSdk = Version.compile_sdk_version
    buildToolsVersion = Version.build_tools_version

    defaultConfig {
        applicationId = "com.gcode.shapebuttonsampledemo"
        minSdk = Version.min_sdk_version
        targetSdk = Version.target_sdk_version
        versionCode = Version.version_code
        versionName = Version.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    namespace = "com.gcode.gstylebuttonsampledemo"
}

dependencies {

    implementation(project(":ShapeButton"))
    implementation(AndroidX.core_ktx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.constraintlayout)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso_core)

    implementation(Libraries.material)
    testImplementation(Libraries.junit)
}