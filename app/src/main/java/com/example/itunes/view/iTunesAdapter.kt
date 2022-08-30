package com.example.itunes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes.databinding.ItunesLayoutBinding
import com.example.itunes.model.datamodel.ItunesModel
import com.example.itunes.viewmodel.MainViewModel

class ItunesAdapter (private var itunes: Array<ItunesModel>, private val viewModel: MainViewModel, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ItunesViewHolder(_binding: ItunesLayoutBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val binding: ItunesLayoutBinding = _binding
        fun bind(itunes: ItunesModel) {
            binding.itunesTitle = itunes
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItunesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItunesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItunesViewHolder ->  {
                holder.bind(itunes[position])
            }
        }
        holder.itemView.setOnClickListener{ viewModel.itunesClicked(itunes[position], context) }
    }

    override fun getItemCount(): Int {
        return itunes.size
    }

    fun updateData(itunes: Array<ItunesModel>) {
        this.itunes = itunes
        notifyDataSetChanged()
    }
}