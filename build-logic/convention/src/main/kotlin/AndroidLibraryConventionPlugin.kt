import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.aya.digital.healthapp.applyVersionCatalogPlugins
import com.aya.digital.healthapp.configureFlavors
import com.aya.digital.healthapp.configureKotlinAndroid
import com.aya.digital.healthapp.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val aliases = listOf<String>(
                "android-library",
                "kotlin-android",
                "kotlin-kapt",
                "detekt",
                "dokka",
                "dependency-graph-generator"
            )
            with(pluginManager) {
                applyVersionCatalogPlugins(libs,aliases)
                apply("kotlin-parcelize")
            }

            tasks.named("dokkaHtmlPartial", DokkaTaskPartial::class.java) {
                outputDirectory.set(buildDir.resolve("docs/partial"))
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
                configureFlavors(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }

            configurations.configureEach {
                resolutionStrategy {
                    // force(libs.findLibrary("junit4").get())
                    // Temporary workaround for https://issuetracker.google.com/174733673
                    force("org.objenesis:objenesis:2.6")
                }
            }
            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}