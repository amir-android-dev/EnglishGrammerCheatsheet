package com.amir.englishgrammercheatsheet.presentation.grammer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.databinding.FragmentGrammarBinding
import com.google.android.material.navigation.NavigationView
import java.util.zip.Inflater


open class BaseFragment : Fragment() {


    //setup navigation
    fun navigationDrawableDisplayingSetUp(
        activity: AppCompatActivity,
        drawerLayout: DrawerLayout,
        toolbar: androidx.appcompat.widget.Toolbar
    ) {
        val toggle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.open, R.string.close)
        return toggle.syncState()
    }


    //setup toolbar
    fun toolbarDisplayingSetUp(toolbar: androidx.appcompat.widget.Toolbar, menuId: Int, title:String) {
        toolbar.inflateMenu(menuId)
        toolbar.title = title
    }


    //sharing app
    fun sharingApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, "Hey! Download the best English Grammar Cheatsheet.\n " +
                        "https://play.google.com/store/apps/details?id=com.amir.deutscheGrammatikCheatsheet"
            )
            type = "text/plain"
        }
        startActivity(sendIntent)
    }



}


