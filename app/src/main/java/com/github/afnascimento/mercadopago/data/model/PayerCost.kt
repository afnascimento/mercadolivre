package com.github.afnascimento.mercadopago.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PayerCost : Parcelable {

    @SerializedName("installments")
    @Expose
    var installments: Int? = null
    @SerializedName("installment_rate")
    @Expose
    var installmentRate: Double? = null
    @SerializedName("discount_rate")
    @Expose
    var discountRate: Int? = null
    @SerializedName("labels")
    @Expose
    var labels: List<String>? = null
    @SerializedName("installment_rate_collector")
    @Expose
    var installmentRateCollector: List<String>? = null
    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Int? = null
    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int? = null
    @SerializedName("recommended_message")
    @Expose
    var recommendedMessage: String? = null
    @SerializedName("installment_amount")
    @Expose
    var installmentAmount: Double? = null
    @SerializedName("total_amount")
    @Expose
    var totalAmount: Double? = null

    constructor()

    constructor(installments: Int?, installmentRate: Double?, discountRate: Int?, labels: List<String>?, installmentRateCollector: List<String>?, minAllowedAmount: Int?, maxAllowedAmount: Int?, recommendedMessage: String?, installmentAmount: Double?, totalAmount: Double?) {
        this.installments = installments
        this.installmentRate = installmentRate
        this.discountRate = discountRate
        this.labels = labels
        this.installmentRateCollector = installmentRateCollector
        this.minAllowedAmount = minAllowedAmount
        this.maxAllowedAmount = maxAllowedAmount
        this.recommendedMessage = recommendedMessage
        this.installmentAmount = installmentAmount
        this.totalAmount = totalAmount
    }

    constructor(source: Parcel) : this(
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.createStringArrayList(),
            source.createStringArrayList(),
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readValue(Int::class.java.classLoader) as Int?,
            source.readString(),
            source.readValue(Double::class.java.classLoader) as Double?,
            source.readValue(Double::class.java.classLoader) as Double?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(installments)
        writeValue(installmentRate)
        writeValue(discountRate)
        writeStringList(labels)
        writeStringList(installmentRateCollector)
        writeValue(minAllowedAmount)
        writeValue(maxAllowedAmount)
        writeString(recommendedMessage)
        writeValue(installmentAmount)
        writeValue(totalAmount)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PayerCost> = object : Parcelable.Creator<PayerCost> {
            override fun createFromParcel(source: Parcel): PayerCost = PayerCost(source)
            override fun newArray(size: Int): Array<PayerCost?> = arrayOfNulls(size)
        }
    }
}
