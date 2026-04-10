plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myfirstapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.myfirstapp"
        minSdk = 27
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
        var kotlinCompilerExtensionVersion = "1.5.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // 1. Compose BOM (버전 관리용 맵)
    // 개별 라이브러리의 버전을 일일이 지정하지 않아도 서로 호환되는 버전을 알아서 맞춰줍니다.
    val composeBom = platform("androidx.compose:compose-bom:2024.02.01")
    implementation("androidx.compose:compose-bom:2026.03.01")
    androidTestImplementation("androidx.compose:compose-bom:2026.03.01")

    // 2. 핵심 UI 라이브러리
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview") // Preview 기능

    // 3. Material 3 디자인 (안드로이드 최신 디자인 시스템)
    implementation("androidx.compose.material3:material3")

    // 4. Activity 연결 (ComponentActivity 등 Compose를 실행하기 위함)
    implementation("androidx.compose.runtime:runtime") // state 관리 핵심
    implementation("androidx.activity:activity-compose:1.13.0")

    // 5. 툴링 (디버그 시 필수)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("androidx.navigation:navigation-compose:2.9.7")
    implementation("io.coil-kt:coil-compose:2.7.0")

}