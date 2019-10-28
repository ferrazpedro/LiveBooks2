package dev.ferrazpedro.livebooks2.ui.loja

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ferrazpedro.livebooks.utils.AppSharedPreferences
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.databinding.ActivityLojaBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class LojaActivity : AppCompatActivity() {

    var pt_br: Locale = Locale("pt", "BR")
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var adapter: LojaAdapter

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
