import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    id("org.openapi.generator") version "7.4.0"
}

group = "com.backend"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    api("org.openapitools:jackson-databind-nullable:0.1.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.21")
    implementation("io.swagger.core.v3:swagger-models:2.1.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

val spec = "$rootDir/openapi/api.yml"
val generatedSourcesDir = "$buildDir/generated/openapi"

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            srcDirs("build/generated/openapi/src")
        }
    }
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set(spec)
    outputDir.set(generatedSourcesDir)
    apiPackage.set("com.openapi.exp.controller")
    modelPackage.set("org.openapi.exp.model")
    generateApiTests = false
    generateModelTests = false
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "useSpringBoot3" to "true",
        ),
    )
}

tasks {
    val openApiGenerate by getting

    val compileKotlin by getting {
        dependsOn(openApiGenerate)
    }
}
