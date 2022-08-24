package com.amir.englishgrammercheatsheet

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amir.englishgrammercheatsheet.databinding.ActivityNoteBinding
import com.amir.englishgrammercheatsheet.room.*
class NoteActivity : BaseActivity() {
    lateinit var binding: ActivityNoteBinding
    lateinit var dao: NoteDAO
    lateinit var repository: NoteRepository
    lateinit var factory: NoteViewModelFactory
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implement viewModel
        implementViewModel()
        //implement toolbar
        toolbarDisplayingSetUp(binding.toolbar, "notepad")
        //show the list oft notes
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        noteViewModel.getSavedNotes().observe(this, Observer {
            Log.i("My Tag", it.toString() + "\n")
            receiveSentDataFromNoteFragmentToUpdate()
        })
    }

    private fun receiveSentDataFromNoteFragmentToUpdate(): Unit {

        val titleFromNoteFragment = intent.getStringExtra("title")
        val descriptionFromNoteFragment = intent.getStringExtra("description")
        binding.etTitle.setText(titleFromNoteFragment)
        binding.etDescription.setText(descriptionFromNoteFragment)
        noteViewModel.saveOrUpdateButtonText.value = "Update"
        // viewModel.initUpdate()
    }

    private fun implementViewModel() {
        // start implement viewModel
        dao = NoteDatabase.getInstance(application).noteDAO
         repository = NoteRepository(dao)
         factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        //end of viewModel Implement
    }

    // display menu bar
    //without implementing toolbar will not work
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bar_note_activity, menu)
        return true
    }

    //making menu bar onClickable
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "save is clicked", Toast.LENGTH_LONG).show()
                noteViewModel.inputTitle.value = binding.etTitle.text.toString()
                noteViewModel.inputDescription.value = binding.etDescription.text.toString()
                noteViewModel.saveOrUpdate()
            }
            R.id.action_delete -> {
                Toast.makeText(this, "delete is clicked", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }



}