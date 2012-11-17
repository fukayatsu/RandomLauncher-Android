package jp.fukayatsu.randomlauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends Activity {
	ImageView last_icon;
	TextView last_label;
	String last_package_name;

	Button btn_finish, btn_next, btn_setting;
	CheckBox cbx_never;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);

		sp = SPUtil.getSahrePreferences(this);

		last_icon = (ImageView) findViewById(R.id.last_icon);
		last_label = (TextView) findViewById(R.id.last_label);
		btn_finish = (Button) findViewById(R.id.btn_finish);
		btn_next = (Button) findViewById(R.id.btn_next);
		btn_setting = (Button) findViewById(R.id.btn_setting);
		cbx_never = (CheckBox) findViewById(R.id.cbx_never);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			last_icon.setImageBitmap((Bitmap) bundle.get("KEY_ICON"));
			last_label.setText(bundle.getCharSequence("KEY_LABEL"));
			last_package_name = bundle.getString("KEY_NAME");
		} else {
			finish();
		}

		btn_finish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(FeedbackActivity.this,
						SplashActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btn_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FeedbackActivity.this, "すみません、まだ実装していません。",
						Toast.LENGTH_SHORT).show();
			}
		});

		cbx_never.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean check) {
				if (check) {
					sp.edit().putBoolean(last_package_name, false).commit();
				} else {
					sp.edit().putBoolean(last_package_name, true).commit();
				}
			}
		});
	}
}
