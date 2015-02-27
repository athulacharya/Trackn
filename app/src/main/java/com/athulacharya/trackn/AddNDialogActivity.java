package com.athulacharya.trackn;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class AddNDialogActivity extends Activity {

    private int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_addn);

        // Get AppWidgetId for the calling widget
        Intent intent = getIntent();
        appWidgetId = Integer.parseInt(intent.getData().getSchemeSpecificPart());
    }

    public void cancel(View view) {
        finish();
    }

    public void add(View view) {
        // Get stored value for this widget
        SharedPreferences pref = getSharedPreferences(TrackApp.DATA_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int N = pref.getInt(Integer.toString(appWidgetId), -1);
        if (N == -1) {
            // This should never happen, but if it does, just start a new entry
            N = 0;
            editor.putInt(Integer.toString(appWidgetId), 0);
            editor.apply();
        }

        // Get the entered +N
        TextView editText = (TextView) findViewById(R.id.N_to_add);
        String input = editText.getText().toString();
        int plusN;
        if (input.equals(""))
            plusN = 0;
        else
            plusN = Integer.parseInt(input);
        editor.putInt(Integer.toString(appWidgetId), N + plusN);
        editor.commit();

        // Tell the widget to update and bounce
        TrackWidgetProvider.updateWidget(appWidgetId, this);
        finish();
    }
}
