package com.burhanuday.confessionsapp.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burhanuday.confessionsapp.Models.Post
import com.burhanuday.confessionsapp.R
import com.burhanuday.confessionsapp.Utility.FirebaseHelper
import kotlinx.android.synthetic.main.activity_post_confession.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PostConfession : AppCompatActivity() {

    lateinit var firebaseHelper: FirebaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_confession)

        firebaseHelper = FirebaseHelper()

        bt_post_confession.setOnClickListener{
            if (et_post.text.trim().isNotEmpty()){
                val calendar: Calendar = Calendar.getInstance()
                val date: Date = calendar.time
                val dateFormat: DateFormat = SimpleDateFormat("HH:mm:ss")
                val post: Post = Post(dateFormat.format(date), et_post.text.toString(), 0, 0)
                firebaseHelper.submitPost(post, baseContext)
            }else{
                Toast.makeText(baseContext, "Cannot post empty text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
