package com.burhanuday.confessionsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burhanuday.confessionsapp.Activities.PostConfession
import com.burhanuday.confessionsapp.Utility.PaginationScrollListener
import com.burhanuday.confessionsapp.Utility.RecyclerPaginatedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG:String = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_app_bar)

        fab.setOnClickListener{
            //handle fab button click
            //open screen to post new confession
            val newPost: Intent = Intent(baseContext, PostConfession::class.java)
            startActivity(newPost)
        }
    }

    override fun onResume() {
        //todo refresh posts when activity resumes
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.app_bar_profile -> {Toast.makeText(baseContext, "Profile clicked", Toast.LENGTH_SHORT).show()}
        }
        return true
    }
}
