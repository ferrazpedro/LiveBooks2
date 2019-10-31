package dev.ferrazpedro.livebooks2.domain.repository

import dev.ferrazpedro.livebooks2.data.api.BibliotecaAPI
import dev.ferrazpedro.livebooks2.data.api.IBibliotecaAPI
import dev.ferrazpedro.livebooks2.data.model.LivroResposta
import retrofit2.Response

class LivroRepositorio {

    private val api: IBibliotecaAPI = BibliotecaAPI.api

    suspend fun getLivros(): Response<List<LivroResposta>> {
        return api.getListaLivro()
    }
}