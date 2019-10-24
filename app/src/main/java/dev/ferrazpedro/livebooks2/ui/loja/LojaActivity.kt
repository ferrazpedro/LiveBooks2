package dev.ferrazpedro.livebooks2.ui.loja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ferrazpedro.livebooks2.R
import java.util.*

class LojaActivity : AppCompatActivity() {

    var pt_br : Locale = Locale("pt", "BR")
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(LojaViewModel::class.java)

        viewModel.getTodos()

        viewModel.livros().observe(this, Observer {
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
