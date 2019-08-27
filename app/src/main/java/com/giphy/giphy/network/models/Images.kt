package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Images {

    @SerializedName("fixed_height_still")
    @Expose
    var fixedHeightStill: ImageData? = null
    @SerializedName("original_still")
    @Expose
    var originalStill: ImageData? = null
    @SerializedName("fixed_width")
    @Expose
    var fixedWidth: ImageData? = null
    @SerializedName("fixed_height_small_still")
    @Expose
    var fixedHeightSmallStill: ImageData? = null
    @SerializedName("fixed_height_downsampled")
    @Expose
    var fixedHeightDownsampled: ImageData? = null
    @SerializedName("preview")
    @Expose
    var preview: ImageData? = null
    @SerializedName("fixed_height_small")
    @Expose
    var fixedHeightSmall: ImageData? = null
    @SerializedName("downsized_still")
    @Expose
    var downsizedStill: ImageData? = null
    @SerializedName("downsized")
    @Expose
    var downsized: ImageData? = null
    @SerializedName("downsized_large")
    @Expose
    var downsizedLarge: ImageData? = null
    @SerializedName("fixed_width_small_still")
    @Expose
    var fixedWidthSmallStill: ImageData? = null
    @SerializedName("preview_webp")
    @Expose
    var previewWebp: ImageData? = null
    @SerializedName("fixed_width_still")
    @Expose
    var fixedWidthStill: ImageData? = null
    @SerializedName("fixed_width_small")
    @Expose
    var fixedWidthSmall: ImageData? = null
    @SerializedName("downsized_small")
    @Expose
    var downsizedSmall: ImageData? = null
    @SerializedName("fixed_width_downsampled")
    @Expose
    var fixedWidthDownsampled: ImageData? = null
    @SerializedName("downsized_medium")
    @Expose
    var downsizedMedium: ImageData? = null
    @SerializedName("original")
    @Expose
    var original: ImageData? = null
    @SerializedName("fixed_height")
    @Expose
    var fixedHeight: ImageData? = null
    @SerializedName("looping")
    @Expose
    var looping: ImageData? = null
    @SerializedName("original_mp4")
    @Expose
    var originalMp4: ImageData? = null
    @SerializedName("preview_gif")
    @Expose
    var previewGif: ImageData? = null
    @SerializedName("480w_still")
    @Expose
    var _480wStill: ImageData? = null

}