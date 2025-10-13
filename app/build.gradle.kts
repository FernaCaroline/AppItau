plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Se estiver usando o Compose Compiler plugin do catálogo, mantenha:
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.bancoitau"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bancoitau"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
    // Se seu catálogo não controla a versão do Compose Compiler, declare aqui:
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

val room_version = "2.6.1"

dependencies {
    // Base AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM centraliza versões
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI
    implementation(libs.androidx.ui)
    implementation("androidx.compose.ui:ui-text")        // <- necessário p/ KeyboardOptions/KeyboardType
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.foundation)
    // Removi duplicatas/itens ambíguos; se tiver `libs.androidx.foundation.layout`, pode tirar.
    // implementation(libs.androidx.foundation.layout)

    // Navegação Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Lifecycle + Compose (necessários para viewModel() e collectAsState)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // Testes
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug/Preview
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
