package com.amir.englishgrammercheatsheet.presentation.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.englishgrammercheatsheet.MainActivity
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.data.model.ContentsModel
import com.amir.englishgrammercheatsheet.databinding.FragmentContentBinding
import com.amir.englishgrammercheatsheet.presentation.IOnBackPressed
import com.amir.englishgrammercheatsheet.presentation.grammer.BaseFragment

import com.amir.englishgrammercheatsheet.presentation.objects.ContentObject
import com.amir.englishgrammercheatsheet.presentation.objects.GrammerObject
import com.google.android.material.navigation.NavigationView


class ContentFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener{

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
        //toolbar
       // toolbarDisplayingSetUp(binding.toolbar,0, "Grammar")
        //display navigation
        navigationDrawableDisplayingSetUp(MainActivity(),binding.drawerLayoutContentFragment,binding.toolbar)
        //making navigation clickAble
        binding.navigationMenu.setNavigationItemSelectedListener(this)

    }

    private fun setupRecyclerView() {
        binding.rvGrammarContent.layoutManager = LinearLayoutManager(activity)
        binding.rvGrammarContent.adapter =
            AdapterContent(contentList) { selectedItem: ContentsModel ->
                listItemClicked(selectedItem)
            }
    }

    private fun listItemClicked(contentsModel: ContentsModel) {
//https://stackoverflow.com/questions/16036572/how-to-pass-values-between-fragments

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


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_nav_dashboard ->{
                binding.drawerLayoutContentFragment.closeDrawers()
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

