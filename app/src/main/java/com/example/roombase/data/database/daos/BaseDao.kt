package com.example.roombase.data.database.daos

import androidx.room.*

interface BaseDao<T> {

    /**
     * Insert an object on db
     *
     * @param obj object to be inserted
     *
     * @return single event
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    /**
     * Insert a list of objects on db
     *
     * @param obj objects to be inserted
     *
     * @return single event
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T): List<Long>

    /**
     * Update an object from the db
     *
     * @param obj Object to be updated
     *
     * @return single event
     */
    @Update
    suspend fun update(obj: T): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     *
     * @return single event
     */
    @Delete
    suspend fun delete(obj: T): Int
}