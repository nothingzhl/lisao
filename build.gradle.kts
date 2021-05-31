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
        set("springFrameworkVersion", "5.3.2")
        set("vavrVersion", "0.10.3")
        set("CCVersion", "4.1")
        set("javaFakerVersion", "1.0.2")
        set("jmockdataVersion", "4.2.0")
        set("jolVersion", "0.16")
        set("fastjsonVersion", "1.2.75")
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
        val javaFakerVersion = rootProject.ext["javaFakerVersion"] as String?
        val jmockdataVersion = rootProject.ext["jmockdataVersion"] as String?
        val jolVersion = rootProject.ext["jolVersion"] as String?
        val fastjsonVersion = rootProject.ext["fastjsonVersion"] as String?

        "implementation"("com.github.jsonzou", "jmockdata", "$jmockdataVersion")
        "implementation"("com.github.javafaker", "javafaker", "$javaFakerVersion")

        "implementation"("com.google.guava", "guava", "$guavaVersion")

        "implementation"("io.vavr", "vavr", "$vavrVersion")
        "implementation"("org.apache.commons", "commons-collections4", "$CCVersion")

        "implementation"("com.alibaba", "fastjson", "$fastjsonVersion")
        "compileOnly"("org.openjdk.jol", "jol-core", "$jolVersion")

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







