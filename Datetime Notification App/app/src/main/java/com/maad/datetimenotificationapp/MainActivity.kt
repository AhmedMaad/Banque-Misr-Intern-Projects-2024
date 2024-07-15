package com.maad.datetimenotificationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maad.datetimenotificationapp.ui.theme.DatetimeNotificationAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatetimeNotificationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DateTime(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTime(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var time by remember { mutableStateOf("Choose a time") }
        var isTimePickerShown by remember { mutableStateOf(false) }
        var date by remember { mutableStateOf("Choose a date") }
        var isDatePickerShown by remember { mutableStateOf(false) }

        if (isTimePickerShown) {
            TimePickerChooser(
                onConfirm = { timeState ->
                    time = "Selected time = ${timeState.hour}:${timeState.minute}"
                    isTimePickerShown = false
                },
                onDismiss = {
                    isTimePickerShown = false
                })
        }

        if (isDatePickerShown) {
            DatePickerChooser(onConfirm = { dateState ->
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                val c = Calendar.getInstance()
                c.timeInMillis = dateState.selectedDateMillis ?: 0L
                date = "Selected date = ${dateFormatter.format(c.time)}"

                isDatePickerShown = false
            }, onDismiss = { isDatePickerShown = false })
        }

        OutlinedButton(onClick = { isTimePickerShown = true }) { Text(text = time) }
        OutlinedButton(onClick = { isDatePickerShown = true }) { Text(text = date) }
        OutlinedButton(onClick = {
            //show local notification with notification sent
        }) { Text(text = "Send notification") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerChooser(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {

    val c = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = c.get(Calendar.HOUR_OF_DAY),
        initialMinute = c.get(Calendar.MINUTE),
        is24Hour = true,
    )

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(timePickerState) }) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = stringResource(R.string.cancel)) }
        },
        text = { TimePicker(state = timePickerState) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(
    onConfirm: (DatePickerState) -> Unit,
    onDismiss: () -> Unit,
) {

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH) + 1
    val day = c.get(Calendar.DAY_OF_MONTH)
    val date = "$year/$month/$day"
    val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    val parsed = dateFormatter.parse(date)?.time ?: 0L

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = parsed,
        yearRange = (2024..2025),
    )

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = stringResource(R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = stringResource(R.string.cancel)) }
        },
        text = { DatePicker(state = datePickerState) }
    )
}

@Preview(showBackground = true)
@Composable
fun DateTimePreview() {
    DatetimeNotificationAppTheme {
        DateTime()
    }
}