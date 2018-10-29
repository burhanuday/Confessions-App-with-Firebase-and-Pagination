package com.burhanuday.confessionsapp.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burhanuday.confessionsapp.Models.Post
import com.burhanuday.confessionsapp.R
import com.burhanuday.confessionsapp.Utility.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_post_confession.*

class PostConfession : AppCompatActivity() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val cr: CollectionReference = db.collection(Constants.collection).document(Constants.posts).collection(Constants.posts)

    fun submitPost(post: Post){
        cr.add(post)
            .addOnSuccessListener {
                Toast.makeText(this, "Submitted successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_confession)

        bt_post_confession.setOnClickListener{
            if (et_post.text.trim().isNotEmpty()){
                val post = Post(Constants.getTime(), et_post.text.toString(), 0, 0)
                submitPost(post)
            }else{
                Toast.makeText(this, "Cannot post empty text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
