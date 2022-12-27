package com.burakdemir.hijricalendar

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter

/**
 * Implementation of App Widget functionality.
 */
class BasicAppWidget : AppWidgetProvider() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@RequiresApi(Build.VERSION_CODES.O)
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val localDate: LocalDate = LocalDate.now(ZoneId.of("Turkey"))

    // convert to hijrah
    val hijrahDate: HijrahDate = HijrahDate.from(localDate)

    // format to DD/MM/YYYY
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formatted: String = formatter.format(hijrahDate) // 16/02/1439

    val widgetText = formatted.subSequence(0,2)

    val intent = Intent(context,MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context,0,intent, FLAG_IMMUTABLE)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.basic_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)
    println("hello $widgetText")
    views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}