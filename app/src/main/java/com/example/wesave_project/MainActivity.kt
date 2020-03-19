package com.example.wesave_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment_RecyclerView = Recycler_view()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout, fragment_RecyclerView,"fragment_RecyclerView")
        transaction.addToBackStack("fragment_list_view")
        transaction.commit()
    }
}
