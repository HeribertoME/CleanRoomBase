package com.example.roombase.domain.bussinesslogic

import com.example.roombase.data.manager.DataManager
import com.example.roombase.data.models.Person
import javax.inject.Inject

class PersonUseCaseImpl @Inject constructor(
    private val dataManager: DataManager
) : PersonUseCase {

    override suspend fun getPersons(): List<Person> {
        val entity = dataManager.getDBRepository().personDao().getAll()
        val personList = mutableListOf<Person>()
        entity.forEach {
            personList.add(Person(it.id, it.name, it.age, it.address))
        }
        return personList
    }
}