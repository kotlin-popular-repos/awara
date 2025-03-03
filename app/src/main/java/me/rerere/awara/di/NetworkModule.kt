package me.rerere.awara.di

import me.rerere.awara.data.source.HitokotoAPI
import me.rerere.awara.data.source.IwaraAPI
import me.rerere.awara.util.SerializationConverterFactory
import me.rerere.compose_setting.preference.mmkvPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

private const val TAG = "NetworkModule"

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val url = request.url
                val newRequest = request.newBuilder()
                    .addHeader("User-Agent", "Awara Android Client")
                    .apply {
                        if(url.host == "api.iwara.tv") {
                            if (url.toString() == "https://api.iwara.tv/user/token") {
                                if (mmkvPreference.contains("refresh_token")) {
                                    addHeader(
                                        "Authorization",
                                        "Bearer ${mmkvPreference.getString("refresh_token", "")}"
                                    )
                                }
                            } else {
                                if (mmkvPreference.contains("access_token")) {
                                    addHeader(
                                        "Authorization",
                                        "Bearer ${mmkvPreference.getString("access_token", "")}"
                                    )
                                }
                            }
                        }
                    }
                    .build()
                it.proceed(newRequest)
            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                }
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.iwara.tv")
            .addConverterFactory(SerializationConverterFactory.create())
            .build()
            .create(IwaraAPI::class.java)
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://v1.hitokoto.cn")
            .addConverterFactory(SerializationConverterFactory.create())
            .build()
            .create(HitokotoAPI::class.java)
    }
}