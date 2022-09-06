package com.amir.englishgrammercheatsheet

import android.os.Bundle
import com.amir.englishgrammercheatsheet.NoteActivity.Companion.myKey

import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding
import com.amir.englishgrammercheatsheet.presentation.content.ContentFragment
import com.amir.englishgrammercheatsheet.presentation.note.NoteFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       loadContentFragment()
        loadNoteFragment()
    }

    private fun loadContentFragment() {
        binding.rbGrammar.setOnClickListener {
            loadFragment(ContentFragment())
            binding.rbNote.isChecked = false
        }
    }


    private fun loadNoteFragment() {
        binding.rbNote.setOnClickListener {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false
        }
    }

    override fun onStart() {
        loadFragment(ContentFragment())
        openTheNoteFragmentAfterDeleteANote()
        super.onStart()
    }

    override fun onBackPressed() {
      if( binding.rbGrammar.isChecked == true){
//            finishAffinity()
//            super.onBackPressed()
        }else if( binding.rbGrammar.isChecked == false){
          loadFragment(ContentFragment())
          binding.rbGrammar.isChecked = true
          binding.rbNote.isChecked = false

      }
    }
    private fun openTheNoteFragmentAfterDeleteANote() {
        val myKey = intent.getStringExtra(myKey)
        if (myKey.equals("MYKEY")) {
            loadFragment(NoteFragment())
            binding.rbGrammar.isChecked = false
            binding.rbNote.isChecked = true
        }
    }

}