package io.horizontalsystems.coinkit

import android.content.Context
import io.horizontalsystems.coinkit.models.Coin
import io.horizontalsystems.coinkit.models.CoinType
import io.horizontalsystems.coinkit.providers.CoinManager
import io.horizontalsystems.coinkit.providers.CoinProvider
import io.horizontalsystems.coinkit.storage.Database
import io.horizontalsystems.coinkit.storage.Storage

class CoinKit(private val coinManager: CoinManager) {

    fun getCoins(): List<Coin> {
        return coinManager.getCoins()
    }

    fun getDefaultCoins(): List<Coin> {
        return coinManager.getDefaultCoins()
    }

    fun getCoin(id: String): Coin? {
        return coinManager.getCoin(id)
    }

    fun getCoin(type: CoinType): Coin? {
        return coinManager.getCoin(type.getCoinId())
    }

    fun saveCoin(coin: Coin) {
        return coinManager.saveCoin(coin)
    }

    companion object {

        fun create(context: Context, isTestnet: Boolean = false): CoinKit {
            val storage = Storage(Database.create(context))
            val coinProvider = CoinProvider(context, isTestnet)
            val coinManager = CoinManager(coinProvider, storage)

            return CoinKit(coinManager)
        }
    }
}