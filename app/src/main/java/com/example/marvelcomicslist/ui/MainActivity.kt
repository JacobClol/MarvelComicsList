package com.example.marvelcomicslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelcomicslist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MarvelComicsList_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}