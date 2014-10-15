package br.com.tnm;

import android.app.Activity;
import android.view.View;

public abstract class ActivityComActionBarVoltar extends Activity {


	public void onClickMenuBack(View arg0) {

		onBackPressed();
	}


	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(0, 0);
	}
}
