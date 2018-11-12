package com.github.afnascimento.mercadopago.data.model

import android.os.Parcel
import android.os.Parcelable
import com.github.afnascimento.mercadopago.ui.adapter.CardItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.StringUtils

class CardIssues : CardItem, Parcelable {

    override fun imageUrl(): String {
        return StringUtils.trimToEmpty(thumbnail)
    }

    override fun name(): String {
        return StringUtils.trimToEmpty(name)
    }

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

    @SerializedName("processing_mode")
    @Expose
    var processingMode: String? = null

    @SerializedName("merchant_account_id")
    @Expose
    var merchantAccountId: String? = null

    constructor()

    constructor(id: String?, name: String?, secureThumbnail: String?, thumbnail: String?, processingMode: String?, merchantAccountId: String?) {
        this.id = id
        this.name = name
        this.secureThumbnail = secureThumbnail
        this.thumbnail = thumbnail
        this.processingMode = processingMode
        this.merchantAccountId = merchantAccountId
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(secureThumbnail)
        writeString(thumbnail)
        writeString(processingMode)
        writeString(merchantAccountId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CardIssues> = object : Parcelable.Creator<CardIssues> {
            override fun createFromParcel(source: Parcel): CardIssues = CardIssues(source)
            override fun newArray(size: Int): Array<CardIssues?> = arrayOfNulls(size)
        }
    }
}