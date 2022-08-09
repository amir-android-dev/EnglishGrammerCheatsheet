package com.amir.englishgrammercheatsheet.presentation.grammar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.databinding.FragmentGrammarBinding
import com.amir.englishgrammercheatsheet.presentation.objects.ContentObject


class GrammarFragment : Fragment() {

    private lateinit var binding: FragmentGrammarBinding
    private val contentList = ContentObject.contentsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGrammarBinding.inflate(layoutInflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvGrammarContent.layoutManager = LinearLayoutManager(activity)
        binding.rvGrammarContent.adapter = AdapterContent(contentList)
    }


}