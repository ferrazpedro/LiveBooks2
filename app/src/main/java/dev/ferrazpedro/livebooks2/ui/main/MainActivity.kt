package dev.ferrazpedro.livebooks2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.api.BibliotecaAPI
import dev.ferrazpedro.livebooks2.domain.model.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import dev.ferrazpedro.livebooks2.ui.loja.LojaActivity
import dev.ferrazpedro.livebooks2.ui.loja.LojaAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val livroRepository = LivroRepositorio(BibliotecaAPI.api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var repository: LivroRepositorio = LivroRepositorio(BibliotecaAPI.api)

        var livros: List<Livros>

        CoroutineScope(Dispatchers.IO).launch {
            livros = repository.getListaLivro()
        }

        CoroutineScope(Dispatchers.IO).launch {

            var listaLivro = repository.getListaLivro()

            runOnUiThread {
                val layoutManager = LinearLayoutManager(this@MainActivity)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerViewMain.layoutManager = layoutManager
                recyclerViewMain.setItemViewCacheSize(listaLivro.size)

                val adapter = MainAdapter.ColeçãoLivros(
                    this@MainActivity.applicationContext,
                    listaLivro
                )
                recyclerViewMain.adapter = adapter
            }
        }
        
        iniBtnLoja()
    }
    fun iniBtnLoja() {

        botaoComprar.setOnClickListener {

            var moverParaLoja = Intent(applicationContext, LojaActivity::class.java)
            startActivity(moverParaLoja)
        }
    }
}
