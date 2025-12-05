package com.example.c37b

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c37b.ui.theme.Blue
import com.example.c37b.ui.theme.White

@Composable
fun HomeScreen(){
    data class Product(val image:Int,val label:String)

    val listData = listOf(
        Product(R.drawable.apple,"Apple"),
        Product(R.drawable.bettafish,"Bettafish"),
        Product(R.drawable.bird,"Bird"),
        Product(R.drawable.cat,"Cat"),
        Product(R.drawable.dog,"Dog"),
        Product(R.drawable.goldfish,"Gold fish"),
    )
    LazyColumn (
        modifier = Modifier.fillMaxSize().background(White)
    ) {
        item {
            Button(onClick = {}){
                Text("click me")
            }
            Button(onClick = {}){
                Text("click me")
            }
        }
        items(10){index->
            Text(index.toString())
        }
        item {

            Image(
                painterResource(R.drawable.banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            LazyRow {
                items(listData.size){index->
                    Column {
                        Image(
                            painterResource(listData[index].image),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp).padding(end = 10.dp)
                        )
                        Text(listData[index].label)
                    }
                }
            }
        }
        items(10){index->Text(index.toString())}

        item {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.height(500.dp)
            ) {
                items(listData.size){
                    index->
                    Image(
                        painterResource(listData[index].image),
                        contentDescription = null
                    )
                }
            }
        }



    }
}

@Preview
@Composable
fun HomePre(){
    HomeScreen()
}