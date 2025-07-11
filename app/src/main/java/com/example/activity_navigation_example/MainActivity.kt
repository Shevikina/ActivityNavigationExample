package com.example.activity_navigation_example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.activity_navigation_example.ui.theme.ActivityNavigationExampleTheme

class MainActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val state = mutableStateOf("")

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val returnedData = data?.getStringExtra("RESULT_KEY")
                state.value = returnedData ?: ""
            }
        }

        setContent {
            val inputValue = stringResource(R.string.hello_from_main)

            ActivityNavigationExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding),
                        result = state.value
                    ) {
                        val intent = Intent(this, ActivityA::class.java).apply {
                            putExtra("INPUT_KEY", inputValue)
                        }
                        resultLauncher.launch(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    result: String = "",
    onClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.current_main))
        TextButton(onClick = onClick) {
            Text(stringResource(R.string.go_to_a))
        }
        HorizontalDivider()
        Text("Result from ActivityA: $result")
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ActivityNavigationExampleTheme {
        Greeting(Modifier) {}
    }
}