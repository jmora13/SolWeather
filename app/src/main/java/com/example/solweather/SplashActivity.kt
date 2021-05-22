package com.example.solweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.solweather.Workers.NetworkWorker
import com.example.solweather.api.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class SplashActivity : AppCompatActivity() {

    private val workManager = WorkManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getNetworkData()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getNetworkData(){
        workManager.enqueue(OneTimeWorkRequest.from(NetworkWorker::class.java))
    }

}