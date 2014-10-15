/**
 * Copyright (C) 2009, 2010 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.tnm.view;

import java.util.HashMap;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import br.com.tnm.dao.SimuladoDaoImpl;

/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {

	private SimuladoDaoImpl sDao;

	/**
	 * Returns the chart name.
	 * 
	 * @return the chart name
	 */
	public String getName() {
		return "Budget chart";
	}

	/**
	 * Returns the chart description.
	 * 
	 * @return the chart description
	 */
	public String getDesc() {
		return "The budget per project for this year (pie chart)";
	}

	/**
	 * Executes the chart demo.
	 * 
	 * @param context
	 *            the context
	 * @return the built intent
	 */

	public final Intent execute(Context context, Long idSimulado) {

		sDao = new SimuladoDaoImpl();
		HashMap<String, String> valores = sDao.mapaSimulado(context, idSimulado);

		String[] values = new String[2];
		values[0] = valores.get("acertos");
		values[1] = valores.get("erros");
		int[] colors = new int[] { Color.GREEN, Color.RED };
		DefaultRenderer renderer = buildCategoryRenderer(colors);
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		renderer.setChartTitleTextSize(20);
		return ChartFactory.getPieChartIntent(context, buildCategoryDataset(valores.get("nome_simulado"), values), renderer, "GRÁFICO >>");
	}
	
	  protected CategorySeries buildCategoryDataset(String title, String[] values) {
	    CategorySeries series = new CategorySeries(title);
	    Integer acertos = Integer.valueOf(values[0]);
	    Integer erros = Integer.valueOf(values[1]);
	    series.add("Acertos: " + acertos, acertos);
	    series.add("Erros: " + erros, erros);
	    return series;
	  }

}
