package dev.ferrazpedro.livebooks2.presentation.loja

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ferrazpedro.livebooks2.domain.use_case.LivroController
import dev.ferrazpedro.livebooks2.data.api.Response
import dev.ferrazpedro.livebooks2.domain.entities.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import dev.ferrazpedro.livebooks2.presentation.FundoInsuficiente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LojaViewModel : ViewModel() {

    var loading = ObservableField<Boolean>(false)
    var isError = ObservableField<Boolean>(false)

    private val repository: LivroController = LivroController(LivroRepositorio())
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

    val listaLivro = MutableLiveData<Response>()

    fun carregaListaLivro() {

        val controller = LivroController(LivroRepositorio())

        CoroutineScope(Dispatchers.IO).launch {

            try {
                loading.set(true)
                val retrieved = controller.getListaLivro()
                isError.set(false)
                listaLivro.postValue(Response.success(retrieved))

            } catch (e: Throwable){
            isError.set(true)
            listaLivro.postValue(Response.error(FundoInsuficiente()))

        } finally {
            loading.set(false)

        }
        }
    }
}