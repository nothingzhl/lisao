dependencies {

    val springFrameworkVersion = rootProject.ext["springFrameworkVersion"] as String?
    val springFrameworkGroup = "org.springframework"

    val imp = listOf("spring-core","spring-context")
    val testImp = listOf("spring-test")

    imp.forEach {
        implementation(springFrameworkGroup,it,springFrameworkVersion)
    }

    testImp.forEach {
       testImplementation(springFrameworkGroup,it,springFrameworkVersion)
    }

}