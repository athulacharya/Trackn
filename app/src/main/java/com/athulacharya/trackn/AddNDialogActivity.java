package com.athulacharya.trackn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AddNDialogActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_addn);
    }

    public void cancel(View view) {
        finish();
    }

    public void add(View view) {
        System.out.println("ok!");
        finish();
    }
}
