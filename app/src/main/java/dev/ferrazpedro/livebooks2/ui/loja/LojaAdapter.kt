package dev.ferrazpedro.livebooks2.ui.loja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.domain.model.Livros
import kotlinx.android.synthetic.main.item_card.view.*

class LojaAdapter(
    private val listaLivro: List<Livros>
) :
    RecyclerView.Adapter<LojaAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int = listaLivro.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = listaLivro[position]

        holder.itemView.title.text = item.titulo
        holder.itemView.writer.text = item.escritor
        holder.itemView.price.text = item.preco.toString()
        Picasso
            .get()
            .load(item.caminhoImagem)
            .into(holder.itemView.thumbnailHd)
        //
    }
}