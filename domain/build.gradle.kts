plugins {
    id("java-library")
    id("kotlin")
}



dependencies {
    implementation(fileTree("libs"){include(listOf("*.jar"))})
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
}

