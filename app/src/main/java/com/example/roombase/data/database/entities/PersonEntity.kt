package com.example.roombase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * People entity
 *
 * @param id id person value
 * @param name name person value
 * @param age age person value
 * @param address address person value
 */
@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey( autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val address: String
)