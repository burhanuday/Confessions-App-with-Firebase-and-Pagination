package com.burhanuday.confessionsapp.Models

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by burhanuday on 28-10-2018.
 */
class Post() {
    var time:String?=null
    var body:String? = null
    var upvotes:Int = 0
    var replyCount:Int = 0

    constructor(time:String, body:String, upvotes:Int, replies:Int):this(){
        this.time = time
        this.body = body
        this.upvotes = upvotes
        this.replyCount = replies
    }
}