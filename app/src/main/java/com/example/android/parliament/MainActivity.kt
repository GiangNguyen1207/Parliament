package com.example.android.parliament

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.android.parliament.databinding.ActivityMainBinding
import com.example.android.parliament.workManager.FetchDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        delayedInit()
    }
}

private fun delayedInit() {
    CoroutineScope(Dispatchers.Default).launch {
        fetchDataAtFirst()
        setupRecurringWork()
    }
}

/*function to ask Work Manager to fetch data for the first time when the app is first launched and
there is no data in db */
private fun fetchDataAtFirst() {
    WorkManager.getInstance()
        .enqueue(OneTimeWorkRequestBuilder<FetchDataWorker>().addTag("dataFetchin").build())
}

/*function to ask Work Manager to fetch Data 1 time/day when there is Wifi or
connections with unlimited data access, when the battery is not low, when the device
is charging and when the device is in idle mode (Android 6.0 Marshmallow or higher) */
private fun setupRecurringWork() {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }
        .build()

    val repeatingRequest = PeriodicWorkRequestBuilder<FetchDataWorker>(1, TimeUnit.DAYS)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance().enqueueUniquePeriodicWork(
        FetchDataWorker.WORK_NAME,
        ExistingPeriodicWorkPolicy.KEEP,
        repeatingRequest
    )
}