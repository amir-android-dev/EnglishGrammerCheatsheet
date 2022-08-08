package com.amir.englishgrammercheatsheet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarDisplayingSetUp(binding.toolbar,"Grammar Cheatsheet")
        navigationDrawableDisplayingSetUp(this, binding.drawerLayout, binding.toolbar)

        binding.rbGrammar.setOnClickListener {
          loadFragment(GrammerFragment())
            binding.rbNote.isChecked = false



        }
        binding.rbNote.setOnClickListener {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false


        }

    }



}