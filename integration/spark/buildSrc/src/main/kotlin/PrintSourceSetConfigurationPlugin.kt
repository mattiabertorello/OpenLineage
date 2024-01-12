package io.openlineage.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer

class PrintSourceSetConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("printSourceSetConfiguration") {
            doLast {
                val sourceSets = project.extensions.getByType(SourceSetContainer::class.java)
                sourceSets.forEach { srcSet ->
                    println("[${srcSet.name}]")
                    println("-->Source directories: ${srcSet.allJava.srcDirs}")
                    println("-->Output directories: ${srcSet.output.classesDirs.files}")
                    println("-->Compile classpath:")
                    srcSet.compileClasspath.files.sortedBy { it.path }.forEach {
                        println("  ${it.path}")
                    }
                    println("")
                }
            }
        }
    }
}

