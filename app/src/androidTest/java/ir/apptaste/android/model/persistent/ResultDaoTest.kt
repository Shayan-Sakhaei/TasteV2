package ir.apptaste.android.model.persistent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.apptaste.android.model.persistence.AppDatabase
import ir.apptaste.android.model.persistence.ResultDao
import ir.apptaste.android.model.persistence.entity.ResultEntity
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var resultDao: ResultDao

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        resultDao = appDatabase.resultDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun getResultsWhenNoResultInserted() {
        resultDao.getAll().test().assertValue {
            it == emptyList<ResultEntity>()
        }
    }

    @Test
    fun insertAndGetResultByName() {
        val result = ResultEntity("LedZeppelin", "music", "", "", "", "")
        resultDao.insert(result).blockingAwait()

        resultDao.getByName(result.name).test().assertValue { resultEntity ->
            resultEntity == result && resultEntity.name == result.name && resultEntity.type == result.type
        }
    }

    @Test
    fun insertAndGetResultsByType() {
        val result1 = ResultEntity("LedZeppelin", "music", "", "", "", "")
        val result2 = ResultEntity("Avatar", "movie", "", "", "", "")
        resultDao.insert(result1, result2).blockingAwait()

        resultDao.getByType("movie").test().assertValue { resultList ->
            resultList.contains(result2)
        }
    }

    @Test
    fun getAllRetrievesAllData() {
        val result1 = ResultEntity("LedZeppelin", "music", "", "", "", "")
        val result2 = ResultEntity("Avatar", "movie", "", "", "", "")
        resultDao.insert(result1, result2).blockingAwait()

        resultDao.getAll().test().assertValue { resultList ->
            resultList.contains(result1)
            resultList.contains(result2)
        }
    }
}


