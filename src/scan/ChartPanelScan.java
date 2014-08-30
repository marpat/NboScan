/*
 * ChartPanelScan.java, part of the NboScan project
 */
package scan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.ShapeUtilities;

/**
 * ChartPanelScan.java (UTF-8)
 *
 * Jun 9, 2014
 *
 * @author Marcel Patek <chemgplus at gmail.com>
 */
public class ChartPanelScan {

    private static final String title = "Energy profile along the coordinate";
    private final ChartPanel chartPanel;

    public ChartPanelScan(String axisDesc, ArrayList<Double> values, ArrayList<Double> ascf1, Double endiff) {
        this.chartPanel = createChart(axisDesc, values, ascf1, endiff);
        JFrame f = new JFrame(title);
        f.setTitle(title);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // important to say DISPOSE
        f.setLayout(new BorderLayout(0, 5));
        f.add(chartPanel, BorderLayout.CENTER);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setHorizontalAxisTrace(false);
        chartPanel.setVerticalAxisTrace(false);

        // Bottom panel for controls
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createTrace());
        panel.add(createDate());
        panel.add(createZoom());
        f.add(panel, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        
        // Position jFrame on screen; a little off the center
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = ((int) rect.getMaxX() - f.getWidth()) - 30;
        int y = ((int) rect.getMaxY() - f.getHeight()) - f.getHeight() * 3 / 4;
        f.setLocation(x, y);
        
        f.setVisible(true);
    }

    private JComboBox createTrace() {
        final JComboBox trace = new JComboBox();
        final String[] traceCmds = {"Enable Trace", "Disable Trace"};
        trace.setModel(new DefaultComboBoxModel(traceCmds));
        trace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (traceCmds[0].equals(trace.getSelectedItem())) {
                    chartPanel.setHorizontalAxisTrace(true);
                    chartPanel.setVerticalAxisTrace(true);
                    chartPanel.repaint();
                } else {
                    chartPanel.setHorizontalAxisTrace(false);
                    chartPanel.setVerticalAxisTrace(false);
                    chartPanel.repaint();
                }
            }
        });
        return trace;
    }

    private JComboBox createDate() {
        final JComboBox date = new JComboBox();
        final String[] dateCmds = {"Horizontal x-labels", "Vertical x-labels"};
        date.setModel(new DefaultComboBoxModel(dateCmds));
        date.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFreeChart chart = chartPanel.getChart();
                XYPlot xyPlot = (XYPlot) chart.getPlot();
                NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
                if (dateCmds[0].equals(date.getSelectedItem())) {
                    domain.setVerticalTickLabels(false);
                } else {
                    domain.setVerticalTickLabels(true);
                }
            }
        });
        return date;
    }

    private JButton createZoom() {
        final JButton auto = new JButton(new AbstractAction("Zoom out") {

            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.restoreAutoBounds();
            }
        });
        return auto;
    }

    private ChartPanel createChart(String axisDesc, ArrayList<Double> values,
            ArrayList<Double> ascf1, Double endiff) {

       // XYDataset roiData = createSampleData();
        JFreeChart chart = ChartFactory.createXYLineChart(title, axisDesc, "Rel. energy (kcal/mol)", createSampleData(values, ascf1),
                PlotOrientation.VERTICAL, false, true, false);

        XYPlot xyPlot = chart.getXYPlot();

        XYItemRenderer renderer1 = xyPlot.getRenderer();
        renderer1.setSeriesPaint(0, Color.blue);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyPlot.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseLinesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(5));

        TextTitle legendText = new TextTitle("(max-min)Ediff: " + endiff + " kcal/mol");
        legendText.setPosition(RectangleEdge.TOP);
        chart.addSubtitle(0, legendText);
                
        // min max array list
        Collections.sort(ascf1); // sort the list, Collections.reverseOrder()
        Double min = ascf1.get(0); // get smallest value
        Double max = ascf1.get(ascf1.size() - 1); // get highest value
        Double margin = Math.abs(max - min) * 0.05;

        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(min - margin, max + margin);
        
        //ChartPanel.Chart().getXYPlot().getRangeAxis().setInverted(true);
        //range.setInverted(true); // revert y-order
        // range.setTickUnit(new NumberTickUnit(0.01));

        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        Double tick = values.get(1) - values.get(0);
        Collections.sort(values); // sort the list
        Double vmin = values.get(0); // get smallest value
        Double vmax = values.get(values.size() - 1); // get highest value
        domain.setRange(vmin - tick / 2, vmax + tick / 2);
        domain.setTickUnit(new NumberTickUnit(tick));
        domain.setVerticalTickLabels(false); // X Label orientation
       
        return new ChartPanel(chart);
    }

    private XYDataset createSampleData(ArrayList<Double> values, ArrayList<Double> ascf1) {
        try {
            if (values.size() != ascf1.size()) {
                throw new IndexOutOfBoundsException("X and Y values have different length.");
            }
            XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
            XYSeries series = new XYSeries("Energy Profile");
            for (int i = 0; i < values.size(); i++) {
                series.add(values.get(i), ascf1.get(i));
            }
            xySeriesCollection.addSeries(series);
            return xySeriesCollection;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Check length of X and Y values. " + ex);
            return null;
        }
    }

}
