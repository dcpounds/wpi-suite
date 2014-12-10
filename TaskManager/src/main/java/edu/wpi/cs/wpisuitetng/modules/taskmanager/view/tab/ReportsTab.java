package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;











import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;


/**
 * @author joe
 */
public class ReportsTab extends JScrollPane {
    static final long serialVersionUID = 2930864775768057902L;
    
    private String title;
    private ChartPanel barChart;
    
    /**
     * Constructor for NewBarChartPanel.
     * 
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
        dataSet.setValue(data.get("PINK"), "PINK", "Category");
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
}
