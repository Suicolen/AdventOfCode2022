plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("one.util:streamex:0.8.1")
    val lombok = module("org.projectlombok", "lombok", "1.18.24")

    compileOnly(lombok)
    annotationProcessor(lombok)

    testCompileOnly(lombok)
    testAnnotationProcessor(lombok)
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.jsoup:jsoup:1.15.3")


}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}