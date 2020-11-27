allprojects {
    group = "org.zhl"
    version = "1.0"
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

        "implementation"("com.google.guava:guava:29.0-jre")
        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.6.2")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.6.2")

        "compileOnly"("org.projectlombok:lombok:1.18.16")
        "annotationProcessor"("org.projectlombok:lombok:1.18.16")

        "testCompileOnly"("org.projectlombok:lombok:1.18.16")
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







