package com.example.blumetask.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    @SerializedName("condition")
    val condition: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("material")
    val material: String = "",
    @SerializedName("menuId")
    val menuId: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: String = ""
) : Parcelable