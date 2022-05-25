package com.example.innofiedtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.innofiedtest.databinding.AccountRowBinding
import com.example.innofiedtest.models.Data

class UserAdapter :
    RecyclerView.Adapter<UserAdapter.MainUserViewHolder>() {

    private var dataUser = ArrayList<Data>()

    private lateinit var binding: AccountRowBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainUserViewHolder {
        binding = AccountRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainUserViewHolder, position: Int) {
        val dataUser = dataUser[position]
        holder.bind(dataUser)
    }

    override fun getItemCount(): Int = dataUser.size

    fun updateList(dataUser: ArrayList<Data>) {
        val oldSize = this.dataUser.size
        this.dataUser.addAll(dataUser)
        notifyItemRangeInserted(oldSize, dataUser.size)
    }


    inner class MainUserViewHolder(private val binding: AccountRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.dataClass = data
        }

    }
}


