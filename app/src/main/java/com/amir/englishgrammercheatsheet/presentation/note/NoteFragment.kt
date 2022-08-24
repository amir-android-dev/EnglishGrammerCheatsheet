package com.amir.englishgrammercheatsheet.presentation.note

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.amir.englishgrammercheatsheet.NoteActivity
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.databinding.FragmentNoteBinding
import com.amir.englishgrammercheatsheet.presentation.grammer.BaseFragment
import com.amir.englishgrammercheatsheet.room.*
import com.amir.englishgrammercheatsheet.swiping.SwipeToEditCallback

class NoteFragment : BaseFragment(), Toolbar.OnMenuItemClickListener {
    lateinit var binding: FragmentNoteBinding
    lateinit var dao: NoteDAO
    lateinit var repository: NoteRepository
    lateinit var factory: NoteViewModelFactory
    lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.toolbarNote.inflateMenu(R.menu.bar_note_fragment)
        toolbarDisplayingSetUpWithMenu(binding.toolbarNote, R.menu.bar_note_fragment, "")
        binding.toolbarNote.setOnMenuItemClickListener(this)
        implementViewModel()
        floatButtonAction()
        setUpRecyclerView()
    }

    private fun floatButtonAction() {
        binding.flBtn.setOnClickListener {
            val intent = Intent(activity, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displaySubscribersList() {
//        val dao = NoteDatabase.getInstance(requireActivity().application).noteDAO
//        val repository = NoteRepository(dao)
//        val factory = NoteViewModelFactory(repository)
//        val model = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)
        viewModel.getSavedNotes().observe(viewLifecycleOwner, Observer {
            Log.i("My Tag", it.toString() + "\n")
            binding.rvNote.adapter = NoteAdapter(it)
            swipeToUpdate(it)

        })
    }

    private fun implementViewModel() {
        dao = NoteDatabase.getInstance(requireActivity().application).noteDAO
        repository = NoteRepository(dao)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        //  binding.rvNote.layoutManager = LinearLayoutManager(activity)
        // binding.rvNote.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvNote.layoutManager = GridLayoutManager(activity, 2)
        displaySubscribersList()
    }

    private fun swipeToUpdate(noteEntity: List<NoteEntity>) {
        val swipeToEditCallback = object : SwipeToEditCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //sending data to NoteActivity
                val intent = Intent(requireActivity(), NoteActivity::class.java)
                intent.putExtra("title", noteEntity[viewHolder.adapterPosition].title)
                intent.putExtra("description", noteEntity[viewHolder.adapterPosition].description)
                startActivity(intent)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToEditCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_deleteAll -> {
                val builder = AlertDialog.Builder(requireActivity())
                builder.setTitle("Delete All")
                builder.setIcon(R.drawable.ic_dangerous_24)
                builder.setPositiveButton("Yes") { dialogInterface, _ ->
                    viewModel.clearAll()
                    dialogInterface.dismiss()
                }
                builder.setNegativeButton("No") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                val alertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()


            }
        }
        return false
    }

}



