package com.example.cardbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardbank.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main_activity, MainFragment.newInstance())
                .commitNow()
        }
    }
}