plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin.android)
    `version-catalog`
    `maven-publish`
}

android {
    namespace = "org.zhuxiaole.gradle.catalog"
    compileSdk = 33

    defaultConfig {
        applicationId = "org.zhuxiaole.gradle.catalog"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

catalog {
    versionCatalog {
        from(files("../libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.zhuxiaole.gradle"
            artifactId = "catalog"
            version = "0.0.1"
            from(components["versionCatalog"])
        }
    }
}