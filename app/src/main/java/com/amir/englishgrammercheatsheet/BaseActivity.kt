package com.amir.englishgrammercheatsheet


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment


open class BaseActivity : AppCompatActivity() {
    //navigation display
    fun navigationDrawableDisplayingSetUp(
        activity: AppCompatActivity,
        drawerLayout: DrawerLayout,
        toolbar: androidx.appcompat.widget.Toolbar
    ) {

        val toggle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.open, R.string.close)
        return toggle.syncState()
    }

    //toolbar display
    fun toolbarDisplayingSetUp(toolbar: androidx.appcompat.widget.Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
    }

    //loadFragment
    fun loadFragment(fragment: Fragment) {

      //  val grammarFragment = GrammerFragment()
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        //transaction.add(R.id.fr_layout, fragment)
        transaction.replace(R.id.fr_layout, fragment)
        transaction.commit()

    }

    //GrammarFragment as first open fragment


}