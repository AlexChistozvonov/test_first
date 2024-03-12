buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}