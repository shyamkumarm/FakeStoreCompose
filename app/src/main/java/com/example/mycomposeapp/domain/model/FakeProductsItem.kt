package com.example.mycomposeapp.domain.model



import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class FakeProductsItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
) : Parcelable {

    companion object {
        val previewContent: FakeProductsItem = Json.decodeFromString<FakeProductsItem>(
            "{\n" +
                    "    \"id\": 1,\n" +
                    "    \"title\": \"Fjallraven - Foōldsack No. 1 Backpack, Fits 15 Laptops\",\n" +
                    "    \"price\": 109.95,\n" +
                    "    \"description\": \"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday\",\n" +
                    "    \"category\": \"men's clothing\",\n" +
                    "    \"image\": \"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg\",\n" +
                    "    \"rating\": {\n" +
                    "      \"rate\": 3.9,\n" +
                    "      \"count\": 120\n" +
                    "    }\n" +
                    "  }"
        )
    }


}