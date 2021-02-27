package io.horizontalsystems.coinkit.demo

import android.content.Context
import io.horizontalsystems.coinkit.CoinKit
import io.horizontalsystems.coinkit.models.Coin
import io.horizontalsystems.coinkit.models.CoinType

class CoinManager(context: Context, isTestnet: Boolean) {
    private val coinKit = CoinKit.create(context, isTestnet)

    fun getCoins() : List<Coin>{
        return coinKit.getCoins()
    }

    fun getCoin(coinType: CoinType) : Coin? {
        return coinKit.getCoin(coinType)
    }

}