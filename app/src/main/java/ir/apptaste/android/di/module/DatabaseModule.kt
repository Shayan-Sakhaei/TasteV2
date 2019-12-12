package ir.apptaste.android.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.apptaste.android.model.persistence.AppDatabase
import ir.apptaste.android.model.persistence.ResultDao

@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "appTaste.db"
        ).build()
    }

    @Provides
    fun provideResultDao(appDatabase: AppDatabase): ResultDao {
        return appDatabase.resultDao()
    }
}