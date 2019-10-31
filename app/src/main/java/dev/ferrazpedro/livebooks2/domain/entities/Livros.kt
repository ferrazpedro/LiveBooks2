package dev.ferrazpedro.livebooks2.domain.entities

import java.util.*

class Livros(
    val titulo: String,
    val preco: Float,
    val escritor: String,
    val caminhoImagem: String,
    val data: Date
)