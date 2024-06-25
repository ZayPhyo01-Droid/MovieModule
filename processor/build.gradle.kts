plugins {
    id("java-library")
    id("kotlin")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("org.jetbrains.kotlin.plugin.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":annotations"))
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.10-1.0.13")
    implementation(libs.kotlinpoet)
    implementation(libs.arrow)
    implementation(libs.bundles.bundleKtor)
    implementation(project(":ktor"))

}