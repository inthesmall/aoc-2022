import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "me.ethanmills.co.uk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.apache.commons/commons-csv
    implementation("org.apache.commons:commons-csv:1.9.0")
    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.11.0")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.12.0")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-collections4
    implementation("org.apache.commons:commons-collections4:4.4")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-math3
    implementation("org.apache.commons:commons-math3:3.6.1")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-text
    implementation("org.apache.commons:commons-text:1.10.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("MainKt")
}