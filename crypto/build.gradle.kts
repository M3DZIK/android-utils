plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "dev.medzik.android.crypto"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = false
    }
}

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.libcrypto)

    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso.core)
}

afterEvaluate {
    publishConfig {
        artifactId = "crypto"

        pom {
            name.set("Android Crypto Utilities")
            description.set("Android Utilities for Application Development - Crypto")
        }
    }
}
