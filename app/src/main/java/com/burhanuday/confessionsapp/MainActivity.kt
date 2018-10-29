package com.burhanuday.confessionsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burhanuday.confessionsapp.Activities.PostConfession
import com.burhanuday.confessionsapp.Utility.PaginationScrollListener
import com.burhanuday.confessionsapp.Utility.RecyclerPaginatedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG:String = "MainActivity"
    private val PAGE_START = 0
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var TOTAL_PAGES = 3
    private var currentPage = PAGE_START
    private lateinit var adapter: RecyclerPaginatedAdapter

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

        adapter = RecyclerPaginatedAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_posts.layoutManager = linearLayoutManager
        rv_posts.itemAnimator = DefaultItemAnimator()
        rv_posts.adapter = adapter

        rv_posts.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override fun getTotalPageCount(): Int {
                return TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })

        loadFirstPage()
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

    private fun loadFirstPage(){

        main_progress.visibility = View.GONE
        //adapter.addAll()
        if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter()
        else isLastPage = true
    }

    private fun loadNextPage(){
        adapter.removeLoadingFooter()
        isLoading = false
        //adapter.addAll()
        if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter()
        else isLastPage = true
    }
}
