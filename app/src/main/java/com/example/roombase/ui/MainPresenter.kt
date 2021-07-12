package com.example.roombase.ui

import com.example.roombase.data.models.Person
import com.example.roombase.domain.bussinesslogic.PersonUseCase

class MainPresenter constructor(
    private val personUseCase: PersonUseCase
) {

    suspend fun getPersons(): List<Person> = personUseCase.getPersons()

}