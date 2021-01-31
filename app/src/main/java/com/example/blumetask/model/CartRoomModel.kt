package com.example.blumetask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class CartRoomModel(
    @PrimaryKey(autoGenerate = true) var xid: Long,
    @ColumnInfo (name = "id") var id: String?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "price") var price: String?,
    @ColumnInfo(name = "quantity") var quantity: String?
){

    constructor(id: String,name: String, price: String, quantity: String) : this(0,id, name, price, quantity)
}