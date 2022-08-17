package com.amir.englishgrammercheatsheet.presentation.content

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.englishgrammercheatsheet.MainActivity
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.data.model.ContentsModel
import com.amir.englishgrammercheatsheet.databinding.FragmentContentBinding
import com.amir.englishgrammercheatsheet.presentation.IOnBackPressed
import com.amir.englishgrammercheatsheet.presentation.MViewModel
import com.amir.englishgrammercheatsheet.presentation.grammer.GrammarFragment

import com.amir.englishgrammercheatsheet.presentation.objects.ContentObject
import com.amir.englishgrammercheatsheet.presentation.objects.GrammerObject


class ContentFragment : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentContentBinding
    private val contentList = ContentObject.contentsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvGrammarContent.layoutManager = LinearLayoutManager(activity)
        binding.rvGrammarContent.adapter =
            AdapterContent(contentList) { selectedItem: ContentsModel ->
                listItemClicked(selectedItem)
            }
    }

    private fun listItemClicked(contentsModel: ContentsModel) {
        // Toast.makeText(activity, "Select ${contentsModel.title}", Toast.LENGTH_SHORT).show()
//https://stackoverflow.com/questions/16036572/how-to-pass-values-between-fragments
//        val intent = Intent(activity?.baseContext,MainActivity::class.java)
//        intent.putExtra("msg","hi")
//        activity?.startActivity(intent)
        binding.tvGrammarTitle.visibility = View.VISIBLE
        binding.tvGrammarDescription.visibility = View.VISIBLE
        binding.rvGrammarContent.visibility = View.GONE

        when (contentsModel.id) {
            1 -> {
                binding.tvGrammarTitle.text = GrammerObject.grammar1.title
                binding.tvGrammarDescription.text = GrammerObject.grammar1.description
            }

            2 -> {
                binding.tvGrammarTitle.text = GrammerObject.grammar2.title
                binding.tvGrammarDescription.text = GrammerObject.grammar2.description
            }
            3 -> {
                binding.tvGrammarTitle.text = GrammerObject.grammar3.title
                binding.tvGrammarDescription.text = GrammerObject.grammar3.description
            }
            4 -> {
                binding.tvGrammarTitle.text = GrammerObject.grammar4.title
                binding.tvGrammarDescription.text = GrammerObject.grammar4.description
            }
            5 -> {
                binding.tvGrammarTitle.text = GrammerObject.grammar5.title
                binding.tvGrammarDescription.text = GrammerObject.grammar5.description
            }
            6 -> {

            }
            7 -> {

            }
            8 -> {

            }
            9 -> {

            }
            10 -> {

            }
            11 -> {

            }
        }

    }

    override fun onBackPressed(): Boolean {
        return if (binding.rvGrammarContent.visibility == View.GONE) {
            binding.tvGrammarTitle.visibility = View.GONE
            binding.tvGrammarDescription.visibility = View.GONE
            binding.rvGrammarContent.visibility = View.VISIBLE
            true
        } else {
            true
        }
    }


}

