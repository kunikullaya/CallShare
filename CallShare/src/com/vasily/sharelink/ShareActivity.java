package com.vasily.sharelink;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent; 

public class ShareActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + "com.vasily.sharelink")));
        finish();
    }
 
}
