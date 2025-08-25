dependencies {
    implementation(project(":lemonmint-api"))
    implementation("org.graalvm.polyglot:polyglot:24.2.2")
    implementation("org.graalvm.polyglot:js:24.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
    compilerArgs.add("-Xlint:-module")
    compilerArgs.add("-Xlint:-removal")
    compilerArgs.add("-Xlint:-dep-ann")
}

tasks.test {
    useJUnitPlatform()
}
