package com.github.afnascimento.mercadopago.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.afnascimento.mercadopago.R
import com.squareup.picasso.Picasso

/**
 * Created by Andre on 09/11/2018.
 */
class CardItemsAdapter<T : CardItem>(private val mListener: Listener<T>) : RecyclerView.Adapter<CardItemsAdapter.ViewHolder>() {

    interface Listener<T : CardItem> {
        fun onCardItemClick(item: T)
    }

    val lista : MutableList<T> = arrayListOf()

    fun setItems(items : MutableList<T>) {
        if (items.isEmpty()) {
            return
        }
        lista.clear()
        lista.addAll(items)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): T {
        return lista.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.payment_item_sel, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.contenClick.setOnClickListener { mListener.onCardItemClick(item) }

        Picasso.get()
                .load(item.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imgItem)

        holder.tvTextItem.text = item.name()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contenClick = itemView.findViewById<View>(R.id.contenClick)
        val imgItem = itemView.findViewById<ImageView>(R.id.imgItem)
        val tvTextItem = itemView.findViewById<TextView>(R.id.tvTextItem)
    }
}