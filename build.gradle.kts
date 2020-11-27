allprojects {
    group = "org.zhl"
    version = "1.0"

    ext{
        set("guavaVersion","29.0-jre")
        set("junitVersion","5.6.2")
        set("lombokVersion","1.18.16")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "application")

    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {

        val guavaVersion = rootProject.ext["guavaVersion"]
        val junitVersion = rootProject.ext["junitVersion"]
        val lombokVersion = rootProject.ext["lombokVersion"]

        "implementation"("com.google.guava:guava:${guavaVersion}")

        "testImplementation"("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params:${junitVersion}")

        "compileOnly"("org.projectlombok:lombok:${lombokVersion}")
        "annotationProcessor"("org.projectlombok:lombok:1.18.16")
        "testCompileOnly"("org.projectlombok:lombok:${lombokVersion}")
        "testAnnotationProcessor"("org.projectlombok:lombok:1.18.16")
    }
    
    val test by tasks.getting(Test::class) {
        useJUnitPlatform()
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}







