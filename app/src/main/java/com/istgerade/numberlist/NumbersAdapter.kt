package com.istgerade.numberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.istgerade.data.entity.NumberResponse
import com.istgerade.databinding.NumberListItemBinding

class NumbersAdapter(
    private val onClickListener: NumberClickListener,
    private val onLongClick: NumberLongClickListener
) :
    RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>() {

    private val numbersList = mutableListOf<NumberResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        return NumbersViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        val num = numbersList[position]
        holder.bind(num, onClickListener, onLongClick)
        holder.itemView.setOnClickListener { num.ad?.let { onClickListener.onClick(it) } }

    }

    override fun getItemCount(): Int {
        return numbersList.size
    }

    fun addList(number: NumberResponse) {
        numbersList.add(number)
        notifyDataSetChanged()
    }

    fun removeItem(number: NumberResponse) {
        val index = numbersList.indexOf(number)
        numbersList.removeAt(index)
        notifyItemRemoved(index)
    }

    class NumbersViewHolder private constructor(val binding: NumberListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            numberResponse: NumberResponse,
            onClickListener: NumberClickListener,
            onLongClick: NumberLongClickListener
        ) {
            binding.number = numberResponse
            binding.numberLayout.setOnClickListener {
                onClickListener.onClick(numberResponse.ad)
            }
            binding.numberLayout.setOnLongClickListener {
                onLongClick.onLongClick(numberResponse)
                true
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NumbersViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NumberListItemBinding.inflate(layoutInflater, parent, false)
                return NumbersViewHolder(binding)
            }
        }
    }
}


class NumberClickListener(val onClick: (str: String?) -> Unit)
class NumberLongClickListener(val onLongClick: (numberResponse: NumberResponse) -> Unit)
