package com.athulacharya.trackn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

public class TrackWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int appWidgetId : appWidgetIds) {
            // Get stored value for this widget
            SharedPreferences pref = context.getSharedPreferences(TrackApp.DATA_FILE, Context.MODE_PRIVATE);
            int N = pref.getInt(Integer.toString(appWidgetId), -1);
            // Or start a new one
            if (N == -1) {
                N = 0;
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt(Integer.toString(appWidgetId), 0);
                editor.apply();
            }

            // Create an Intent to launch the AddNDialog
            Intent intent = new Intent(context, AddNDialogActivity.class);
            // Make it a standalone dialog
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Include the current appWidgetId
            intent.setAction(Intent.ACTION_EDIT);
            intent.setData(Uri.fromParts("awi", Integer.toString(appWidgetId), null));
            // Bundle it all up into a PendingIntent
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Get the widget layout and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.track_widget);
            views.setOnClickPendingIntent(R.id.addN, pendingIntent);
            views.setTextViewText(R.id.tracked_int, Integer.toString(N));

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static void updateWidget(int appWidgetId, Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, context, TrackWidgetProvider.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] {appWidgetId});
        context.sendBroadcast(intent);
    }

}
