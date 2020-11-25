plugins {
    id("java-library")
    id("kotlin")
}



dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
}
