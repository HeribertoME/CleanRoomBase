package com.example.roombase.data.models

interface BaseBo<T> {
    fun toEntity(): T
}