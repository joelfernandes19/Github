package joel.fernandes.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


fun createNetworkClient(baseUrl: String, debug: Boolean = false, apiKey : String = "", apiKeyParamName: String = "") =
    retrofitClient(baseUrl, httpClient(debug, apiKey, apiKeyParamName))

private fun httpClient(debug: Boolean, apiKey : String, apiKeyParamName : String): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }


    if(apiKey.isNotEmpty()) {
        if(apiKeyParamName.isEmpty()) throw Exception("Parameter name is required")

        clientBuilder.addInterceptor(object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/vnd.github.v3+json")
                    .url(originalHttpUrl.newBuilder()
                        .addQueryParameter(apiKeyParamName, apiKey).build()
                )

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
    }

    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
