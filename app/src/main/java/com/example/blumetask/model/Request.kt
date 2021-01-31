package com.example.blumetask.model
import com.google.gson.annotations.SerializedName


data class Request(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("products")
    val products: List<CartRoomModel> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("paymentMethod")
    val paymentMethod: String = "",
    @SerializedName("paymentState")
    val paymentState: String = "",
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("total")
    val total: String = ""
) {

}