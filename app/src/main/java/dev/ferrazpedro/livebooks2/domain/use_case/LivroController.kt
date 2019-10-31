package dev.ferrazpedro.livebooks2.domain.use_case

import dev.ferrazpedro.livebooks2.data.api.BibliotecaAPI.api
import dev.ferrazpedro.livebooks2.domain.entities.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import java.util.*

class LivroController(private val livroRepository: LivroRepositorio) {

    var listaLivro: List<Livros>? = null

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
    /*
    val response = livroRepository.getListaLivro()

    listaLivro = response
    return listaLivro ?: listOf()

    */

}