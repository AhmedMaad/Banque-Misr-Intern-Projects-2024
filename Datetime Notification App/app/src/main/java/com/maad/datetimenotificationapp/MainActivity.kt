package com.maad.datetimenotificationapp

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import androidx.core.content.ContextCompat.getSystemService
import com.maad.datetimenotificationapp.ui.theme.DatetimeNotificationAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.min

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
        Log.d("trace", "Recomposition")
        var timeButton by remember { mutableStateOf("Choose a time") }
        var isTimePickerShown by remember { mutableStateOf(false) }
        var dateButton by remember { mutableStateOf("Choose a date") }
        var isDatePickerShown by remember { mutableStateOf(false) }
        val context = LocalContext.current
        var year by remember { mutableIntStateOf(0) }
        var month by remember { mutableIntStateOf(0) }
        var day by remember { mutableIntStateOf(0) }
        var hour by remember { mutableIntStateOf(0) }
        var minute by remember { mutableIntStateOf(0) }

        if (isTimePickerShown) {
            TimePickerChooser(
                onConfirm = { timeState ->
                    hour = timeState.hour
                    minute = timeState.minute
                    timeButton = "$hour:$minute"
                    isTimePickerShown = false
                },
                onDismiss = { isTimePickerShown = false })
        }

        if (isDatePickerShown) {
            DatePickerChooser(onConfirm = { dateState ->
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                val c = Calendar.getInstance()
                //Ex: time in millis = 1720483200000
                c.timeInMillis = dateState.selectedDateMillis ?: 0L
                //Ex: c.time = Tue Jul 09 03:00:00 GMT+03:00 2024 --> date formatter = 09-07-2024
                dateButton = dateFormatter.format(c.time)
                year =
                    SimpleDateFormat("yyyy", Locale.US).format(dateFormatter.parse(dateButton)!!)
                        .toInt()
                month =
                    SimpleDateFormat("MM", Locale.US).format(dateFormatter.parse(dateButton)!!)
                        .toInt()
                day =
                    SimpleDateFormat("dd", Locale.US).format(dateFormatter.parse(dateButton)!!)
                        .toInt()
                isDatePickerShown = false
            }, onDismiss = { isDatePickerShown = false })
        }

        OutlinedButton(onClick = { isTimePickerShown = true }) { Text(text = timeButton) }
        OutlinedButton(onClick = { isDatePickerShown = true }) { Text(text = dateButton) }
        OutlinedButton(onClick = {
            sendNotification(
                context,
                "Notification Scheduled",
                "Your notification is scheduled on $dateButton $timeButton"
            )
            //Log.d("trace", "date: $year, $month, $day, $hour, $minute")
            scheduleNotification(context, year, month, day, hour, minute)
        }) { Text(text = "Send notification") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerChooser(onConfirm: (TimePickerState) -> Unit, onDismiss: () -> Unit) {

    //val c = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        //All of them are optionals to pass (Extra)
        /*
        initialHour = c.get(Calendar.HOUR_OF_DAY),
        initialMinute = c.get(Calendar.MINUTE),
        */
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
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {

    /*
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    //Months are zero base (e.g. January = 0 & December = 11)
    val month = c.get(Calendar.MONTH) + 1
    val day = c.get(Calendar.DAY_OF_MONTH)
    val date = "$year/$month/$day"
    val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    val parsed = dateFormatter.parse(date)?.time ?: 0L
    */

    val datePickerState = rememberDatePickerState(
        //initialSelectedDateMillis = parsed,
        //yearRange = (2024..2025),
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
        text = { DatePicker(state = datePickerState) },
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
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

fun sendNotification(context: Context, title: String, text: String) {
    val builder = NotificationCompat.Builder(context, "1")
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(title)
        .setContentText(text)
        .setAutoCancel(true)

    /*
    You can request permissions at runtime using this code, or grant it manually
    by hold click the app icon -> choose App Info  --> Permissions --> Notifications
    */

    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        )
        != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat
            .requestPermissions(
                context.getActivityOrNull()!!,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                5
            )
    } else*/

    //To make the notification appear
    NotificationManagerCompat.from(context).notify(99, builder.build())
}

/*
fun Context.getActivityOrNull(): Activity? {
    var context = this
    //a ContextWrapper provides a flexible way to adapt and customize the behavior
    //of a Context object without directly modifying its underlying implementation
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext //https://stackoverflow.com/a/42210910/10413818
    }

    return null
}
*/

fun scheduleNotification(
    context: Context,
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
) {
    val intent = Intent(context, NotificationReceiver::class.java)
    intent.putExtra("title", "New notification")
    intent.putExtra("text", "Your notification has arrived successfully!")

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        200, //used in later points for cancelling
        intent,
        //If you have an exact action, use "FLAG_IMMUTABLE" that cannot be modified by the app
        //A pending intent can always update by passing the flag "FLAG_UPDATE_CURRENT"
        //Read more: https://medium.com/androiddevelopers/all-about-pendingintents-748c8eb8619
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val calendar = Calendar.getInstance()
    calendar.set(year, month-1, day, hour, minute, 0)
    Log.d("trace", "time scheduled: ${calendar.time}")

    try {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    } catch (e: SecurityException) {
        Log.d("trace", "Error: ${e.message}")
    }

}

@Preview(showBackground = true)
@Composable
fun DateTimePreview() {
    DatetimeNotificationAppTheme {
        DateTime()
    }
}