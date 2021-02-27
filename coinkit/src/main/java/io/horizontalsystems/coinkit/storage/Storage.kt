package io.horizontalsystems.coinkit.storage

import io.horizontalsystems.coinkit.models.Coin
import io.horizontalsystems.coinkit.models.ResourceInfo
import io.horizontalsystems.coinkit.models.ResourceType

class Storage(database: Database) : IStorage {

    private val coinDao = database.coinDao
    private val resourceInfoDao = database.resourceInfoDao

    override fun saveCoins(coins: List<Coin>) {
        coinDao.insertCoins(coins)
    }

    override fun saveCoin(coin: Coin) {
        coinDao.insertCoin(coin)
    }

    override fun getCoins(): List<Coin> {
        return coinDao.getCoins()
    }

    override fun getCoin(coinId: String): Coin? {
        return coinDao.getCoin(coinId)
    }

    override fun getResourceInfo(resourceType: ResourceType): ResourceInfo? {
        return resourceInfoDao.getResourceInfo(resourceType.name)
    }

    override fun saveResourceInfo(resourceInfo: ResourceInfo) {
        resourceInfoDao.insertResouceInfo(resourceInfo)
    }

}


interface IStorage {
    fun saveCoins(coins: List<Coin>)
    fun saveCoin(coin: Coin)
    fun getCoins(): List<Coin>
    fun getCoin(coinId: String): Coin?
    fun getResourceInfo(resourceType: ResourceType): ResourceInfo?
    fun saveResourceInfo(resourceInfo: ResourceInfo)
}