package com.example.activity_navigation_example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.activity_navigation_example.ui.theme.ActivityNavigationExampleTheme

class ActivityA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val inputData = intent.getStringExtra("INPUT_KEY")

        setContent {
            val result = stringResource(R.string.result_from_a)

            ActivityNavigationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        modifier = Modifier.padding(innerPadding),
                        inputData = inputData ?: ""
                    ) {
                        val resultIntent = Intent().apply {
                            putExtra("RESULT_KEY", result)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting2(
    modifier: Modifier = Modifier,
    inputData: String = "",
    onClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.current_a))
        TextButton(onClick = onClick) {
            Text(stringResource(R.string.send_result))
        }
        HorizontalDivider()
        Text("Input data from MainActivity: $inputData")
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview2() {
    ActivityNavigationExampleTheme {
        Greeting2() {}
    }
}