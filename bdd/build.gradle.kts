import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm")
}

group = "com.soat220"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")
    testImplementation("junit:junit:4.13.2")
    // https://mvnrepository.com/artifact/io.cucumber/cucumber-java8
    testImplementation("io.cucumber:cucumber-java8:7.13.0")
    // https://mvnrepository.com/artifact/io.cucumber/cucumber-junit
    testImplementation("io.cucumber:cucumber-junit:7.14.0")
    testImplementation(("io.rest-assured:rest-assured:4.3.0"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}