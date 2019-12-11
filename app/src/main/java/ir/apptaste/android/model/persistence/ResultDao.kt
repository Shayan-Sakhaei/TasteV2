package ir.apptaste.android.model.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ir.apptaste.android.model.persistence.entity.ResultEntity

@Dao
interface ResultDao {

    @Insert
    fun insert(vararg resultEntity: ResultEntity): Completable

    @Delete
    fun delete(resultEntity: ResultEntity): Completable

    @Query("SELECT * FROM result_table WHERE name = :queryName ")
    fun getByName(queryName: String): Observable<List<ResultEntity>>

    @Query("SELECT * FROM result_table WHERE type = :queryName ")
    fun getByType(queryName: String): Observable<List<ResultEntity>>

    @Query("SELECT * FROM result_table LIMIT 1")
    fun getAnyResult(): LiveData<ResultEntity>

}