object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

/**
 * To define dependencies
 */
object Deps {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }

    //UI

    //adapterDelegates
    val adapterDelegates by lazy {"com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterDelegates}"}
    val adapterDelegatesViewBinding by lazy {"com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"}



    //navigation
    val cicerone by lazy {"com.github.terrakok:cicerone::${Versions.cicerone}"}

    //testing
    val testCore by lazy {"androidx.test:core:${Versions.testCore}"}
    val espresso by lazy {"androidx.test.espresso:espresso-core:${Versions.espressoCore}"}
    val jUnitRunner by lazy {"androidx.test:runner:${Versions.testRunner}"}
    val jUnitRules by lazy {"androidx.test:rules:${Versions.testRules}"}
    val extJUnit by lazy {"androidx.test.ext:junit:${Versions.testJunit}"}
    val extTruth by lazy {"androidx.test.ext:${Versions.testTruth}"}


}