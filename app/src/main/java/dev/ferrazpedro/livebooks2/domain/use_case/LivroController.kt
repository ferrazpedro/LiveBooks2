package dev.ferrazpedro.livebooks2.domain.use_case

import dev.ferrazpedro.livebooks2.domain.entities.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio

class LivroController(private val livroRepository: LivroRepositorio) {

    var listaLivro: List<Livros>? = null

    suspend fun getListagemLivro(): List<Livros> {

        val response = livroRepository.getListaLivro()

        listaLivro = response
        return listaLivro ?: listOf()
    }
}