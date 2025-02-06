package com.example.mycomposeapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.mycomposeapp.R
import com.example.mycomposeapp.domain.model.FakeProductsItem

@Composable
fun ListView(
    fakeProductsItems: List<FakeProductsItem>,
    paddingValues: PaddingValues = PaddingValues(10.dp),
    onClickAction: (FakeProductsItem) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(10.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(fakeProductsItems) { index, item ->
            Column(
                modifier = Modifier.clickable { onClickAction.invoke(item) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image)
                        .size(Size.ORIGINAL) // Set the target size to load the image at.
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "The design logo",
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
                Text(
                    text = item.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
                Text(
                    text = "Rs. " + item.price.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

        }
    }
}