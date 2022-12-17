package com.example.challengechapter4_revisi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challengechapter4_revisi.data.dao.NoteData
import com.example.challengechapter4_revisi.databinding.FragmentAddNoteBinding
import com.example.challengechapter4_revisi.ui.viewmodel.NoteViewModel

class AddNoteFragment : DialogFragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences : SharedPreferences
    private val viewModel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)

        binding.btnAdd.setOnClickListener {
            binding.apply {
                val title = edtJudul.text.toString()
                val content = edtCatatan.text.toString()

                if (edtJudul.text!!.isEmpty()  || edtCatatan.text!!.isEmpty()){
                    Toast.makeText(context, "Anda belum mengisi note", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.addNote(NoteData(0, title, content))
                    Toast.makeText(context, "Note tersimpan", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}