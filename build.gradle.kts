import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot") version "2.2.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.8itsequence.apps.spring"
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation(springBootStarter("data-jpa"))
    implementation(springBootStarter("web"))

    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:2.5.3")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("com.github.javafaker:javafaker:1.0.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    register("bootRunDev") {
        dependsOn(setOf("build"))

        group = "application"
        description = "Runs project dev profile"

        doFirst {
            bootRun.configure {
                systemProperty("spring.profiles.active", "dev")
            }
        }

        finalizedBy("bootRun")
    }
}

fun DependencyHandler.springBootStarter(module: String, version: String? = null): Any =
    "org.springframework.boot:spring-boot-starter-$module${version?.let { ":$version" } ?: ""}"