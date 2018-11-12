package com.github.afnascimento.mercadopago.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Issuer {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

}
