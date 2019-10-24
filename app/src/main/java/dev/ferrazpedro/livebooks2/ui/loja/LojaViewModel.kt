package dev.ferrazpedro.livebooks2.ui.loja

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.ferrazpedro.livebooks2.api.BibliotecaAPI
import dev.ferrazpedro.livebooks2.domain.model.Livros
import dev.ferrazpedro.livebooks2.domain.repository.LivroRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LojaViewModel : ViewModel() {

    private val repository: LivroRepositorio = LivroRepositorio(BibliotecaAPI.api)
    private val livros: MutableLiveData<List<Livros>> = MutableLiveData()

    fun livros() = livros as LiveData<List<Livros>>

    fun getTodos(){
        GlobalScope.launch(context = Dispatchers.IO) {

            try {
                livros.postValue(repository.getListaLivro())
            } catch (e: Exception) {
                Log.d("teste", "error")
            }
        }
    }

}