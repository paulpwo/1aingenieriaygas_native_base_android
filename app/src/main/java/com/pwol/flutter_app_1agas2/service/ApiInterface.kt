package com.pwol.flutter_app_1agas2.service

import com.pwol.flutter_app_1agas2.Config
import com.pwol.flutter_app_1agas2.service.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import com.pwol.flutter_app_1agas2.service.model.UserLogin

import android.R.string.no
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


interface ApiInterface {


    @POST("jwt-auth/v1/token")
    fun setLogin(@Body userLogin: UserLogin): Call<User>

    companion object {

        var BASE_URL = Config.api_base_url

        fun create() : ApiInterface {
            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
            }.build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }



}