package ir.apptaste.android.model.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.apptaste.android.model.persistence.entity.InfoEntity
import ir.apptaste.android.model.persistence.entity.ResultEntity

@Database(entities = [InfoEntity::class, ResultEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultDao
}