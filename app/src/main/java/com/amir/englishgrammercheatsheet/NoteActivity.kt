package com.amir.englishgrammercheatsheet

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.amir.englishgrammercheatsheet.databinding.ActivityNoteBinding
import com.amir.englishgrammercheatsheet.room.*
import kotlinx.coroutines.launch

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
       // displaySubscribersList()

        receiveSentDataFromNoteFragmentToUpdate()


    }

//    private fun displaySubscribersList() {
//        noteViewModel.getSavedNotes().observe(this, Observer {
//            Log.i("My Tag", it.toString() + "\n")
//            noteViewModel.inputTitle.value = binding.etTitle.text.toString()
//            noteViewModel.inputDescription.value = binding.etDescription.text.toString()
//        })
//    }

    private fun receiveSentDataFromNoteFragmentToUpdate(): Unit {
        val idFromNoteFragment = intent.getStringExtra("id")
        val titleFromNoteFragment = intent.getStringExtra("title")
        val descriptionFromNoteFragment = intent.getStringExtra("description")
        binding.tvId.setText(idFromNoteFragment)
        binding.etTitle.setText(titleFromNoteFragment)
        binding.etDescription.setText(descriptionFromNoteFragment)


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
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val id  = binding.tvId.text.toString()

        when (item.itemId) {
            R.id.action_save -> {
                Toast.makeText(this, "save is clicked", Toast.LENGTH_LONG).show()
                if(title.isEmpty() || description.isEmpty()){
                    Toast.makeText(this, "The Title or Note filed is empty.", Toast.LENGTH_LONG).show()
                }
                else {
                    noteViewModel.inputTitle.value = title
                    noteViewModel.inputDescription.value = description
                    noteViewModel.saveOrUpdate()
                }
            }
            R.id.action_delete -> {
              val idToDelete  = Integer.parseInt(id)

                Toast.makeText(this, "delete is clicked", Toast.LENGTH_LONG).show()

                noteViewModel.delete(NoteEntity(idToDelete, title, description))
                onBackPressed()
            }

            R.id.action_update -> {
                val idToUpdate  = Integer.parseInt(id)

                Toast.makeText(this, "update is clicked", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "update $id is clicked", Toast.LENGTH_LONG).show()
               noteViewModel.update(NoteEntity(idToUpdate, title, description))
//                lifecycleScope.launch {
//                   repository.update(NoteEntity(id  , title, description))
//               }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}