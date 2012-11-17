package jp.fukayatsu.randomlauncher;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class Luncher {
	private PackageManager manager;
	private int appNumberOld = -1;

	@SuppressWarnings("unused")
	private Luncher() {
	}

	public Luncher(Context context) {
		this.manager = context.getPackageManager();
	}

	public ActivityInfo getRandomApp() {
		// アプリのリストを取得
		List<ResolveInfo> infoes = getAppList();

		// ランダムで選ぶ
		return getRandome(infoes).activityInfo;
	}

	public ActivityInfo getFirstApp() {
		// アプリのリストを取得
		List<ResolveInfo> infoes = getAppList();

		// 最初のアプリ
		return infoes.get(0).activityInfo;
	}

	private List<ResolveInfo> getAppList() {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> infoes = manager.queryIntentActivities(intent, 0);

		return infoes;
	}

	private ResolveInfo getRandome(List<ResolveInfo> infoes) {
		Random random = new Random();
		int appNumber = random.nextInt(infoes.size());
		while (appNumberOld == appNumber) {
			appNumber = random.nextInt(infoes.size());
		}
		appNumberOld = appNumber;
		return infoes.get(appNumber);
	}
}
