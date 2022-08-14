package com.amir.englishgrammercheatsheet

import android.os.Bundle
import android.widget.Toast

import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding
import com.amir.englishgrammercheatsheet.presentation.IOnBackPressed
import com.amir.englishgrammercheatsheet.presentation.content.ContentFragment
import com.amir.englishgrammercheatsheet.presentation.grammer.GrammarFragment
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
          loadFragment(ContentFragment())
            binding.rbNote.isChecked = false

        }
        binding.rbNote.setOnClickListener {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false

        }

    }

    override fun onStart() {
        loadFragment(ContentFragment())
        super.onStart()
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.ll_fragment_content_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

}