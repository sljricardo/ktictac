plugins {
    kotlin("jvm") version "2.1.0"
    application // Add the application plugin
}

group = "com.sljricardo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}

// Configure the application plugin
application {
    // Replace with your fully qualified main class name
    mainClass.set("com.sljricardo.MainKt")
}

// Configure the JAR task to include the Main-Class attribute
tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    // Include all dependencies in the JAR for an executable fat JAR
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
