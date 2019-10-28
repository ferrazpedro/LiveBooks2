package dev.ferrazpedro.livebooks2.api

import retrofit2.Response
import retrofit2.http.GET

interface IBibliotecaAPI {

    @GET("products.json")
    suspend fun getListaLivro(): Response<List<LivroResposta>>
}