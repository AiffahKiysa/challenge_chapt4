package com.example.challengechapter4_revisi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapter4_revisi.dao.NoteData
import com.example.challengechapter4_revisi.databinding.NoteListBinding

class NoteAdapter(private var onClick : NoteAdapter.NotesInterface) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var diffCallback = object : DiffUtil.ItemCallback<NoteData>(){

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id_notes == newItem.id_notes
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder (private val binding: NoteListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(notes: NoteData){
            binding.apply {
                dataNote = notes

                btnEdit.setOnClickListener {
                    onClick.editNote(notes)
                }

                btnDelete.setOnClickListener {
                    onClick.deleteNote(notes)
                }
            }
        }
    }

    interface NotesInterface {
        fun editNote(notes: NoteData)
        fun deleteNote(notes: NoteData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun setData(data : List<NoteData>){
        differ.submitList(data)
    }
}