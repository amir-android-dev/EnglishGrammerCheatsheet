package com.amir.englishgrammercheatsheet

import android.os.Bundle
import androidx.fragment.app.FragmentManager

import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding
import com.amir.englishgrammercheatsheet.presentation.grammar.GrammarFragment
import com.amir.englishgrammercheatsheet.presentation.note.NoteFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarDisplayingSetUp(binding.toolbar,"Grammar Cheatsheet")
        navigationDrawableDisplayingSetUp(this, binding.drawerLayout, binding.toolbar)

        binding.rbGrammar.setOnClickListener {
          loadFragment(GrammarFragment())

            binding.rbNote.isChecked = false



        }
        binding.rbNote.setOnClickListener {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false

        }

    }

    override fun onStart() {
        loadFragment(GrammarFragment())
        super.onStart()
    }

}