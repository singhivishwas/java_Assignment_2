plugins {
    id("java")
}

group = "Assignment2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.projectlombok:lombok:1.18.28")
    annotationProcessor ("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.28") // For tests
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.2") // Core Jackson library
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.15.2") // Jackson annotations
    implementation ("com.fasterxml.jackson.core:jackson-core:2.15.2") // Core utilities for JSON
}

tasks.test {
    useJUnitPlatform()
}