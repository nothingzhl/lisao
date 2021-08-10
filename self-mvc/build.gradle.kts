plugins {
    java
    war
    id("com.bmuschko.tomcat") version "2.7.0"
}

repositories {
    mavenCentral()
}

dependencies {
    val tomcatVersion = "9.0.50"
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    providedCompile("javax.servlet:jsp-api:2.0")
    tomcat("org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}")
    tomcat("org.apache.tomcat.embed:tomcat-embed-logging-juli:9.0.0.M6")
    tomcat("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}")
}

tomcat {

    httpPort = 8090
    httpsPort = 8091
    contextPath = "/"

    httpProtocol = "org.apache.coyote.http11.Http11Nio2Protocol"
    ajpProtocol = "org.apache.coyote.ajp.AjpNio2Protocol"
    

}

;

val tomcatRun = tasks.getByName("tomcatRun");



