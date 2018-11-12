package com.github.afnascimento.mercadopago.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Installment {

    @SerializedName("payment_method_id")
    @Expose
    var paymentMethodId: String? = null

    @SerializedName("payment_type_id")
    @Expose
    var paymentTypeId: String? = null

    @SerializedName("issuer")
    @Expose
    var issuer: Issuer? = null

    @SerializedName("processing_mode")
    @Expose
    var processingMode: String? = null

    @SerializedName("payer_costs")
    @Expose
    var payerCosts: MutableList<PayerCost> = arrayListOf()

}
