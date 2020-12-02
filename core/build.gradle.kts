tasks.withType<JavaCompile>{
    // 限制不安全是内部专有API的警告
    options.compilerArgs = listOf("-XDignore.symbol.file","-Xlint:deprecation")
    options.forkOptions.executable = "javac"
}