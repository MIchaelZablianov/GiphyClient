package com.giphy.giphy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.giphy.giphy.dal.Dal
import com.giphy.giphy.network.models.GifData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val LIMIT = 15
        val TAG = "MainActivity"
    }

    private var adapter: BaseQuickAdapter<GifData, BaseViewHolder>? = null

    private var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL).apply {
            setDrawable(getDrawable(android.R.drawable.divider_horizontal_bright))
        })

        adapter = object : BaseQuickAdapter<GifData, BaseViewHolder>(R.layout.gif_item_layout) {
            override fun convert(helper: BaseViewHolder?, item: GifData?) {
                Glide.with(this@MainActivity)
                        .asGif()
                        .apply(RequestOptions().placeholder(R.drawable.abc_ic_star_black_16dp))
                        .load(item?.images?.fixedWidth?.url)
                        .into(helper?.getView(R.id.img)!!)
            }
        }

        recycler.adapter = adapter

        Dal.searchGifs("cat", offset, LIMIT)
                .done {
                    if (offset == 0) {
                        adapter?.setNewData(it)
                    } else {
                        adapter?.addData(it)
                    }
                    offset += LIMIT
                }.fail {
            Log.e(TAG, "failed to load gifs", it)
        }
    }
}