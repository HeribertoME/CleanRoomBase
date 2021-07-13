package com.example.roombase.data.database.entities

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.roombase.data.database.AppDatabase
import com.example.roombase.data.database.daos.SessionDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SessionEntityTest : TestCase() {

    // get reference to the AppDatabase and OrderDao class
    private lateinit var db: AppDatabase
    private lateinit var sessionDao: SessionDao
    private var sessionId: Int = 0

    /**
     * Override function setUp() and annotate it with @Before
     * this function will be called at first when this test class is called
     */
    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        // initialize the db and dao variables
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        sessionDao = db.sessionDao()

        // insert values
        runBlocking {
            val session = SessionEntity(
                id = 1,
                accessToken = "123-ABC",
                deviceSerialNumber = "ABCD-9876",
                isPos = true,
                userName = "dev",
                partnerId = 1
            )
            sessionId = sessionDao.insert(session).toInt()
        }
    }

    /**
     * Override function closeDb() and annotate it with @After
     * this function will be called at last when this test class is called
     */
    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getSessions() {
        runBlocking {
            val sessions = sessionDao.getSessions()
            assertTrue(sessions.isNotEmpty())
        }
    }

    @Test
    fun findSessionById() {
        runBlocking {
            val session = sessionDao.findSessionById(sessionId)
            assertEquals("dev", session.userName)
        }
    }

    @Test
    fun insertSessionList() {
        runBlocking {
            val sessions = listOf(
                SessionEntity(),
                SessionEntity(),
                SessionEntity()
            )
            sessionDao.insert(sessions)
            val total = sessionDao.getSessions()
            assert(total.size == 4)
        }
    }

    @Test
    fun updateSession() {
        runBlocking {
            val currentSession = sessionDao.findSessionById(sessionId)
            currentSession.userName = "code"
            val updated = sessionDao.update(currentSession)
            assert(updated >= 1)
        }
    }

    @Test
    fun deleteSession() {
        runBlocking {
            val session = sessionDao.findSessionById(sessionId)
            val deleted = sessionDao.delete(session)
            assertTrue(deleted >= 1)
        }
    }

}