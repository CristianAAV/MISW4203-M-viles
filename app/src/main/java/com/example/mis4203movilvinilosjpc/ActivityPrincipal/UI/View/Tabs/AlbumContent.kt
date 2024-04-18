package com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.Tabs


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mis4203movilvinilosjpc.R


@Preview(showSystemUi = true)
@Composable
fun AlbumContent() {
    Card {
        Modifier
            .fillMaxWidth()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           informacionAlbum(Modifier.weight(1f))
           imagenesAlbum(Modifier.weight(0.5f))


        }
    }
}

@Composable
fun informacionAlbum(modifier: Modifier) {
    Column(
        modifier =modifier
            .wrapContentSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Corazon",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
        Text(text = "Santana", textAlign = TextAlign.Center, fontSize = 15.sp)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Detalles")
        }
    }
}

@Composable
fun imagenesAlbum(modifier: Modifier) {
    Box(modifier =modifier.wrapContentSize() ){
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Select item tienda",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Blue, CircleShape)
                .padding(end = 4.dp)
        )
    }
}