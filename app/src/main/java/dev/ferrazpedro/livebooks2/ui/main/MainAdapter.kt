package dev.ferrazpedro.livebooks2.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.ferrazpedro.livebooks2.R
import dev.ferrazpedro.livebooks2.domain.model.Livros
import kotlinx.android.synthetic.main.item_card.view.*

class MainAdapter {

    class ColeçãoLivros (context: Context,
                               private var listaLivro: List<Livros>): RecyclerView.Adapter<ColeçãoLivros.MyViewHolder>() {

        val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = mLayoutInflater.inflate(R.layout.item_card, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int = listaLivro.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val item = listaLivro[position]

            holder.itemView.title.text = item.titulo
            holder.itemView.writer.text = item.escritor
            Picasso
                .get()
                .load(item.caminhoImagem)
                .into(holder.itemView.thumbnailHd)
            //
        }

        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
    }

}