package com.burakdemir.hijricalendar

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val localDate: LocalDate = LocalDate.now(ZoneId.of("Turkey"))

        // convert to hijrah
        val hijrahDate: HijrahDate = HijrahDate.from(localDate)

        // format to DD/MM/YYYY
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatted: String = formatter.format(hijrahDate) // 16/02/1439

        date.text = formatted.subSequence(0,2)

    }
}