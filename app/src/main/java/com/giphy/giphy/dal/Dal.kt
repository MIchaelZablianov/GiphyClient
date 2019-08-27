package com.giphy.giphy.dal

import com.giphy.giphy.network.NetworkManager
import com.giphy.giphy.network.models.GifData
import org.jdeferred.Promise
import org.jdeferred.android.AndroidDeferredObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Dal {

    val gifQueryMap: HashMap<String, ArrayList<GifData>> = HashMap()

    fun searchGifs(query: String, offset: Int = 0, limit: Int = 25): Promise<List<GifData>, Throwable, Void> {
        val deferred = AndroidDeferredObject<List<GifData>, Throwable, Void>()

        if (gifQueryMap[query] != null) {
            if (gifQueryMap[query]?.size!! >= offset + limit) {
                deferred.resolve(gifQueryMap[query]?.subList(offset, offset + limit))
            } else if (gifQueryMap[query]?.size!! in (offset + 1)..(limit - 1)) {
                deferred.resolve(gifQueryMap[query]?.subList(offset, gifQueryMap[query]?.size!! - 1))
            }
        } else {
            gifQueryMap[query] = ArrayList()
        }

        if (deferred.isPending) {
            NetworkManager.giphyGifApi.search(query, offset, limit).enqueue(object : Callback<List<GifData>> {
                override fun onFailure(call: Call<List<GifData>>?, t: Throwable?) {
                    deferred.reject(t)
                }

                override fun onResponse(call: Call<List<GifData>>?, response: Response<List<GifData>>?) {
                    gifQueryMap[query]?.addAll(response?.body()!!)
                    deferred.resolve(response?.body())
                }
            })
        }

        return deferred.promise()
    }
}