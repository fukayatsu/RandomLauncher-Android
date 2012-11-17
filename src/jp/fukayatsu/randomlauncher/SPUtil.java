package jp.fukayatsu.randomlauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPUtil {
	static final String PREF_LAST_ICON = "pref_last_icon";
	static final String PREF_LAST_LABEL = "pref_last_label";

	public static SharedPreferences getSahrePreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

}
