package com.example.empty.kotlinmap

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloWordText.setText("Witaj")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.mapFrame, GoogleMapFragment.newInstance("test", 12.0,12.0)).commit()
        }
    }
}
