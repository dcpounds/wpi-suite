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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import net.miginfocom.swing.MigLayout;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.TabController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.WorkflowController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.controller.datalogger.DataLoggerController;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.DateLabelFormatter;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.StageModel;
import edu.wpi.cs.wpisuitetng.modules.taskmanager.model.WorkflowModel;


/**
 * @author joe
 * The view that contains task reports
 */
public class ReportsTab extends JScrollPane implements IHashableTab, MouseListener, ActionListener, KeyListener {
    static final long serialVersionUID = 2930864775768057902L;
    
    private final WorkflowModel workflowModel;
    
    private String title;
    private ChartPanel barChart;
    private ChartPanel velocityChart;
    private Date startDate;
    private Date endDate;
    private Boolean devMode;
    private Action toggleDev;
    private int panelIndex;

	private UtilDateModel dateModel1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	private UtilDateModel dateModel2;
	private JDatePanelImpl datePanel2;
	private JDatePickerImpl datePicker2;	
	private UtilDateModel dateModel3;
	private JDatePanelImpl datePanel3;
	private JDatePickerImpl datePicker3;
	private JPanel panel;
    
   
    /**
     * Constructor for NewBarChartPanel.
     * @param title String
     */
    public ReportsTab(String title) {
        this.workflowModel = WorkflowController.getWorkflowModel();
        this.panelIndex = 0;
        buildPanel(title);
    }
    
    public void updateStartDate()
    {
    	String daysString = Integer.toString(datePicker1.getModel().getDay());
    	String monthString = Integer.toString(datePicker1.getModel().getMonth()+1);
    	String yearString = Integer.toString(datePicker1.getModel().getYear());

	    try {
			Date StartDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearString+"-"+monthString+"-"+daysString);
			WorkflowController.getWorkflowModel().setStartDate(StartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }   
    
    public void updateEndDate()
    {
    	String daysString = Integer.toString(datePicker2.getModel().getDay());
    	String monthString = Integer.toString(datePicker2.getModel().getMonth()+1);
    	String yearString = Integer.toString(datePicker2.getModel().getYear());

	    try {
			Date EndDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearString+"-"+monthString+"-"+daysString);
			WorkflowController.getWorkflowModel().setEndDate(EndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void updateOverrideDate()
    {
    	String daysString = Integer.toString(datePicker3.getModel().getDay());
    	String monthString = Integer.toString(datePicker3.getModel().getMonth()+1);
    	String yearString = Integer.toString(datePicker3.getModel().getYear());

	    try {
			Date OverrideDate = new SimpleDateFormat("yyyy-MM-dd").parse(yearString+"-"+monthString+"-"+daysString);
			WorkflowController.getWorkflowModel().setOverrideDate(OverrideDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void buildPanel(String title)
    {
    	this.title = title;//title of the chart, either status or iteration
        panel = new JPanel(new BorderLayout());
        if (panelIndex == 0)
        {
        	barChart = createPanel();
        	panel.add(barChart, BorderLayout.WEST);
        }
        
        else
        {
        	velocityChart = createPanel();

        	
        	
            final XYDataset dataset = createVelocityDataset();
            final JFreeChart chart = createVelocityChart(dataset);
            velocityChart = new ChartPanel(chart);
       		panel.add(velocityChart, BorderLayout.WEST);
        }
        

        
        JPanel rightPanel = (new JPanel(new MigLayout("wrap 4")));
        
        JLabel top = new JLabel("Choose the time period for your report");

        JLabel start = new JLabel("Select Starting Date");
        JLabel end = new JLabel("Select Ending Date");
        JButton button1 = new JButton("Task Distribution");
        JButton button2 = new JButton("Velocity Chart");
        
        
        button2.addActionListener( new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {

        	}
        });

        JButton button3 = new JButton("Scrum Burndown");
        JButton button4 = new JButton("Category Distribution");
        JLabel bottom = new JLabel("Select \"Task Completed\" Stage");
        JComboBox<String> dropDown = new JComboBox<String>();
        dropDown.setModel(new DefaultComboBoxModel<String>(getStages()));
        
        dateModel1 = new UtilDateModel();

		Date Bufferdate = WorkflowController.getWorkflowModel().getStartDate();
		dateModel1.setValue(Bufferdate);
			

        
        
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(dateModel1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		

		datePicker1.addMouseListener(this);
		datePanel1.addMouseListener(this);
		datePicker1.addActionListener(this);
		
		
		
        dateModel2 = new UtilDateModel();
        

		Bufferdate = WorkflowController.getWorkflowModel().getEndDate();
		dateModel2.setValue(Bufferdate);

		
		Properties q = new Properties();
		q.put("text.today", "Today");
		q.put("text.month", "Month");
		q.put("text.year", "Year");
		datePanel2 = new JDatePanelImpl(dateModel2, q);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		

		datePicker2.addMouseListener(this);
		datePanel2.addMouseListener(this);
		datePicker2.addActionListener(this);
		
		
		

        dateModel3 = new UtilDateModel();
        

		Bufferdate = WorkflowController.getWorkflowModel().getOverrideDate();
		dateModel3.setValue(Bufferdate);

		
		Properties r = new Properties();
		r.put("text.today", "Today");
		r.put("text.month", "Month");
		r.put("text.year", "Year");
		datePanel3 = new JDatePanelImpl(dateModel3, r);
		datePicker3 = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
		

		datePicker3.addMouseListener(this);
		datePanel3.addMouseListener(this);
		datePicker3.addActionListener(this);
        
		
		
        
        rightPanel.add(top, "span 4");
        rightPanel.add(start, "span 2");
        rightPanel.add(end, "span 2");
		rightPanel.add(datePicker1, "span 2");
        //rightPanel.add(cal1, "span 2");
		rightPanel.add(datePicker2, "span 2");
        //rightPanel.add(cal2, "span 2");
 
        rightPanel.add(button1);
        rightPanel.add(button2);
        rightPanel.add(button3);
        rightPanel.add(button4);
        rightPanel.add(bottom, "span 2");
        rightPanel.add(dropDown, "span 2");
        rightPanel.add(datePicker3, "span 4");
        datePicker3.setVisible(true);

        
        

        
        
        
        panel.add(rightPanel, BorderLayout.CENTER);
        
        
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
    
    
	private static XYDataset createVelocityDataset() {
		final XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);

        final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        return dataset;
	}
	
	
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createVelocityChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Line Chart Demo 6",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
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
        return barChart;
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
		// TODO Auto-generated method stub
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
	
	private String[] getStages() {
		ArrayList<String> statusOptions = new ArrayList<String>();
		for (StageModel stage : workflowModel.getStageModelList().values()) {
			String truncatedTitle;
			if (stage.getTitle().length() >= 21)
				truncatedTitle = stage.getTitle().substring(0, 21) + "...";
			else
				truncatedTitle = stage.getTitle();
			statusOptions.add(truncatedTitle);
		}
		return statusOptions.toArray(new String[statusOptions.size()]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateStartDate();
		updateEndDate();
		updateOverrideDate();
		
		buildPanel(title);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.requestFocusInWindow();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		if (e.getKeyChar() == 'e')
		{

			devMode = !devMode;
			datePanel3.setVisible(devMode);

		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void switchDev() {
		devMode = !devMode;
		datePanel3.setVisible(devMode);
	}

	/**
	 * @return the panelIndex
	 */
	public int getPanelIndex() {
		return panelIndex;
	}

	/**
	 * @param panelIndex the panelIndex to set
	 */
	public void setPanelIndex(int panelIndex) {
		this.panelIndex = panelIndex;
	}
	
	
	
}
