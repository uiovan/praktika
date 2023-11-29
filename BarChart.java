import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import javax.swing.*;
import java.util.List;

public class BarChart extends JFrame {

    public BarChart(String applicationTitle , String chartTitle, List<String> count) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Year",
                "Count",
                createDataset(count),
                PlotOrientation.HORIZONTAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(List<String> count) {
        final String c = "count";
        final String year;
        int var;

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < count.size(); i++){
            String[] s = count.get(i).split(" - ");
            var = Integer.parseInt(s[1]);
            dataset.addValue(var, c, s[0]);
        }
        return dataset;
    }

}