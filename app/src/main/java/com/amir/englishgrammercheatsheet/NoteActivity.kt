package com.amir.englishgrammercheatsheet

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.amir.englishgrammercheatsheet.databinding.ActivityNoteBinding
import com.amir.englishgrammercheatsheet.presentation.note.NoteFragment
import com.amir.englishgrammercheatsheet.room.*

class NoteActivity : BaseActivity() {
    lateinit var binding: ActivityNoteBinding
    lateinit var dao: NoteDAO
    lateinit var repository: NoteRepository
    lateinit var factory: NoteViewModelFactory
    lateinit var noteViewModel: NoteViewModel
    private var idFromNoteFragment: String? = null
    var iconFromNoteFragment: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //implement viewModel
        implementViewModel()
        //implement toolbar
        toolbarDisplayingSetUp(binding.toolbar, "notepad")

        receiveSentDataFromNoteFragmentToUpdate()
    }

//    private fun displaySubscribersList() {
//        noteViewModel.getSavedNotes().observe(this, Observer {
//            Log.i("My Tag", it.toString() + "\n")
//            noteViewModel.inputTitle.value = binding.etTitle.text.toString()
//            noteViewModel.inputDescription.value = binding.etDescription.text.toString()
//        })
//    }

    private fun receiveSentDataFromNoteFragmentToUpdate() {
        // val idFromNoteFragment = intent.getStringExtra("id")
        idFromNoteFragment = intent.getStringExtra("id")
        val titleFromNoteFragment = intent.getStringExtra("title")
        val descriptionFromNoteFragment = intent.getStringExtra("description")
        iconFromNoteFragment = intent.getIntExtra("icon", R.drawable.ic_edit_24)

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
        //changing the icon of menu
        if (idFromNoteFragment == binding.tvId.text.toString()) {
            val save = menu.findItem(R.id.action_save)
            save.setIcon(R.drawable.ic_edit_24)
            val delete = menu.findItem(R.id.action_delete)
            delete.isVisible = true
        }
        return true
    }

    //making menu bar onClickable
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        val id = binding.tvId.text.toString()

        when (item.itemId) {
            R.id.action_save -> {
                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(this, "The Title or Note filed is empty.", Toast.LENGTH_LONG)
                        .show()
                } else {
                    if (idFromNoteFragment.isNullOrBlank() && id != idFromNoteFragment) {
                        noteViewModel.inputTitle.value = title
                        noteViewModel.inputDescription.value = description
                        noteViewModel.saveOrUpdate()
                        binding.etTitle.text!!.clear()
                        binding.etDescription.text!!.clear()
                        Toast.makeText(this, "$title is saved.", Toast.LENGTH_LONG).show()
                        //item.setIcon(R.drawable.ic_edit_24)
                    } else if (idFromNoteFragment.equals(id) && iconFromNoteFragment == R.drawable.ic_edit_24) {
                        val idToUpdate = Integer.parseInt(id)
                        noteViewModel.update(NoteEntity(idToUpdate, title, description))
                        Toast.makeText(this, "$title is updated.", Toast.LENGTH_LONG).show()
                    }
                }
            }
            R.id.action_delete -> {

                if (idFromNoteFragment.equals(id)) {
                    val idToDelete = Integer.parseInt(id)
                    Toast.makeText(this, "delete is clicked", Toast.LENGTH_LONG).show()

                    noteViewModel.delete(NoteEntity(idToDelete, title, description))
                    //onBackPressed()
                    var intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("delete", "DELETE")
                    startActivity(intent)
                } else if (idFromNoteFragment.isNullOrBlank() && id != idFromNoteFragment) {
                }
            }

            /* R.id.action_update -> {
                 val idToUpdate = Integer.parseInt(id)

                 Toast.makeText(this, "update is clicked", Toast.LENGTH_LONG).show()
                 Toast.makeText(this, "update $id is clicked", Toast.LENGTH_LONG).show()
                 noteViewModel.update(NoteEntity(idToUpdate, title, description))

                 /*  lifecycleScope.launch {
                    repository.update(NoteEntity(id  , title, description))
                 }*/
             }*/
        }
        return super.onOptionsItemSelected(item)
    }


}