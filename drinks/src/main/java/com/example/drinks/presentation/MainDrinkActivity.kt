package com.example.drinks.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.drinks.R
import com.example.drinks.domain.models.Drink
import com.example.drinks.domain.models.LiquorProduct
import com.example.drinks.ui.theme.MyComposeAppTheme

class MainDrinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myDrinkViewmodel by viewModels<MyDrinkViewmodel>()
            val liquorProduct by myDrinkViewmodel.productState.collectAsStateWithLifecycle()
            MyComposeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DrinkListView(
                        dataCallback = liquorProduct,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DrinkListView(dataCallback: ProductState, modifier: Modifier = Modifier) {

    when (dataCallback) {
        is ProductState.Success -> {
            val state  = rememberLazyListState()
            val buttonVisible by remember {
                derivedStateOf {
                    state.firstVisibleItemIndex < 5
                }
            }
            val list = remember {
                mutableStateListOf<Drink>().apply { addAll(dataCallback.liquorProduct.drinks) }
            }
            Log.d("Shyam", "DrinkListView ${list.size}")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if(buttonVisible) {
                        Button(onClick = { list.removeAt(0) }) {
                            Text(
                                text = "Remove in Total item ${list.size}", modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                LazyColumn(state = state,
                    modifier = modifier, contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(items = list, key = { it.idDrink }) { it ->
                        Row(Modifier.border(2.dp, MaterialTheme.colorScheme.primary)) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.strDrinkThumb)
                                    .size(
                                        Size(
                                            200,
                                            200
                                        )
                                    ) // Set the target size to load the image at.
                                    .build(),
                                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                                error = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "The design logo",
                                modifier = Modifier.wrapContentHeight()
                            )
                            Text(
                                text = it.strDrink,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                            )

                        }
                    }
                }

            }
        }

        ProductState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )

        is ProductState.Error -> {
            Box(modifier = modifier.fillMaxSize().background(Color.Green),
                contentAlignment = Alignment.Center) {
                Text( modifier = modifier
                    .background(Color.Red),
                    text = dataCallback.exception.message.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyComposeAppTheme {
        DrinkListView(
            ProductState.Success(
                liquorProduct = LiquorProduct(
                    listOf(
                        Drink(
                            idDrink = "1",
                            strDrink = "shyam",
                            strDrinkThumb = ""
                        )
                    )
                )
            )
        )
    }
}