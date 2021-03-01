package io.horizontalsystems.coinkit.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.horizontalsystems.coinkit.models.CoinType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var coinManager: CoinManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coinManager = CoinManager(this, false)

        btnGetCoins.setOnClickListener {
            getCoins()
        }

        btnGetCoinsByType.setOnClickListener {
            getCoin()
        }
    }

    private fun getCoins(){

        val coins = coinManager.getCoins()
        txtCoin1.text = "Number of Coins:${coins.size}"
        txtCoin2.text = "1:${coins[0].code} - ${coins[0].title} - ${coins[0].decimal} - ${coins[0].type.getCoinId()}"
        txtCoin3.text = "1:${coins[1].code} - ${coins[1].title} - ${coins[1].decimal} - ${coins[1].type.getCoinId()}"
        txtCoin4.text = "1:${coins[2].code} - ${coins[2].title} - ${coins[2].decimal} - ${coins[2].type.getCoinId()}"
    }

    private fun getCoin(){

        val coin1 = coinManager.getCoin(CoinType.fromString("bitcoin"))
        val coin2 = coinManager.getCoin(CoinType.fromString("erc20|0x68A118Ef45063051Eac49c7e647CE5Ace48a68a5"))

        coin1?.let {
            txtCoinType1.text = "${it.code} - ${it.title} - ${it.decimal} - ${it.type.getCoinId()}"
        }

        coin2?.let {
            txtCoinType2.text = "${it.code} - ${it.title} - ${it.decimal} - ${it.type.getCoinId()}"
        }
    }

}