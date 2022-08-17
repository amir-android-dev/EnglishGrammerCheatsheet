package com.amir.englishgrammercheatsheet.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.englishgrammercheatsheet.databinding.NoteRowBinding

class NoteAdapter(private val noteEntityList: List<NoteEntity>) :
    RecyclerView.Adapter<MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = NoteRowBinding.inflate(layoutInflater, parent, false)
        return MViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(noteEntityList[position])
    }

    override fun getItemCount(): Int {
        return noteEntityList.size
    }

}


class MViewHolder(private val binding: NoteRowBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(noteEntity: NoteEntity) {
        binding.tvTitle.text = noteEntity.title
        binding.tvDescription.text = noteEntity.description
    }
}