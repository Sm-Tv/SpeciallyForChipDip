package com.example.chipdip.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chipdip.R
import com.example.chipdip.databinding.ItemValuteBinding
import com.example.chipdip.model.valute.ItemValute
import kotlin.math.abs

class ValuteAdapter(private val context: Context) : RecyclerView.Adapter<ValuteAdapter.ItemHolder>() {


    private var items = mutableListOf<ItemValute>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: MutableList<ItemValute>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemValuteBinding.bind(view)

        fun bind(item: ItemValute, context: Context) {
            binding.valuteName.text = item.charCode
            binding.valuteNow.text = String.format(context.getString(R.string.new_value), item.value)
            binding.valutePrevious.text = String.format(context.getString(R.string.old_value), item.previous)
            if (item.previous > item.value)
                binding.imageViewIncreaseDecrease.setBackgroundResource(R.drawable.ic_baseline_arrow_downward_24)
            else binding.imageViewIncreaseDecrease.setBackgroundResource(R.drawable.ic_baseline_arrow_upward_24)
            binding.difference.text = String.format(context.getString(R.string.format), abs(item.value - item.previous))
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_valute, parent, false)
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.bind(it, context)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
