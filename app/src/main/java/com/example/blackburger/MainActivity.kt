package com.example.blackburger

import android.content.Intent
import android.os.Bundle
import android.telecom.Call.Details
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blackburger.ui.theme.BlackBurgerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackBurgerTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {

    Box {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "description",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column {

            Column(
                modifier = Modifier
                    .weight(2f).padding(5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.menu),
                    contentDescription = "description",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.align(Alignment.Start).size(30.dp).weight(1f)
                )

                Text(
                    text = "Bienvenue chez ",
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.Start).weight(1f)
                )
                Text(
                    text = "BlackBurger!",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.align(Alignment.Start).weight(1f)
                )
            }

            var selectedIndex by remember { mutableStateOf(-1) }

            LazyRow(modifier = Modifier.weight(0.8f).padding(5.dp)) {
                items(8) { i ->
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        painter = painterResource(pickMenuImageFromIndex(i)),
                        contentDescription = "description",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { selectedIndex = i }
                            .background(
                                color = if (selectedIndex == i) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                            .padding(10.dp,0.dp)
                    )
                }
            }

            LazyColumn(modifier = Modifier.weight(10f).fillMaxWidth().padding(5.dp)) {
                itemsIndexed(items = (0..19).toList().chunked(2))
                { index, pair ->
                    Row(modifier = Modifier.fillMaxSize()) {
                        pair.forEach { item ->
                            Box(
                                modifier = Modifier.weight(2f).padding(10.dp,40.dp)
                                    .clickable {
                                        /*
                                        val context = LocalContext.current
                                        val intent = Intent(context, DetailsActivity::class.java)
                                        context.startActivity(intent)
                                        */
                                    }
                            ) {

                                Box(modifier = Modifier
                                    .height(180.dp)
                                    .align(Alignment.Center)
                                    .offset(x = 0.dp, y = 80.dp)
                                    .background(
                                        color = Color.Black.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(30.dp)
                                    )) {

                                    Column(modifier = Modifier
                                        .fillMaxSize()) {}
                                }

                                Column (modifier = Modifier.align(Alignment.Center)) {
                                    Image(
                                        painter = painterResource(pickBurgerImageFromIndex()),
                                        contentDescription = "description",
                                        modifier = Modifier
                                            .size(150.dp))
                                    Text(
                                        text = "Beef Burger",
                                        fontStyle = FontStyle.Normal,
                                        color = Color.White,
                                        fontSize = 30.sp)
                                    Text(
                                        text = "spicy with garlic",
                                        color = Color.White,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 20.sp)
                                }


                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MyPreview")
@Composable
fun DefaultPreview() {
    BlackBurgerTheme {
        Greeting()
    }
}

fun pickMenuImageFromIndex(i:Int): Int {
    return when (i) {
        0 -> R.drawable.burger
        1 -> R.drawable.coffee
        2 -> R.drawable.fries
        3 -> R.drawable.gelato
        4 -> R.drawable.soda
        else -> R.drawable.burger
    }
}

fun pickBurgerImageFromIndex(): Int {
    return if ((0..1).random() == 1)
        R.drawable.burgerone
    else
        R.drawable.burgertwo
}