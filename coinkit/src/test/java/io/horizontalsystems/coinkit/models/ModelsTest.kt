package io.horizontalsystems.coinkit.models

import org.junit.Assert
import org.junit.Test

class ModelsTest {

    private val coinBtc = Coin(CoinType.Bitcoin, "SFP", "SFP", 0)
    private val tokenSFP1 = Coin(CoinType.fromString("bep20|0xd41fdb03ba84762dd66a0af1a6c8540ff1ba5dfb"),"SFP","SFP",0)
    private val tokenSFP2 = Coin(CoinType.Bep20("0xd41fdb03ba84762dd66a0af1a6c8540ff1ba5dfb"),"SFPT","SFP-TOKEN",0)

    @Test
    fun testCoinsClass() {
        Assert.assertEquals(tokenSFP1, tokenSFP2)
        Assert.assertNotEquals(tokenSFP1, coinBtc)
    }
}