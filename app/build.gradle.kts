plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.kotlinquiz.app"

        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))


    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
    androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
    androidTestImplementation("it.xabaras.android.espresso:recyclerview-child-actions:1.0")
    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.3.0")

    //test
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
    testImplementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.mockito:mockito-inline:3.5.13")


    //Dagger
    kaptAndroidTest("com.google.dagger:dagger-compiler:2.30.1")


    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")


    kapt("com.google.dagger:dagger-compiler:2.30.1")
    kapt("androidx.room:room-compiler:2.2.6")
    kapt("com.github.bumptech.glide:compiler:4.11.0")
    kapt("com.google.dagger:dagger-compiler:2.30.1")
    kapt("androidx.room:room-runtime:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")

    kaptTest("com.google.dagger:dagger-compiler:2.30.1")

/////////////////////////////////////////////////////////////////////////////////////////////////////


    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("com.google.android.material:material:1.3.0-rc01")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.room:room-runtime:2.2.6")
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("com.google.android.gms:play-services-vision:20.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")

    implementation("androidx.recyclerview:recyclerview:1.1.0")

    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.3")
    // Retrofit
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")


    //firebase
    implementation("com.google.firebase:firebase-analytics:18.0.1")
    implementation("com.google.firebase:firebase-database-ktx:19.6.0")


    // kotlinLibs
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    //libs
    implementation("com.karumi:dexter:6.2.1")
    implementation("com.google.dagger:dagger:2.30.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("com.squareup.picasso:picasso:2.71828")

    //admod
    implementation("com.google.android.gms:play-services-ads:19.7.0")

    implementation("com.github.ThomyChancaMedina:ToolsDesign:1.0.1")

    implementation("androidx.palette:palette-ktx:1.0.0")
}