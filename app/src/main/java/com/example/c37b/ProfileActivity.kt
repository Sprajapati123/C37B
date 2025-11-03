package com.example.c37b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.c37b.ui.theme.C37BTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileBody()
        }
    }
}

@Composable
fun ProfileBody(){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row {
            //widgets
        }
        Row{
            //widgets
        }
        Column {
            //widgets
        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    ProfileBody()
}