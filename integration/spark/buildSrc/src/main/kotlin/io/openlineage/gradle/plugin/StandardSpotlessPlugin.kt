package io.openlineage.gradle.plugin

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.spotless.FormatterFunc
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class StandardSpotlessPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("com.diffplug.spotless")

        val disallowWildcardImports = FormatterFunc { text ->
            val regex = Regex("^import\\s+\\w+(\\.\\w+)*\\.*;$")
            val m = regex.find(text)
            if (m != null) {
                throw RuntimeException("Wildcard imports are disallowed - ${m.groupValues}")
            }
            text
        }

        target.extensions.configure<SpotlessExtension> {
            java {
                googleJavaFormat()
                removeUnusedImports()
                custom("disallowWildcardImports", disallowWildcardImports)
            }
        }
    }
}
