package com.giphy.giphy.dal

import com.giphy.giphy.network.NetworkManager
import com.giphy.giphy.network.models.GifData
import com.giphy.giphy.network.models.GifResponseData
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
            NetworkManager.giphyGifApi.search(query, offset, limit).enqueue(object : Callback<GifResponseData> {
                override fun onFailure(call: Call<GifResponseData>?, t: Throwable?) {
                    deferred.reject(t)
                }

                override fun onResponse(call: Call<GifResponseData>?, response: Response<GifResponseData>?) {
                    gifQueryMap[query]?.addAll(response?.body()?.data!!)
                    deferred.resolve(response?.body()?.data)
                }
            })
        }

        return deferred.promise()
    }
}