buildscript {
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        jcenter()
        google()
        mavenCentral()
    }
}


allprojects {
    group = "org.zhl"
    version = "1.0"

    ext {
        set("guavaVersion", "29.0-jre")
        set("junitVersion", "5.6.2")
        set("lombokVersion", "1.18.16")
        set("log4jVersion", "2.14.0")
        set("springFrameworkVersion", "5.3.2");
        set("vavrVersion", "0.10.3")
        set("CCVersion","4.1")
    }


}

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")
    apply(plugin = "idea")

    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {

        val guavaVersion = rootProject.ext["guavaVersion"] as String?
        val junitVersion = rootProject.ext["junitVersion"] as String?
        val lombokVersion = rootProject.ext["lombokVersion"] as String?
        val vavrVersion = rootProject.ext["vavrVersion"] as String?
        val CCVersion = rootProject.ext["CCVersion"] as String?

        "implementation"("com.google.guava", "guava", "$guavaVersion")

        "implementation"("io.vavr", "vavr", "$vavrVersion")
        "implementation"("org.apache.commons", "commons-collections4", "$CCVersion")


        "testImplementation"("org.junit.jupiter", "junit-jupiter-api", "$junitVersion")
        "testRuntimeOnly"("org.junit.jupiter", "junit-jupiter-engine", "$junitVersion")
        "testImplementation"("org.junit.jupiter", "junit-jupiter-params", "$junitVersion")

        "compileOnly"("org.projectlombok", "lombok", "$lombokVersion")
        "annotationProcessor"("org.projectlombok", "lombok", "$lombokVersion")
        "testCompileOnly"("org.projectlombok", "lombok", "$lombokVersion")
        "testAnnotationProcessor"("org.projectlombok", "lombok", "$lombokVersion")
    }

    val test by tasks.getting(Test::class) {
        useJUnitPlatform()
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}







