package com.burhanuday.confessionsapp.Utility

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.burhanuday.confessionsapp.Models.Post
import com.burhanuday.confessionsapp.R
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by burhanuday on 29-10-2018.
 */
class RecyclerPaginatedAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded: Boolean = false
    private var confessions: MutableList<Post> = ArrayList()
    private val ITEM = 0
    private val LOADING = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        when(viewType){
            ITEM -> {viewHolder = getViewHolder(parent, inflater)}
            LOADING -> {
                val v2: View = inflater.inflate(R.layout.item_progress, parent, false)
                viewHolder = LoadingVH(v2)
            }
        }

        return viewHolder!!
    }

    @NonNull
    fun getViewHolder(parent: ViewGroup, inflater: LayoutInflater):RecyclerView.ViewHolder{
        val viewHolder: RecyclerView.ViewHolder
        val view1 = inflater.inflate(R.layout.row_post, parent, false)
        viewHolder = PostVH(view1)
        return viewHolder
    }

    override fun getItemCount(): Int {
        //return if (confessions == null) 0 else confessions.size
        return confessions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = confessions[position]
        when(position){
            ITEM -> {
                val postVH: PostVH = holder as PostVH
                postVH.textView.text = post.body
            }

            LOADING-> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == confessions.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun getPosts(): List<Post>{
        return confessions
    }

    fun setPosts(posts: List<Post>){
        this.confessions = confessions
    }

    fun add(post: Post){
        confessions.add(post)
        notifyItemInserted(confessions.size-1)
    }

    fun addAll(posts: List<Post>){
        for (post in posts){
            add(post)
        }
    }

    fun remove(post: Post){
        val position = confessions.indexOf(post)
        if (position> -1){
            confessions.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear(){
        isLoadingAdded = false
        while (itemCount > 0){
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean{
        return itemCount == 0
    }

    fun addLoadingFooter(){
        isLoadingAdded = true
        add(Post())
    }

    fun removeLoadingFooter(){
        isLoadingAdded = false
        val position = confessions.size - 1
        val item: Post? = getItem(position)
        if (item!=null){
            confessions.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): Post{
        return confessions[position]
    }

    protected class PostVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_body)
    }

    protected class LoadingVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    }


}