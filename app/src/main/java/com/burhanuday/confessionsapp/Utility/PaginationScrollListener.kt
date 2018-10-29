package com.burhanuday.confessionsapp.Utility

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by burhanuday on 29-10-2018.
 */
abstract class PaginationScrollListener(linearLayoutManager: LinearLayoutManager): RecyclerView.OnScrollListener() {

    val layoutManager = linearLayoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    public abstract fun getTotalPageCount(): Int
    public abstract fun isLastPage(): Boolean
    public abstract fun isLoading(): Boolean

}