package com.amir.englishgrammercheatsheet.presentation.grammer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.databinding.FragmentGrammarBinding
import java.util.zip.Inflater


class GrammarFragment : Fragment() {

private lateinit var binding: FragmentGrammarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentGrammarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}