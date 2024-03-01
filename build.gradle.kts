plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"

    id("org.liquibase.gradle") version "2.2.0"
}

group = "ru.zencode"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    liquibaseRuntime("org.liquibase:liquibase-core")
    liquibaseRuntime("ch.qos.logback:logback-core:1.2.3")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.2.3")
    liquibaseRuntime("info.picocli:picocli:4.7.5")
    liquibaseRuntime("org.yaml:snakeyaml:1.33")
    liquibaseRuntime("org.postgresql:postgresql")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

liquibase {
    activities.register("main") {
        val dbUrl = System.getenv("DB_URL")
        val dbUser = System.getenv("DB_USERNAME")
        val dbPassword = System.getenv("DB_PASSWORD")
        this.arguments = mapOf(
            "logLevel" to "info",
            "searchPath" to "src/main/resources/",
            "changeLogFile" to "db/changelog/db.changelog-master.xml",
            "url" to dbUrl,
            "username" to dbUser,
            "password" to dbPassword
        )
    }
    runList = "main"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
