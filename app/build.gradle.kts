import java.util.Properties
import org.gradle.api.GradleException

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.services)

}

val properties = Properties().apply {
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(localPropertiesFile.inputStream())
    }
}

fun getRequiredProperty(key: String): String {

    val localValue = properties[key]?.toString()
    if (!localValue.isNullOrBlank()) {
        return localValue
    }

    val envValue = System.getenv(key.toUpperCase().replace('.', '_'))
    if (!envValue.isNullOrBlank()) {
        return envValue
    }

    throw GradleException("Property '$key' is missing. Please define it in local.properties or as an environment variable (e.g., ${key.toUpperCase().replace('.', '_')}) in CI/CD.")
}
android {
    namespace = "com.hsLink.hslink"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.hsLink.hslink"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"${getRequiredProperty("base.url")}\"")
        manifestPlaceholders["kakaoAppKey"] = getRequiredProperty("kakao.native.app.key")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"${getRequiredProperty("base.url")}\"")
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
        compose = true
        buildConfig = true
    }

}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.test)

    debugImplementation(libs.bundles.debug)

    implementation(libs.bundles.androidx)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.kotlinx.immutable)

    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.coil.compose)

    implementation(libs.timber)

    implementation(libs.accompanist.systemuicontroller)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // KaKao
    implementation("com.kakao.sdk:v2-user:2.20.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Paging3
    implementation("androidx.paging:paging-runtime-ktx:3.3.0")
    implementation("androidx.paging:paging-compose:3.3.0")

}