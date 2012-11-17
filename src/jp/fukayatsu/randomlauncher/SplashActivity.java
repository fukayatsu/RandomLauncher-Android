package jp.fukayatsu.randomlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onResume();
		Intent intent = new Intent(getApplication(), MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onResume() {

	}

}
