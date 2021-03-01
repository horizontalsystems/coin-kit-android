package io.horizontalsystems.coinkit.models

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eclipsesource.json.Json
import java.io.InputStreamReader

@Entity
class Coin(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val type: CoinType,
    val code: String,
    val title: String,
    val decimal: Int){

    val id: String
        get() = type.ID
}

sealed class CoinType {
    object Bitcoin : CoinType()
    object Litecoin : CoinType()
    object BitcoinCash : CoinType()
    object Dash : CoinType()
    object Ethereum : CoinType()
    object BinanceSmartChain : CoinType()
    object Zcash : CoinType()
    class Erc20(val address: String) : CoinType()
    class Bep2(val symbol: String) : CoinType()
    class Bep20(val address: String) : CoinType()
    class Unsupported(val id: String) : CoinType()

    val ID: String
        get() = getCoinId()

    fun getCoinId(): String {
        return when (this) {
            is Bitcoin -> "bitcoin"
            is Litecoin -> "litecoin"
            is BitcoinCash -> "bitcoinCash"
            is Dash -> "dash"
            is Ethereum -> "ethereum"
            is Zcash -> "zcash"
            is BinanceSmartChain -> "binanceSmartChain"
            is Erc20 -> "erc20|${this.address}"
            is Bep2 -> "bep2|${this.symbol}"
            is Bep20 -> "bep20|${this.address}"
            is Unsupported -> "unsupported|${this.id}"
        }
    }

    companion object {

        fun fromString(id: String): CoinType {
            val chunks = id.split("|")

            if (chunks.size == 1) {
                return when (chunks[0]) {
                    "bitcoin" -> Bitcoin
                    "litecoin" -> Litecoin
                    "bitcoinCash" -> BitcoinCash
                    "dash" -> Dash
                    "ethereum" -> Ethereum
                    "zcash" -> Zcash
                    "binanceSmartChain" -> BinanceSmartChain
                    else -> Unsupported(chunks[0])
                }
            } else {
                return when (chunks[0]) {
                    "erc20" -> Erc20(chunks[1])
                    "bep2" -> Bep2(chunks[1])
                    "bep20" -> Bep20(chunks[1])
                    "unsupported" -> Unsupported(chunks.drop(1).joinToString("|"))
                    else -> Unsupported(chunks.joinToString("|"))
                }
            }
        }
    }
}

@Entity
class ResourceInfo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val resourceType: ResourceType,
    val version: Int)

enum class ResourceType{
    DEFAULT_COINS;
}

data class CoinResponse(val version: Int, val coins: List<Coin>){
    companion object{
        fun parseFile(context: Context, fileName: String): CoinResponse{
            val inputStream = context.assets.open(fileName)
            val jsonObject = Json.parse(InputStreamReader(inputStream))
            val coins = mutableListOf<Coin>()

            val version = jsonObject.asObject().get("version").asInt()
            jsonObject.asObject().get("coins").asArray().forEach { category ->
                val code = category.asObject().get("code").asString()
                val title = category.asObject().get("title").asString()
                val decimal =  category.asObject().get("decimal").asInt()
                var typeString = category.asObject().get("type").asString()

                if(category.asObject().get("address") != null){
                    if(!category.asObject().get("address").isNull)
                        typeString = "${typeString.trim()}|${category.asObject().get("address").asString().trim()}"
                } else if(category.asObject().get("symbol") != null){
                    if(!category.asObject().get("symbol").isNull)
                        typeString = "${typeString.trim()}|${category.asObject().get("symbol").asString().trim()}"
                }

                coins.add(Coin(CoinType.fromString(typeString), code, title, decimal))
            }

            return CoinResponse(version, coins)

        }
    }
}