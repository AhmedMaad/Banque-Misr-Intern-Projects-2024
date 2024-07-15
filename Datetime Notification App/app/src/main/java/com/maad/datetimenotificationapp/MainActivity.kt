package com.maad.datetimenotificationapp

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
                    //You must create the notification channel before posting any notifications
                    createNotificationChannel(LocalContext.current)
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
        val context = LocalContext.current

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
            sendConfirmationNotification(
                context,
                date,
                time
            )
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

// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is not in the Support Library
private fun createNotificationChannel(context: Context) {
    val name = "Datetime"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel("1", name, importance)
    channel.description = "Datetime Scheduled Notification"
    // Register the channel with the system
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

fun sendConfirmationNotification(context: Context, date: String, time: String) {
    val builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Notification Scheduled")
        .setContentText("Your notification is scheduled on $date $time")
        .setAutoCancel(true)
    //To make the notification appear
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                .requestPermissions(context.getActivityOrNull()!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 5)
        }
    else
        NotificationManagerCompat.from(context).notify(99, builder.build())
}

fun Context.getActivityOrNull(): Activity? {
    var context = this
    //a ContextWrapper provides a flexible way to adapt and customize the behavior
    //of a Context object without directly modifying its underlying implementation
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }

    return null
}

@Preview(showBackground = true)
@Composable
fun DateTimePreview() {
    DatetimeNotificationAppTheme {
        DateTime()
    }
}