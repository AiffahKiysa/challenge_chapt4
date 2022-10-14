package com.example.challengechapter4_revisi.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challange4.room.NoteViewModel
import com.example.challengechapter4_revisi.R
import com.example.challengechapter4_revisi.dao.NoteData
import com.example.challengechapter4_revisi.dao.NoteDatabase
import com.example.challengechapter4_revisi.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NoteAdapter.NotesInterface {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : NoteViewModel by viewModels()
    lateinit var  sharedPreferences : SharedPreferences
    private lateinit var builder : AlertDialog.Builder
    private lateinit var adapter : NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    companion object{
        const val USERNAME = "username"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        builder = AlertDialog.Builder(context)

        adapter = NoteAdapter(this)
        val user = sharedPreferences.getString(USERNAME, "")
        binding.txtWelcome.text = "Welcome, $user!"

        dataEmpty()

        binding.apply {
            binding.btnAddNote.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
            }

            binding.logout.setOnClickListener {
                logout()
            }
        }
    }

    private fun dataEmpty() {
        binding.apply {
            viewModel.getDataNotes().observe(viewLifecycleOwner) {
                adapter.setData(it)
                if (it.isEmpty()){
                    tvAlertKosong.visibility = View.VISIBLE
                }
                else
                    tvAlertKosong.visibility = View.GONE
                }
            rvNote.adapter = adapter
            rvNote.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun editNote(notes: NoteData) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(notes))
    }

    override fun deleteNote(notes: NoteData) {
        builder.setTitle("Warning!")
            .setMessage("Ingin menghapus note ini?")
            .setCancelable(true)
            .setPositiveButton("Ya"){ _, _ ->
                viewModel.deleteNote(notes)
            }
            .setNegativeButton("Tidak"){
                    dialog, _ -> dialog.dismiss()
            }
            .show()
    }

    private fun logout() {
        var pref = sharedPreferences.edit()
        pref.clear()
        pref.apply()

        Toast.makeText(context, "Berhasil logout", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}