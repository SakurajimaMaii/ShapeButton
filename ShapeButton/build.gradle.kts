import com.gcode.plugin.version.AndroidX
import com.gcode.plugin.version.Libraries
import com.gcode.plugin.version.Version

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
    id("com.gcode.plugin.version")
}

android {
    compileSdk = Version.compile_sdk_version
    buildToolsVersion = Version.build_tools_version

    defaultConfig {
        minSdk = Version.min_sdk_version
        targetSdk = Version.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    sourceSets["main"].java.srcDir("src/main/kotlin")

    namespace = "com.gcode.shapebutton"
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven")  {
                groupId = "io.github.sakurajimamaii"
                artifactId = "ShapeButton"
                version = "0.0.5"
            }
        }
    }
}

dependencies {
    implementation(AndroidX.core_ktx)
    implementation(AndroidX.appcompat)
    implementation(Libraries.material)
    testImplementation(Libraries.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso_core)
}