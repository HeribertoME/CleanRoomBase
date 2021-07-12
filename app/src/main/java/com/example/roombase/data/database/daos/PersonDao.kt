package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.roombase.data.database.entities.PersonEntity

/**
 * PeopleDao
 */
@Dao
abstract class PersonDao : BaseDao<PersonEntity> {

    /**
     * Query method to get all person
     */
    @Query("SELECT * FROM person")
    abstract suspend fun getAll(): List<PersonEntity>

    /**
     * Method to get people by id
     *
     * @return person entity
     */
    @Transaction @Query("SELECT * FROM person WHERE id = :id")
    abstract suspend fun findById(id: Int) : PersonEntity

}