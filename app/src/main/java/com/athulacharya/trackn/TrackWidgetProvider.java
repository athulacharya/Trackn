package com.athulacharya.trackn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;

public class TrackWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            System.out.println("dafuqdoe"); //TODO: This works, what now?
            // Create an Intent to launch the AddNDialog
            Intent intent = new Intent(context, AddNDialogFragment.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the widget layout and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.track_widget);
            views.setOnClickPendingIntent(R.id.addN, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
