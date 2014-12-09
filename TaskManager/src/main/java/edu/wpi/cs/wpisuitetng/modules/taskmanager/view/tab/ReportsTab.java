package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementStatus;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.Iteration;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.iterations.IterationModel;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.overview.OverviewBarButton;

/**
 * @author justinhess
 * @version $Revision: 1.0 $
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
        OverviewBarButton buttons = new OverviewBarButton();
        panel.add(barChart, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        
        this.setViewportView(panel);
    }
    
    /**
     * @return the data set depending on the type of data called for either
     *         status or iteration
     */
    private CategoryDataset setData() {
        if (title.equals("Iteration")) {
            return setDataIteration();
        } else if (title.equals("Status")) {
            return setDataStatus();
        } else {
            return setDataAssignTo();
        }
        
    }
    
    /**
     * @return the dataset based upon the statuses of all requirements
     */
    private static CategoryDataset setDataStatus() {
        int numStatusNew = 0;
        int numStatusDeleted = 0;
        int numStatusInprogress = 0;
        int numStatusComplete = 0;
        int numStatusOpen = 0;
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        List<Requirement> requirements = RequirementModel.getInstance().getRequirements();// list of requirements
        for (int i = 0; i < requirements.size(); i++) {
            if (requirements.get(i).getStatus() == RequirementStatus.NEW) {
                numStatusNew += 1;
            } else if (requirements.get(i).getStatus() == RequirementStatus.DELETED) {
                numStatusDeleted += 1;
            } else if (requirements.get(i).getStatus() == RequirementStatus.INPROGRESS) {
                numStatusInprogress += 1;
            } else if (requirements.get(i).getStatus() == RequirementStatus.COMPLETE) {
                numStatusComplete += 1;
            } else {
                numStatusOpen += 1;
            }
        }
        dataSet.setValue(numStatusNew, "New", "Status");
        dataSet.setValue(numStatusDeleted, "Deleted", "Status");
        dataSet.setValue(numStatusInprogress, "In Progress", "Status");
        dataSet.setValue(numStatusComplete, "Complete", "Status");
        dataSet.setValue(numStatusOpen, "Open", "Status");
        return dataSet;
    }
    
    /**
     * @return the data of iterations to be displayed by the bar chart
     */
    private static CategoryDataset setDataIteration() {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        List<Iteration> iterations = IterationModel.getInstance()
                .getIterations();// list of iterations
        List<Requirement> requirements = RequirementModel.getInstance()
                .getRequirements();// list of requirements
        int[] iterationNum = new int[iterations.size()];
        for (int i = 0; i < iterations.size(); i++) {
            for (int j = 0; j < requirements.size(); j++) {
                if (requirements.get(j).getIteration().toString()
                        .equals(iterations.get(i).toString())) {
                    iterationNum[i]++;// increments the number if the requiremet
                                      // belongs to the given iteration
                }
            }
        }
        for (int k = 0; k < iterationNum.length; k++) {
            dataSet.setValue(iterationNum[k], iterations.get(k).toString(), "Iteration");// sets
            // the
            // data
        }
        return dataSet;
    }
    
    /**
     * @return the data of the number of requirements a user has assigned to
     *         them
     */
    private static CategoryDataset setDataAssignTo() {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        ArrayList<String> userNames = new ArrayList<String>();
        
        List<Requirement> requirements = RequirementModel.getInstance()
                .getRequirements();// list of requirements
        for (int i = 0; i < requirements.size(); i++) {
            List<String> users = requirements.get(i).getAssignedTo();// list of
                                                                     // users
                                                                     // for
                                                                     // specific
                                                                     // requirement
            for (int j = 0; j < users.size(); j++) {
                if (!userNames.contains(users.get(j))) {
                    userNames.add(users.get(j));// populate a list of all users
                }
            }
            
        }
        int[] numReqAssigned = new int[userNames.size()];
        for (int i = 0; i < requirements.size(); i++) {
            List<String> users = requirements.get(i).getAssignedTo();
            for (int j = 0; j < userNames.size(); j++) {
                if (users.contains(userNames.get(j))) {
                    numReqAssigned[j]++;// count the number of requirements a
                                        // user has assigned to them
                }
            }
        }
        for (int k = 0; k < userNames.size(); k++) {
            dataSet.setValue(numReqAssigned[k], userNames.get(k), "Assigned To");// populate
            // bar chart
            // data
        }
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
