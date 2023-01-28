// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import jdk.jfr.Enabled

class AppState {
    var text by mutableStateOf("")
    // fun isButtonEnabled() = text.isNotEmpty()
    val buttonEnabled: Boolean
        get() = text.isNotEmpty()
}

@Composable
@Preview
fun App(appState: AppState) {

    MaterialTheme {
        Column {
            TextField(
                value = appState.text,
                onValueChange = { nextText ->
                    appState.text = nextText
                }
            )
            Text(text = buildMessage(message = appState.text))
            ClearButton(
                //buttonEnable = appState.isButtonEnabled(),
                buttonEnable = appState.buttonEnabled,
                onClick = { appState.text = "" }
            )
        }

    }
}

@Composable
fun ClearButton(buttonEnable: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = buttonEnable) {
        Text("Clear")
    }
}

fun buildMessage(message: String) = "Hello $message"

fun main() {

    val appState = AppState()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Notes App"
        ) {
            App(appState)
        }
    }
}
