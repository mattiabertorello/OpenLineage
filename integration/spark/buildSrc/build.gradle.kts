plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

gradlePlugin {
    plugins {
        create("printSourceSetConfiguration") {
            id = "io.openlineage.print-source-set-configuration"
            implementationClass = "io.openlineage.gradle.plugin.PrintSourceSetConfigurationPlugin"
        }
    }
}
