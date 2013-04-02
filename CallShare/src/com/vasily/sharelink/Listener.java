package com.vasily.sharelink;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent; 

public class Listener extends Activity {
 
	 	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			String url = getIntent().getDataString(); 
			Intent i = new Intent(android.content.Intent.ACTION_SEND);
			i.putExtra(android.content.Intent.EXTRA_TEXT, url);
			i.setType("text/plain");
			startActivity(Intent.createChooser(i, "Share..."));
			finish();
		}

	}
