package dev.ferrazpedro.livebooks2.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ferrazpedro.livebooks.utils.AppSharedPreferences
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.data.api.BibliotecaAPI
import dev.ferrazpedro.livebooks2.domain.entities.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import dev.ferrazpedro.livebooks2.presentation.loja.LojaActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var pt_br: Locale = Locale("pt", "BR")

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
        checarPrimeiraVez()
    }

    fun checarPrimeiraVez() {

        if (!AppSharedPreferences.pegarEstado(applicationContext)) {
            AppSharedPreferences.darEstado(applicationContext, true)
            AppSharedPreferences.darSaldo(applicationContext, 100.00f)

        } else {
            var saldo = AppSharedPreferences.pegarSaldo((applicationContext))
            saldoMenu.text = NumberFormat.getCurrencyInstance(pt_br).format(saldo)
        }
    }

    fun iniBtnLoja() {

        botaoComprar.setOnClickListener {

            var moverParaLoja = Intent(applicationContext, LojaActivity::class.java)
            startActivity(moverParaLoja)
        }
    }
}
