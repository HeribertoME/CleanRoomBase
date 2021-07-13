package com.example.roombase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.roombase.data.database.entities.SessionEntity

/**
 * SessionDao
 */
@Dao
abstract class SessionDao : BaseDao<SessionEntity> {

    /**
     * Query method to get sessions
     */
    @Query("SELECT * FROM session")
    abstract suspend fun getSessions(): List<SessionEntity>

    /**
     * Query method to get session by id
     *
     * @param id session id value
     */
    @Query("SELECT * FROM session WHERE id = :id")
    abstract suspend fun findSessionById(id: Int): SessionEntity

}