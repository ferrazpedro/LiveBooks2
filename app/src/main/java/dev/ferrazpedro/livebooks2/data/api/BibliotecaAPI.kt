package dev.ferrazpedro.livebooks2.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object BibliotecaAPI {

    private val repositorioClient = OkHttpClient().newBuilder().build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/Felcks/desafio-mobile-lemobs/master/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(repositorioClient)
        .build()


    val api: IBibliotecaAPI = retrofit().create(IBibliotecaAPI::class.java)

}