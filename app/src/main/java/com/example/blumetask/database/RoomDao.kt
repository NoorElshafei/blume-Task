package com.example.blumetask.database

import androidx.room.*
import com.example.blumetask.model.CartRoomModel

@Dao
interface RoomDao {

    @Query("SELECT * FROM CartRoomModel")
    fun getAll(): List<CartRoomModel>

    @Query("SELECT * FROM CartRoomModel WHERE name LIKE :userIds")
    fun loadAllByIds(userIds: String): List<CartRoomModel>

    @Query("SELECT * FROM CartRoomModel WHERE name LIKE :name")
    fun findByName(name: String): CartRoomModel


    @Insert
    fun insertAll(vararg cartModel: CartRoomModel)

    @Delete
    fun delete(cartModel: CartRoomModel)

    @Query("DELETE FROM CartRoomModel")
    fun deleteAll()

    @Update
    fun updateProduct(cartModel: CartRoomModel?): Int

    fun updateCart(id: String, newQuantity: String?) {
        val cartModel: CartRoomModel = getCart(id)!!
        cartModel.quantity = newQuantity
        updateProduct(cartModel)
    }

    @Query("SELECT * FROM CartRoomModel WHERE id LIKE :id")
    fun getCart(id: String): CartRoomModel?
}