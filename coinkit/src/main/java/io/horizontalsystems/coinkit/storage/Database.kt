package io.horizontalsystems.coinkit.storage

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.horizontalsystems.coinkit.models.Coin
import io.horizontalsystems.coinkit.models.ResourceInfo

@androidx.room.Database(version = 1, exportSchema = false, entities = [
    Coin::class,
    ResourceInfo::class
])

@TypeConverters(DatabaseConverters::class)

abstract class Database : RoomDatabase() {
    abstract val coinDao: CoinDao
    abstract val resourceInfoDao: ResourceInfoDao

    companion object {
        fun create(context: Context): Database {
            return Room.databaseBuilder(context, Database::class.java, "coin-kit-database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
