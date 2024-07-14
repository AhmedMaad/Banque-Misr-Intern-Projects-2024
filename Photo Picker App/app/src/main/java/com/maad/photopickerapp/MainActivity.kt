package com.maad.photopickerapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maad.photopickerapp.ui.theme.PhotoPickerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoPickerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        val context = LocalContext.current
        val prefs = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedEmail = prefs.getString("email", "")!!
        val savedPassword = prefs.getString("password", "")!!


        var email by remember { mutableStateOf(savedEmail) }
        var password by remember { mutableStateOf(savedPassword) }
        var checkBoxState by remember { mutableStateOf(true) }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.remember_me_next_time))
            Checkbox(
                checked = checkBoxState,
                onCheckedChange = { checkBoxState = it },
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(onClick = { saveCredentials(email, password, checkBoxState, context) }) {
                Text(text = stringResource(R.string.login))
            }
        }

    }
}

fun saveCredentials(email: String, pass: String, cbState: Boolean, context: Context) {
    val editor = context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit()
    if (cbState) {
        editor.putString("email", email)
        editor.putString("password", pass)
    } else {
        editor.putString("email", "")
        editor.putString("password", "")
    }
    editor.apply()
    val i = Intent(context, PictureChooserActivity::class.java)
    context.startActivity(i)
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}
