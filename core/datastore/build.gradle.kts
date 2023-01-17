import com.google.protobuf.gradle.builtins
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc


plugins {
    id("patientapp.android.library")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.aya.digital.core.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering {
                    option("lite")
                }
                val kotlin by registering {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:security"))
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.rxjava3)
    implementation(libs.protobuf.kotlin.lite)
    implementation(libs.kodein.framework.androidx)
    implementation(libs.kotlinx.serialization.protobuf)
    implementation(libs.tink)

}