package io.horizontalsystems.coinkit.storage

import androidx.room.*
import io.horizontalsystems.coinkit.models.Coin
import io.horizontalsystems.coinkit.models.ResourceInfo

@Dao
interface ResourceInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResouceInfo(info: ResourceInfo)

    @Query("SELECT * FROM ResourceInfo WHERE id=:resourceName")
    fun getResourceInfo(resourceName: String): ResourceInfo?

}
