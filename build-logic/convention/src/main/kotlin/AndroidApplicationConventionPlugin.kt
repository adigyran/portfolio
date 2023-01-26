import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.dsl.ApplicationExtension
import com.aya.digital.healthapp.configureFlavors
import com.aya.digital.healthapp.configureKotlinAndroid
import com.aya.digital.healthapp.configurePrintApksTask
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.dokka")
                apply("com.vanniktech.dependency.graph.generator")
            }

            tasks.named<DokkaTaskPartial>("dokkaHtmlPartial",DokkaTaskPartial::class.java,{
                outputDirectory.set(buildDir.resolve("docs/partial"))
            })

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
                defaultConfig.manifestPlaceholders.put("appAuthRedirectScheme","com.aya.digital.healthapp")
                configureFlavors(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
        }
    }

}