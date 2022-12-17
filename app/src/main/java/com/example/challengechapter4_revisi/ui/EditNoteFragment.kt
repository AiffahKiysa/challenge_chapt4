package com.example.challengechapter4_revisi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challengechapter4_revisi.R
import com.example.challengechapter4_revisi.databinding.FragmentEditNoteBinding
import com.example.challengechapter4_revisi.data.dao.NoteData
import com.example.challengechapter4_revisi.ui.viewmodel.NoteViewModel

class EditNoteFragment : DialogFragment() {
    private var _binding : FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val args : EditNoteFragmentArgs by navArgs()
    private val viewModel : NoteViewModel by viewModels()
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getData = args.dataNotes
        sharedPreferences = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)

        binding.apply {
            dataEdit = getData
            btnEdit.setOnClickListener {
                val title = binding.edtJudul.text.toString()
                val content = binding.edtCatatan.text.toString()

                if (edtJudul.text!!.isEmpty() || edtCatatan.text!!.isEmpty()){
                    Toast.makeText(context, "Isi Note Dahulu", Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.editNote(NoteData(args.dataNotes.id_notes, title, content))
                    Toast.makeText(context, "Edit Note tersimpan", Toast.LENGTH_SHORT).show()
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