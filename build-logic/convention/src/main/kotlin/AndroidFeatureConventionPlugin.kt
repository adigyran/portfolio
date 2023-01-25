import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.aya.digital.healthapp.configureFlavors
import com.aya.digital.healthapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("healthapp.android.library")
                apply("kotlin-parcelize")
                apply("org.jetbrains.dokka")
            }
            /* extensions.configure<LibraryExtension> {
                 defaultConfig {
                     testInstrumentationRunner =
                         "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
                 }
             }*/

            tasks.named<DokkaTaskPartial>("dokkaHtmlPartial", DokkaTaskPartial::class.java,{
                outputDirectory.set(buildDir.resolve("docs/partial"))
            })

            extensions.configure<LibraryExtension> {
                viewBinding.enable = true
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:dibase"))
                add("implementation", project(":core:di"))
                add("implementation", project(":core:baseresources"))
                add("implementation", project(":core:navigation"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:uibase"))
                add("implementation", project(":core:ext"))
                add("implementation", project(":core:mvi"))
                add("implementation", project(":core:util"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":core:testing"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":core:testing"))

                add("implementation", libs.findLibrary("orbit.viewmodel").get())
                add("implementation", libs.findLibrary("kodein").get())
                add("implementation", libs.findLibrary("kodein.framework.androidx").get())
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("material.design").get())
                add("implementation", libs.findLibrary("cicerone").get())
                add("implementation", libs.findLibrary("androidx.lifecycle").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModel").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.livedata-ktx").get())
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}
