package com.giphy.giphy

import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.giphy.giphy.dal.Dal
import com.giphy.giphy.network.models.GifData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val LIMIT = 10
        val TAG = "MainActivity"
        val SCREEN_WIDTH = App.get().resources.displayMetrics.widthPixels
    }

    private var adapter: BaseQuickAdapter<GifData, BaseViewHolder>? = null

    private var offset: Int = 0
    private var queryCount: Int = 0
    private var query: String = "cat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            setDrawable(getDrawable(android.R.drawable.divider_horizontal_bright))
        })

        initAdapter()
        recycler.adapter = adapter

        swipeRefresh.setOnRefreshListener {
            offset = 0
            queryCount++
            query = when (queryCount % 5) {
                0 -> "cat"
                1 -> "dog"
                2 -> "mouse"
                3 -> "horse"
                else -> "donkey"
            }

            loadGifs()
        }

        loadGifs()
    }

    private fun initAdapter() {
        adapter = object : BaseQuickAdapter<GifData, BaseViewHolder>(R.layout.gif_item_layout) {
            override fun convert(helper: BaseViewHolder?, item: GifData?) {
                val imgView = helper?.getView<ImageView>(R.id.img)!!
                val imgData = item?.images?.fixedWidth!!
                val relativeHeight = ((imgData.height!!.toDouble() / imgData.width!!.toDouble()) * SCREEN_WIDTH.toDouble()).toInt()
                imgView.layoutParams = ConstraintLayout.LayoutParams(SCREEN_WIDTH, relativeHeight)
                imgView.setBackgroundColor(when (helper.adapterPosition % 4) {
                    0 -> Color.BLUE
                    1 -> Color.RED
                    2 -> Color.GREEN
                    else -> Color.YELLOW
                })
                Glide.with(this@MainActivity)
                        .asGif()
                        .apply(RequestOptions()
                                .error(R.drawable.ic_broken_image_black_24dp))
                        .load(imgData.url)
                        .into(imgView)
            }
        }

        adapter?.setEnableLoadMore(true)
        adapter?.setPreLoadNumber(3)
        adapter?.setOnLoadMoreListener(
                {
                    offset += LIMIT
                    loadGifs()
                }, recycler)
    }

    private fun loadGifs() {
        Dal.searchGifs(query, offset, LIMIT).done {
            if (offset == 0) {
                adapter?.setNewData(ArrayList(it))
            } else {
                adapter?.addData(ArrayList(it))
            }
            adapter?.loadMoreComplete()
            swipeRefresh.isRefreshing = false
        }.fail {
            Log.e(TAG, "failed to load gifs", it)
            adapter?.loadMoreFail()
            swipeRefresh.isRefreshing = false
        }
    }
}