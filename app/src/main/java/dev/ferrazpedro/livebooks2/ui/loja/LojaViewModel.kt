package dev.ferrazpedro.livebooks2.ui.loja

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ferrazpedro.livebooks2.api.BibliotecaAPI
import dev.ferrazpedro.livebooks2.api.LivroController
import dev.ferrazpedro.livebooks2.domain.model.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LojaViewModel : ViewModel() {

    private val livroController: LivroController? = null
    var loading = ObservableField<Boolean>(false)
    var isError = ObservableField<Boolean>(false)

    private val repository: LivroRepositorio = LivroRepositorio(BibliotecaAPI.api)
    private val livros: MutableLiveData<List<Livros>> = MutableLiveData()

    fun livros() = livros as LiveData<List<Livros>>

    fun getTodos() {
        GlobalScope.launch(context = Dispatchers.IO) {

            try {
                livros.postValue(repository.getListaLivro())
            } catch (e: Exception) {
                Log.d("teste", "error")
            }
        }
    }

    val listaLivro = MutableLiveData<LojaResponse>()

    fun carregaListaLivro() {

        val controller = LivroRepositorio(BibliotecaAPI.api)

        CoroutineScope(Dispatchers.IO).launch {

            try {
                val retrieved = controller.getListaLivro()
                listaLivro.postValue(
                    LojaResponse.success(
                        retrieved
                    )
                )

            } catch (e: Exception) {
                listaLivro.postValue(
                    LojaResponse.error(
                        Throwable(
                            ""
                        )
                    )
                )
            }
        }
    }
}