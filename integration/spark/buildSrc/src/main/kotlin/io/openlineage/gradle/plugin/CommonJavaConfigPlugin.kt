package io.openlineage.gradle.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.maven

class CommonJavaConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        configurePlugins(target)
        configureJava(target)
        configureRepositories(target)
    }

    private fun configurePlugins(target: Project) {
        target.plugins.apply("java-library")
    }

    private fun configureJava(target: Project) {
        target.extensions.getByType<JavaPluginExtension>().apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    private fun configureRepositories(target: Project) {
        target.repositories.mavenCentral()
        target.repositories.mavenLocal()
        target.repositories.maven("https://astronomer.jfrog.io/artifactory/maven-public-libs-snapshot")
    }
}
