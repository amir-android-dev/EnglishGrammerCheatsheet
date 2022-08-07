package com.amir.englishgrammercheatsheet

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.amir.englishgrammercheatsheet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationDrawableSetUp(this, binding.drawerLayout, binding.toolbar)
    }

//    private fun navigationDrawableSetUp() {
//        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.open,
//            R.string.close
//        )
//        toggle.syncState()
//    }
    
}