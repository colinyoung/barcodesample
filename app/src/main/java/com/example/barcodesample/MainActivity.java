package com.example.barcodesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Would not be in plugin - this button is just for testing
    public void onClick(View v) {
        this.openIntent(null, null);
    }

    // Start copying here!
    // vvvvv

    // This callback class needs to be the one from React, but I don't
    // have that imported here.
    Callback mErrorCallback;
    Callback mSuccessCallback;


    // TODO You'd uncomment this annotation (@ReactMethod) to make the ZXing intent open.
    // @ReactMethod
    public void openIntent(Callback errorCallback, Callback successCallback) {
        mErrorCallback = errorCallback;
        mSuccessCallback = successCallback;
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

                // TODO Uncomment
                // mErrorCallback.invoke("Error");
            } else {
                // Contents of scanned item are in result.getContents()
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // Instead of toast you'd obviously respond back:
                // TODO Uncomment
                // mSuccessCallback.invoke(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
