import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.Double.NaN;

public class Chart {
    public static void draw(ArrayList<Point> in, double[][] ans) {
        XYSeriesCollection lineDataset = new XYSeriesCollection ();
        Algo[] val = Algo.values();

        double a = in.get(0).getX(), b = in.get(in.size() - 1).getX() + 1.0;
        /*XYSeries border = new XYSeries("y=0");
        border.add(a,0);
        border.add(b,0);

        lineDataset.addSeries(border);*/

        XYSeries series0 = new XYSeries("Начальные точки");
        for (Point point : in) {
            series0.add(point.getX(), point.getY());
        }

        lineDataset.addSeries(series0);

        for (int i = 0; i < val.length; i++) {
            XYSeries series = new XYSeries(val[i]);

            for (double j = a; j < b; j += (b - a) / 100.0) {
                double y = Function.getFunction(j, val[i], ans[i]);

                if (!Double.isInfinite(y)) {
                    series.add(j, y);
                }
            }

            lineDataset.addSeries(series);
        }

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "f(x)", "x",
                "y",
                lineDataset, PlotOrientation.VERTICAL,
                true, true, false);

        try {
            ChartUtils.saveChartAsJPEG(new File("src/main/resources/chart/charts.jpeg"), lineChart, 1920, 1080);
        } catch (IOException e) {
            System.out.println("Не удалось сохранить график");
        } catch (IllegalArgumentException e) {
            System.out.println("Не правильный формат числа");
        }
    }
}
