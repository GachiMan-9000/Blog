plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":memory_repository")))
    implementation(project(mapOf("path" to ":sql_repository")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":models"))
    implementation(project(":repository"))
    implementation(project(":json"))
}

tasks.test {
    useJUnitPlatform()
}