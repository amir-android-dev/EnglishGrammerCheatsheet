package com.amir.englishgrammercheatsheet

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider

import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding
import com.amir.englishgrammercheatsheet.presentation.IOnBackPressed
import com.amir.englishgrammercheatsheet.presentation.MViewModel
import com.amir.englishgrammercheatsheet.presentation.content.ContentFragment
import com.amir.englishgrammercheatsheet.presentation.note.NoteFragment
import com.amir.englishgrammercheatsheet.room.NoteViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        openTheNoteFragmentAfterDeleteTheNote()
        super.onStart()
    }

    override fun onBackPressed() {
//        val fragment =
//            this.supportFragmentManager.findFragmentById(R.id.ll_fragment_content_container)
//        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
//            super.onBackPressed()
//        }

    }

    fun openTheNoteFragmentAfterDeleteTheNote() {
        val delete = intent.getStringExtra("delete")
        if (delete.equals("DELETE")) {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false
            binding.rbNote.isChecked = true
        }
    }

}