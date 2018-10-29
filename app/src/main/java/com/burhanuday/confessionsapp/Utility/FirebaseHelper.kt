package com.burhanuday.confessionsapp.Utility

import android.content.Context
import android.widget.Toast
import com.burhanuday.confessionsapp.Models.Post
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by burhanuday on 28-10-2018.
 */
class FirebaseHelper {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun submitPost(post: Post, context: Context){
        val cr:CollectionReference = db.collection(Constants.collection).document(Constants.posts).collection(Constants.posts)
        cr.add(post)
            .addOnSuccessListener {
                Toast.makeText(context, "Submitted successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
    }

    fun getPosts(){

    }

}