package com.amir.englishgrammercheatsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amir.englishgrammercheatsheet.databinding.ActivityNoteBinding
import com.amir.englishgrammercheatsheet.room.NoteDatabase
import com.amir.englishgrammercheatsheet.room.NoteRepository
import com.amir.englishgrammercheatsheet.room.NoteViewModel
import com.amir.englishgrammercheatsheet.room.NoteViewModelFactory

class NoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoteBinding
     lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityNoteBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        val dao = NoteDatabase.getInstance(application).noteDAO
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        binding.mViewModel = viewModel
        binding.lifecycleOwner = this

        binding.tvCancel.setOnClickListener {
            onBackPressed()
        }
        displaySubscribersList()

    }

    private fun displaySubscribersList() {
        viewModel.getSavedNotes().observe(this, Observer {
            Log.i("My Tag", it.toString() + "\n")
        })
    }

}