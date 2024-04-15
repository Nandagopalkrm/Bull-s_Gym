plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gym.bullsgym"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gym.bullsgym"
        minSdk = 30
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // CircularImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

//    implementation ("com.github.florent37:runtime-permission:1.1.2")  //for runtime permission
    implementation ("com.github.bumptech.glide:glide:4.12.0")   //show images
//    debugImplementation ("com.amitshekhar.android:debug-db:1.0.6") //view database
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") // for viewModelScope
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // for lifecycleScope
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // for lifecycleScope
}