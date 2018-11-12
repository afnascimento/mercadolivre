package com.github.afnascimento.mercadopago.data.model

import android.os.Parcel
import android.os.Parcelable
import com.github.afnascimento.mercadopago.ui.adapter.CardItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.StringUtils

class PaymentMethod : CardItem, Parcelable {

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
    @SerializedName("payment_type_id")
    @Expose
    var paymentTypeId: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("deferred_capture")
    @Expose
    var deferredCapture: String? = null

    constructor()

    constructor(id: String?, name: String?, paymentTypeId: String?, status: String?, secureThumbnail: String?, thumbnail: String?, deferredCapture: String?) {
        this.id = id
        this.name = name
        this.paymentTypeId = paymentTypeId
        this.status = status
        this.secureThumbnail = secureThumbnail
        this.thumbnail = thumbnail
        this.deferredCapture = deferredCapture
    }

    constructor(source: Parcel) : this(
            source.readString(),
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
        writeString(paymentTypeId)
        writeString(status)
        writeString(secureThumbnail)
        writeString(thumbnail)
        writeString(deferredCapture)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PaymentMethod> = object : Parcelable.Creator<PaymentMethod> {
            override fun createFromParcel(source: Parcel): PaymentMethod = PaymentMethod(source)
            override fun newArray(size: Int): Array<PaymentMethod?> = arrayOfNulls(size)
        }
    }
}
