tasks.withType<JavaCompile> {
    // 限制不安全是内部专有API的警告
    options.compilerArgs = listOf("-XDignore.symbol.file")
//    options.forkOptions.executable = "javac"
    options.isFork = true
}

dependencies {
    val current = org.gradle.internal.jvm.Jvm.current()
    // 添加tool到classpath中
//    implementation(files(current?.toolsJar ?: ""))
}

tasks.create<Jar>("agentJar") {

    // manifest的配置
    val manifestConfig = mutableMapOf<String, Any>()
            .apply {
                this["Agent-Class"] = "org.zhl.agent.Agent"
                this["Premain-Class"] = "org.zhl.agent.Agent"
                this["Can-Redefine-Classes"] = true
                this["Can-Retransform-Classes"] = true
            }

    manifest.attributes(manifestConfig)

    from("$buildDir/classes/java/main/org/zhl/agent/Agent.class")
//    destinationDirectory.set(file("$buildDir/agent"))
    archiveBaseName.set("JavaAgent")
    archiveVersion.set("1.0.0")
}


tasks.create<Delete>("deleteAgent") {
    delete("$buildDir/agent")
}

/**
tasks.create<JavaCompile>("buildMain") {
//    source = sourceSets.main.get().java.asFileTree
include("org/zhl/agent/Agent.java")
classpath = sourceSets.main.get().compileClasspath
//    destinationDir = file("$buildDir/agent")
}

tasks.create<JavaExec>("runAgentMain") {
dependsOn("agentJar")
dependsOn("buildMain")
classpath = sourceSets.main.get().runtimeClasspath
main = "org.zhl.agent.TestAgent"
jvmArgs = listOf("-javaagent:$buildDir/agent/JavaAgent-1.0.0.jar")
}
 **/