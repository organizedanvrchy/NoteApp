package com.example.noteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.example.noteapp.MainActivity
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.databinding.FragmentNewNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    private var _binding  : FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel
        mView = view

    }

    private fun saveNote(view: View) {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        if(noteTitle.isNotEmpty()) {
            val note = Note(
                0,
                noteTitle,
                noteBody
            )

            notesViewModel.addNote(note)

            Toast.makeText(
                mView.context,
                "Note Saved Successfully",
                Toast.LENGTH_LONG
            ).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        } else {
            Toast.makeText(
                mView.context,
                "Please Enter Note Title",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_save -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
