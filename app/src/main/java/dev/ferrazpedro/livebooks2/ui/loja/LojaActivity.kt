package dev.ferrazpedro.livebooks2.ui.loja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.databinding.ActivityLojaBinding
import java.util.*

class LojaActivity : AppCompatActivity() {

    var pt_br: Locale = Locale("pt", "BR")
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lojaViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LojaViewModel::class.java)

        val lojaBinding: ActivityLojaBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_loja)
        lojaBinding.lojaModel = lojaViewModel
        lojaBinding.executePendingBindings()


        lojaViewModel.getTodos()

        lojaViewModel.livros().observe(this, Observer {
            viewManager = LinearLayoutManager(this@LojaActivity)
            viewAdapter = LojaAdapter(it)
            recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMain).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })
    }
}
