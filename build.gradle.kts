plugins {
    java
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.sonarqube") version "6.2.0.5505"
}

// Análisis estático: ./gradlew sonar -Dsonar.host.url=<servidor> -Dsonar.token=<token>
sonar {
    properties {
        property("sonar.projectKey", "rutas-api")
        property("sonar.projectName", "Iri Travel Peru - API")
        property("sonar.sources", "src/main/java,src/main/resources")
        property("sonar.exclusions", "src/main/resources/seed/**")
        property("sonar.coverage.exclusions", "**/*")
    }
}

group = "com.nexocercaati"
version = "0.1.0"
description = "Backend de Iri Travel Perú: API pública + administración"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val jjwtVersion = "0.12.6"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

    annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:all,-processing", "-parameters"))
}
