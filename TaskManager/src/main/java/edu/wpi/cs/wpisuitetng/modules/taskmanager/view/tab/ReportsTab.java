/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.util.Hashtable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;


/**
 * @author joe
 * The view that contains task reports
 */
public class ReportsTab extends JScrollPane implements IHashableTab {
    static final long serialVersionUID = 2930864775768057902L;
    
    private String title;
    private ChartPanel barChart;
    
   
    /**
     * Constructor for NewBarChartPanel.
     * @param title String
     */
    public ReportsTab(String title) {
        this.title = title;//title of the chart, either status or iteration
        JPanel panel = new JPanel(new BorderLayout());
        barChart = createPanel();
        panel.add(barChart, BorderLayout.CENTER);
        
        this.setViewportView(panel);
    }
    
    /**
     * @return the data set depending on the type of data called for either
     *         status or iteration
     */
    private CategoryDataset setData() {
        return setDataCategory();   
    }
    
 
    /**
     * @return the dataSet based upon the statuses of all requirements
     */
    private static CategoryDataset setDataCategory() {

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        Hashtable<String, Integer> data = DataLoggerController.getDataModel().exportAllCategories();

        
        dataSet.setValue(data.get("GRAY"), "Gray", "Category");
        dataSet.setValue(data.get("WHITE"), "White", "Category");
        dataSet.setValue(data.get("BROWN"), "Brown", "Category");
        dataSet.setValue(data.get("RED"), "Red", "Category");
        dataSet.setValue(data.get("PINK"), "Pink", "Category");
        dataSet.setValue(data.get("ORANGE"), "Orange", "Category");
        dataSet.setValue(data.get("YELLOW"), "Yellow", "Category");
        dataSet.setValue(data.get("GREEN"), "Green", "Category");
        dataSet.setValue(data.get("BLUE"), "Blue", "Category");
        dataSet.setValue(data.get("PURPLE"), "Purple", "Category");

        return dataSet;
    }

    
    /**
     * @param dataset the data to be shown by the chart
     * @param title the title of the chart(either status or iteration) @return
     *            the created bar graph
     */
    private static JFreeChart createChart(CategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,         // chart title
                "Name",               // domain axis label
                "Value",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL, // orientation
                true,                     // include legend
                true,
                false
                );
        
        
        
        
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gray = new GradientPaint(
            0.0f, 0.0f, Color.gray, 
            0.0f, 0.0f, Color.white
        );
        final GradientPaint white = new GradientPaint(
            0.0f, 0.0f, Color.white, 
            0.0f, 0.0f, Color.white
        );
        final GradientPaint brown = new GradientPaint(
            0.0f, 0.0f, new Color(0xA67C52), 
            0.0f, 0.0f, Color.white
        );
        final GradientPaint red = new GradientPaint(
                0.0f, 0.0f, Color.red, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint pink = new GradientPaint(
                0.0f, 0.0f, Color.pink, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint orange = new GradientPaint(
                0.0f, 0.0f, Color.orange, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint yellow = new GradientPaint(
                0.0f, 0.0f, Color.yellow, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint green = new GradientPaint(
                0.0f, 0.0f, Color.green, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint blue = new GradientPaint(
                0.0f, 0.0f, Color.blue, 
                0.0f, 0.0f, Color.white
            );
        final GradientPaint purple = new GradientPaint(
                0.0f, 0.0f,  new Color(0xA187BE), 
                0.0f, 0.0f, Color.white
            );
        
        renderer.setSeriesPaint(0, gray);
        renderer.setSeriesPaint(1, white);
        renderer.setSeriesPaint(2, brown);
        renderer.setSeriesPaint(3, red);
        renderer.setSeriesPaint(4, pink);
        renderer.setSeriesPaint(5, orange);
        renderer.setSeriesPaint(6, yellow);
        renderer.setSeriesPaint(7, green);
        renderer.setSeriesPaint(8, blue);
        renderer.setSeriesPaint(9, purple);
        
        return chart;
    }
    
    /**
     * @return the created bar graph
     **/
    public ChartPanel createPanel() {
        JFreeChart chart = createChart(setData(), title);
        
        return new ChartPanel(chart);
    }
    
    /**
     * Method paintComponent.
     * 
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        barChart.setChart(createChart(setData(), title));
        super.paintComponent(g);
    }
    
    //Getters and setters
    
    /**
     * @return the barChart
     */
    ChartPanel getBarChart() {
        return this.barChart;
    }
    
    /**
     * @param barChart the barChart to set
     */
    void setBarChart(ChartPanel barChart) {
        this.barChart = barChart;
    }
    
    /**
     * @return the title of the chart
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param title the title to set
     */
    void setTitle(String title) {
        this.title = title;
    }

	@Override
	public int getModelID() {
		return 0;
	}

	@Override
	public TabType getTabType() {
		// TODO Auto-generated method stub
		return TabType.REPORTS;
	}

	/* (non-Javadoc)
	 * @see edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab.IHashableTab#hasBeenModified()
	 */
	@Override
	public boolean hasBeenModified() {
		return false;
	}
}
