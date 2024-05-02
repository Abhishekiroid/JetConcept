package com.example.jetcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetcompose.data.Quote

@Composable()
fun  QuoteListItem (quote: Quote,onClick:(quote: Quote)->Unit){
    Card(elevation = CardDefaults.cardElevation(4.dp),
         modifier = Modifier
             .clickable{onClick(quote)}
             .padding(6.dp,5.dp,6.dp,0.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp))
        {
        Image(imageVector = Icons.Default.FormatQuote,
            colorFilter = ColorFilter.tint(Color.White),
            alignment = Alignment.TopStart,
            contentDescription ="quotes",
            modifier = Modifier
                .size(40.dp)
                .rotate(180F)
                .background(Color.Black)
        )
            Spacer(modifier = Modifier.padding(4.dp))
            Column (modifier = Modifier.weight(1F)) {
                Text(
                    text = quote.text, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )

                Box(
                    modifier = Modifier
                        .background(Color(0XFFEEEEEE))
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }

    }
}

