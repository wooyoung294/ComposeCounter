package com.example.ComposeCounter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ComposeCounter.ui.theme.ComposeCounterTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCounterTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .semantics { testTagsAsResourceId = true } // ← testTag를 resource-id로 노출
                ) {
                    CounterScreen(Modifier.align(Alignment.Center))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme {
        Scaffold { pad ->
            Box(Modifier.fillMaxSize().padding(pad)) {
                CounterScreen(Modifier.align(Alignment.Center))
            }
        }
    }
}


@Composable
fun CounterScreen(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }


    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            fontSize = 96.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .testTag("counter_value")
                .semantics { contentDescription = "counter.value" }
        )
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = { count-- },
                modifier = Modifier
                    .testTag("counter_decrement")
                    .semantics { contentDescription = "counter.decrement" }
            ) { Text("-1") }


            Button(
                onClick = { count++ },
                modifier = Modifier
                    .testTag("counter_increment")
                    .semantics { contentDescription = "counter.increment" }
            ) { Text("+1") }
        }
        Spacer(Modifier.height(8.dp))
    }
}
