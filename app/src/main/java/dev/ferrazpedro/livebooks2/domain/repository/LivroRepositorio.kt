package dev.ferrazpedro.livebooks2.domain.repository

import dev.ferrazpedro.livebooks2.api.IBibliotecaAPI
import dev.ferrazpedro.livebooks2.domain.model.Livros
import java.util.*

class LivroRepositorio(private val api: IBibliotecaAPI) {

    suspend fun getListaLivro(): List<Livros> {

        val response = api.getListaLivro()

        if (!response.isSuccessful)
            throw Throwable(response.errorBody()?.string())

        return api.getListaLivro().body()?.map {
            Livros(
                it.title ?: "",
                it.price ?: 0.0,
                it.writer ?: "",
                it.thumbnailHd ?: "",
                Date()
            )
        } ?: listOf()

    }
}