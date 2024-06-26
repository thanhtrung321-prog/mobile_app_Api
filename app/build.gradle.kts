plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.vothanhtrung_shop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vothanhtrung_shop"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding =true;
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("org.mindrot:jbcrypt:0.4")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.android.volley:volley:1.2.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.mediarouter:mediarouter:1.6.0")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.navigation:navigation-fragment:2.7.7")
    implementation("androidx.navigation:navigation-ui:2.7.7")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}