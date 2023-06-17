import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.inject:javax.inject:1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.7.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-inline:4.3.1")
    testImplementation("io.mockk:mockk:1.13.4")
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