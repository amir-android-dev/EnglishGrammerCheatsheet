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

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//mViewModel
      //  noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
//toolbar
        toolbarDisplayingSetUp(binding.toolbar, "Grammar Cheatsheet")
        //navigation drawable
        navigationDrawableDisplayingSetUp(this, binding.drawerLayout, binding.toolbar)
        binding.navigationMenu.setNavigationItemSelectedListener(this)

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
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.ll_fragment_content_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_nav_dashboard ->{
                binding.drawerLayout.closeDrawers()
            }
            R.id.item_nav_developer->{

            }
            R.id.item_nav_note ->{

            }
            R.id.item_nav_rate->{

            }

            R.id.item_nav_feedback->{

            }
            R.id.item_nav_share->{
                sharingApp()
            }
            R.id.item_privacy_policy->{

            }
        }

        return true
    }


}