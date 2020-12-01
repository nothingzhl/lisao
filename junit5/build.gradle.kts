dependencies {

    var log4jVersion = rootProject.ext["log4jVersion"] as String?

    implementation("org.apache.logging.log4j", "log4j-api", "$log4jVersion")
    implementation("org.apache.logging.log4j", "log4j-core", "$log4jVersion")
}