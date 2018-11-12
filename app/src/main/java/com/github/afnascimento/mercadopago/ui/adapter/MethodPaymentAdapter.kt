package com.github.afnascimento.mercadopago.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.squareup.picasso.Picasso
import org.apache.commons.lang3.StringUtils

/**
 * Created by Andre on 09/11/2018.
 */
class MethodPaymentAdapter(private val mListener: Listener) : RecyclerView.Adapter<MethodPaymentAdapter.ViewHolder>() {

    interface Listener {
        fun onPaymentMethodClick(item: PaymentMethod)
    }

    val lista : MutableList<PaymentMethod> = arrayListOf()

    fun setItems(items : MutableList<PaymentMethod>) {
        if (items.isEmpty()) {
            return
        }
        lista.clear()
        lista.addAll(items)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): PaymentMethod {
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
        holder.bind(getItem(position), mListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PaymentMethod, listener: Listener) {
            itemView.setOnClickListener { listener.onPaymentMethodClick(item) }

            val imgItem = itemView.findViewById<ImageView>(R.id.imgItem)
            val tvTextItem = itemView.findViewById<TextView>(R.id.tvTextItem)

            Picasso.get()
                    .load(item.thumbnail)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgItem)

            tvTextItem.text = StringUtils.capitalize(StringUtils.lowerCase(StringUtils.trimToEmpty(item.name)))
        }
    }
}