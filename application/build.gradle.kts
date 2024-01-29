import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("jacoco")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // relatório é sempre gerado após os testes
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)

        val jacocoXmlReportPath = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
        xml.destination = jacocoXmlReportPath

        val jacocoHTMLReportPath = file("$buildDir/reports/jacoco/html")
        html.destination = jacocoHTMLReportPath

        doLast {
            if (!jacocoXmlReportPath.exists()) {
                throw GradleException("JaCoCo XML report not found at: $jacocoXmlReportPath")
            }

            println("JaCoCo XML report generated at: $jacocoXmlReportPath")
        }
    }
}
