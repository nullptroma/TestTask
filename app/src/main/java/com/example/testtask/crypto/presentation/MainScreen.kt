package com.example.testtask.crypto.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testtask.crypto.domain.Cryptocurrency

@Composable
fun MainScreen(state: MainScreenState, onLoadClick : ()->Unit, onShowClick : ()->Unit) {
    Column(
        Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween) {

        Box(modifier = Modifier.weight(1f, false).fillMaxSize(), contentAlignment = Alignment.Center) {
            if(state.error!=null)
                Text(text = state.error)
            else if(state.isLoading)
                CircularProgressIndicator()
            else
                Table(tableData = state.cryptos)
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)){
            OutlinedButton(onClick = { onLoadClick() }) {
                Text(text = "Load")
            }

            OutlinedButton(onClick = { onShowClick() }) {
                Text(text = "Show")
            }
        }
    }
}

@Composable
fun Table(tableData: List<Cryptocurrency>) {
    val column1Weight = .3f
    val column2Weight = .7f
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Name", weight = column1Weight)
                TableCell(text = "Value", weight = column2Weight)
            }
        }
        items(tableData) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.name, weight = column1Weight)
                TableCell(text = it.percentage.toString(), weight = column2Weight)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun Crypto(item: Cryptocurrency) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = item.name)
        Text(text = item.percentage.toString())
    }
}