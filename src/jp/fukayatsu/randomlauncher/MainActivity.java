package jp.fukayatsu.randomlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

public class MainActivity extends Activity {
	ActivityInfo actInfo = null;
	SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		sp = SPUtil.getSahrePreferences(this);

		Luncher luncher = new Luncher(this);
		Intent intent = new Intent();

		while (true) {
			actInfo = luncher.getRandomApp();
			// sharedprefに無い、もしくは起動フラグがあればループを抜ける
			if (sp.getBoolean(actInfo.name, true)) {
				break;
			}
		}

		intent.setClassName(actInfo.packageName, actInfo.name);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, 0);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		PackageManager pm = getPackageManager();
		Bitmap bmp = ((BitmapDrawable) actInfo.loadIcon(pm)).getBitmap();

		Intent intent = new Intent(this, FeedbackActivity.class);
		intent.putExtra("KEY_ICON", bmp);
		intent.putExtra("KEY_LABEL", actInfo.loadLabel(pm));
		intent.putExtra("KEY_NAME", actInfo.name);

		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}