package br.com.tnm;

import br.com.tnm.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

public class MenuPrincipal extends TabActivity implements OnTabChangeListener, TabContentFactory{
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setTitle(R.string.titulo_menu);
		
		TabHost tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);
		tabHost.setBackgroundColor(getResources().getColor(R.color.branco));
		TabSpec tab1 = tabHost.newTabSpec("Tab 1");
		
		tab1.setIndicator("Início", getResources().getDrawable(R.drawable.tab_home));
		tab1.setContent(new Intent(getApplicationContext(),Home.class));
		tabHost.addTab(tab1);
		
		
		TabSpec tab2 = tabHost.newTabSpec("Tab 2");
		
		tab2.setIndicator("Sobre", getResources().getDrawable(R.drawable.info_icon));
		Intent it = new Intent(getApplicationContext(),Sobre.class);
		tab2.setContent(it);
		tabHost.addTab(tab2);
		
	}

	public View createTabContent(String tagId) {
		TextView tv = new TextView(this);
		tv.setText("Utilizando factory para criação de TAB "+tagId);
		return tv;
	
	}

	public void onTabChanged(String tabId) {
		
	}
	
}
