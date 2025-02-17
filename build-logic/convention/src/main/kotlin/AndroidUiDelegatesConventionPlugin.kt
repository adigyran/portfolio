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

class AndroidUiDelegatesConventionPlugin : Plugin<Project> {
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

                add("implementation", project(":core:ext"))
                add("implementation", project(":core:ui:base"))
                add("implementation", project(":core:ui:adapters"))
                add("implementation", project(":core:localisation"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:baseresources"))
                add("implementation", libs.findLibrary("material.design").get())
                add("implementation", libs.findLibrary("adapter.delegates").get())
                add("implementation", libs.findLibrary("adapter.delegates.view.binding").get())
                add("implementation", libs.findLibrary("groupie").get())
                add("implementation", libs.findLibrary("groupie.view.binding").get())
                add("implementation", libs.findLibrary("glide").get())
                add("implementation", libs.findLibrary("timber").get())
                add("kapt", libs.findLibrary("glide.annotationprocessor").get())


                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}