package com.example.c37b

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import coil3.compose.AsyncImage
import com.example.c37b.model.ProductModel
import com.example.c37b.repository.ProductRepoImpl
import com.example.c37b.ui.theme.Blue
import com.example.c37b.ui.theme.White
import com.example.c37b.viewmodel.ProductViewModel
import kotlin.toString

@Composable
fun HomeScreen() {
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }
    var productViewModel = remember { ProductViewModel(ProductRepoImpl()) }
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val data = productViewModel.products.observeAsState(initial = null)
    LaunchedEffect(data.value) {
        productViewModel.getAllProduct()

        data.value?.let { product ->
            pName = product.productName
            pPrice = product.price.toString()
            pDesc = product.description
        }
    }

    val products = productViewModel.allProducts.observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        item {
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    }, // dismiss when clicked outside
                    confirmButton = {
                        TextButton (onClick = {
                            val model = ProductModel(
                                productId = data.value!!.productId,
                                productName = pName,
                                price = pPrice.toDouble(),
                                description = pDesc,
                                categoryId = ""

                            )
                            productViewModel.updateProduct(model){
                                success,message->
                                if(success){
                                    showDialog = false
                                }else{
                                    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }) {
                            Text("Update")
                        }
                    },
                    dismissButton = {
                        TextButton (onClick = {
                            // Cancel action
                            showDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = { Text(text = "Update Product") },
                    text = { Column {
                        Text("Product name")
                        OutlinedTextField(value = pName, onValueChange = {pName = it})
                        Text("Product price")
                        OutlinedTextField(value = pPrice, onValueChange = {pPrice = it})
                        Text("Product description")
                        OutlinedTextField(value = pDesc, onValueChange = {pDesc = it})
                    } }
                )
            }
        }

        items(products.value!!.size) { index ->
            val data = products.value!![index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            ) {
                Column {
                    AsyncImage(
                        data.imageUrl,
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Text(data.productName)
                    Text(data.price.toString())
                    Text(data.description)
                    IconButton(onClick = {
                        showDialog = true
                        productViewModel.getProductById(data.productId)
                    }) { Icon(Icons.Default.Edit, contentDescription = null) }

                    IconButton(onClick = {

                    }) { Icon(Icons.Default.Delete, contentDescription = null) }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePre() {
    HomeScreen()
}