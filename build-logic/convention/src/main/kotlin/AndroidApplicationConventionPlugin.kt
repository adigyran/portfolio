import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.dsl.ApplicationExtension
import com.aya.digital.healthapp.*
import com.aya.digital.healthapp.configureKotlinAndroid
import com.aya.digital.healthapp.configurePrintApksTask
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val aliases = listOf<String>(
                "android-application",
                "kotlin-android",
                "kotlin-kapt",
                "detekt",
                "dokka",
                "dependency-graph-generator",
                "firebase-crashlytics",
                "firebase-appdistribution",
                "gms-googleServices"
            )
            with(pluginManager) {
                applyVersionCatalogPlugins(libs,aliases)
            }

           /* tasks.named<DokkaTaskPartial>("dokkaHtmlPartial",DokkaTaskPartial::class.java) {
                outputDirectory.set(buildDir.resolve("docs/partial"))
            }*/

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
