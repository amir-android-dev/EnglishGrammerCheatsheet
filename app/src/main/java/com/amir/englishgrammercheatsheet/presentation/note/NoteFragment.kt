package com.amir.englishgrammercheatsheet.presentation.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.amir.englishgrammercheatsheet.NoteActivity
import com.amir.englishgrammercheatsheet.databinding.FragmentNoteBinding
import com.amir.englishgrammercheatsheet.room.*
import com.amir.englishgrammercheatsheet.swiping.SwipeToEditCallback

class NoteFragment : Fragment() {
    lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatButtonAction()
        setUpRecyclerView()
    }

    fun floatButtonAction() {
        binding.flBtn.setOnClickListener {
            val intent = Intent(activity, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displaySubscribersList() {
        val dao = NoteDatabase.getInstance(requireActivity().application).noteDAO
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        val model = ViewModelProvider(requireActivity(),factory).get(NoteViewModel::class.java)
        model.getSavedNotes().observe(viewLifecycleOwner, Observer {
            Log.i("My Tag", it.toString() + "\n")
            binding.rvNote.adapter = NoteAdapter(it)
            swipeToUpdate(it)
        })
    }
    fun setUpRecyclerView() {
   //  binding.rvNote.layoutManager = LinearLayoutManager(activity)
    // binding.rvNote.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.rvNote.layoutManager = GridLayoutManager(activity,2)
        displaySubscribersList()
    }

    fun swipeToUpdate(noteEntity: List<NoteEntity>){
        val swipeToEditCallback = object : SwipeToEditCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //sending data to NoteActivity
                val intent = Intent(requireActivity(),NoteActivity::class.java)
                intent.putExtra("title",noteEntity[viewHolder.adapterPosition].title)
                intent.putExtra("description",noteEntity[viewHolder.adapterPosition].description)
                startActivity(intent)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToEditCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

}