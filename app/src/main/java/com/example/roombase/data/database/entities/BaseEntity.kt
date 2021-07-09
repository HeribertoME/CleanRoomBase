package com.example.roombase.data.database.entities

interface BaseEntity<T> {
    fun toBo(): T
}