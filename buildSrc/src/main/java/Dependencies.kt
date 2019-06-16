object ApplicationId {
    val id = "joel.fernandes.github"
}

object Modules {
    val app = ":app"
    val network = ":network"
    val cache = ":cache"
}


object Versions {
    val kotlin = "1.3.30"
    val ktx = "1.0.2"

    val appVersionCode = 1
    val appVersionName = "1.0.0"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28


    //SupportLibraries
    val appcompat = "1.1.0-beta01"
    val design = "1.1.0-alpha06"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val constraintlayout = "2.0.0-beta1"

    //TestLibraries
    val junit = "4.12"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.23.4"


    //Libraries
    val gson = "2.8.5"
    val paperdb = "2.6"
    val koin = "2.0.0-alpha-6"
    val timber = "4.7.1"
    val rxjava = "2.2.9"
    val rxandroid = "2.1.1"
    val rxkotlin = "2.3.0"
    val retrofit = "2.5.0"
    val loggingInterceptor = "3.12.1"
    val glide = "4.9.0"
    val lottieVersion = "3.0.6"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val design = "com.google.android.material:material:${Versions.design}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val runner = "androidx.test:runner:1.1.1"
    val espresso = "androidx.test.espresso:espresso-core:3.1.1"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val paperdb = "io.paperdb:paperdb:${Versions.paperdb}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"

    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}