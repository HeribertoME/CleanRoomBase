package com.example.roombase.domain.bussinesslogic

import com.example.roombase.data.models.Person

interface PersonUseCase {

    suspend fun getPersons(): List<Person>
}