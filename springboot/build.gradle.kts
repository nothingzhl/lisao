fun getSpringBootVersion(): String? {
    return rootProject.ext["springBootVersion"] as String?
}

plugins {
    id("org.springframework.boot") version ("2.3.3.RELEASE")
    id("io.spring.dependency-management") version ("1.0.8.RELEASE")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}