package ir.apptaste.android.model.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import ir.apptaste.android.model.persistence.entity.ResultEntity

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg resultEntity: ResultEntity): Completable

    @Query("SELECT * FROM result_table")
    fun getAll(): Observable<List<ResultEntity>>

    @Delete
    fun delete(resultEntity: ResultEntity): Completable

    @Query("SELECT * FROM result_table WHERE name = :queryName ")
    fun getByName(queryName: String): Observable<ResultEntity>

    @Query("SELECT * FROM result_table WHERE type = :queryType ")
    fun getByType(queryType: String): Observable<List<ResultEntity>>
}