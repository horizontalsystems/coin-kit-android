package io.horizontalsystems.coinkit.storage

import androidx.room.*
import io.horizontalsystems.coinkit.models.Coin

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(all: List<Coin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoin(coin: Coin)

    @Query("SELECT * FROM Coin")
    fun getCoins(): List<Coin>

    @Query("SELECT * FROM Coin WHERE id=:coinId")
    fun getCoin(coinId: String): Coin?

}
