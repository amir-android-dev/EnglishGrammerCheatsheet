package com.amir.englishgrammercheatsheet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout

open class BaseActivity : AppCompatActivity() {

    fun navigationDrawableSetUp(
        activity: AppCompatActivity,
        drawerLayout: DrawerLayout,
        toolbar: androidx.appcompat.widget.Toolbar
    ) {

        val toggle: ActionBarDrawerToggle =
            ActionBarDrawerToggle( activity, drawerLayout, toolbar, R.string.open, R.string.close)
        return toggle.syncState()
    }


}