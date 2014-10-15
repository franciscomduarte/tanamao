package br.com.tnm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	private static final String[] materias = 
		{"Administração (345)","Informática (10.250)",
		 "Direito Administrativo (2.221)","Direito Constitucional (1.298)"};
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materias));
		
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		switch (position){
		
		case 0:
			startActivity(new Intent(this, Pesquisa.class));
		break;
		case 1:
			startActivity(new Intent(this, Pesquisa.class));
		break;
		case 2:
			startActivity(new Intent(this, Pesquisa.class));
		break;
		default:
			finish();
		}
	}

}


