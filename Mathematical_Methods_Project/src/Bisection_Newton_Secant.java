/*import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
*/

public class Bisection_Newton_Secant {

    private String type;

    public Bisection_Newton_Secant(String type) {
        this.type = type;
    }

    // Function f(x)
    public double f(double x) {
        switch (type) {
            case "ex1": return Math.pow(x, 3) + 4 * Math.pow(x, 2) - 10;
            case "ex2": return Math.cos(x) - Math.pow(x, 2) / 2;
            case "ex3": return Math.exp(-Math.pow(x, 2)) - 2 * x;
            case "ex4": return Math.exp(-Math.pow(Math.sin(x), 2) + x) - 2 * x;
            case "ex5": return Math.pow(x, 3) - 2 * x + 1;
            case "ex6": return Math.pow(x, 4) + 2 * Math.pow(x, 2) - x - 3;
            default: throw new IllegalArgumentException("Unknown function type!");
        }
    }

    // Derivative f'(x)
    public double fd(double x) {
        switch (type) {
            case "ex1": return 3 * Math.pow(x, 2) + 8 * x;
            case "ex2": return -Math.sin(x) - x;
            case "ex3": return -2 * x * Math.exp(-Math.pow(x, 2)) - 2;
            case "ex4": return Math.exp(-Math.pow(Math.sin(x), 2) + x) * (1 - 2 * Math.sin(x) * Math.cos(x)) - 2;
            case "ex5": return 3 * Math.pow(x, 2) - 2;
            case "ex6": return 4 * Math.pow(x, 3) + 4 * x - 1;
            default: throw new IllegalArgumentException("Unknown function type!");
        }
    }

    // Interval limits and initial values
    public double[] limitsInt() {
        switch (type) {
            case "ex1": return new double[]{1, 2, 2, 1, 2};
            case "ex2": return new double[]{0.5, 1.5, 1.75, 0.5, 2};
            case "ex3": return new double[]{0, 1, 1, 0, 1};
            case "ex4": return new double[]{0, 1.5, 0, 0, 1.5};
            case "ex5": return new double[]{0.75, 2, 1.5, 0.75, 1.5};
            case "ex6": return new double[]{0, 2, 1, 1, 2};
            default: throw new IllegalArgumentException("Unknown function type!");
        }
    }

    // Bisection Method
    public double[] bisectionMethod(double a, double b, double e) {
        double m = (a + b) / 2.0;
        double d = Math.abs(b - a);
        int k = 1;

        while (d > e && k <= 100) {
            if (f(a) * f(m) < 0) b = m;
            else a = m;

            m = (a + b) / 2.0;
            d = Math.abs(b - a);
            k++;
            if (f(m) == 0) break;
        }
        return new double[]{m, k};
    }

    // Newton Method
    public double[] newtonMethod(double x0, double e) {
        double xn = x0 - f(x0) / fd(x0);
        int k = 1;

        while (Math.abs(xn - x0) > e && k <= 100) {
            x0 = xn;
            xn = x0 - f(x0) / fd(x0);
            k++;
        }
        return new double[]{xn, k};
    }

    // Secant Method
    public double[] secantMethod(double x0, double x1, double e) {
        double x2 = x1 - f(x1) * (x1 - x0) / (f(x1) - f(x0));
        int k = 1;

        while (Math.abs(x2 - x1) > e && k <= 100) {
            x0 = x1;
            x1 = x2;
            x2 = x1 - f(x1) * (x1 - x0) / (f(x1) - f(x0));
            k++;
        }
        return new double[]{x2, k};
    }

    // Plot Function
    /*public static XYSeries createSeries(String title, Bisection_Newton_Secant nm, double a, double b, double step) {
        XYSeries series = new XYSeries(title);
        for (double x = a; x <= b; x += step) {
            series.add(x, nm.f(x));
        }
        return series;
    }

    public static void plotFunction(Bisection_Newton_Secant nm, double a, double b) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(createSeries("f(x)", nm, a - 0.5, b + 0.5, 0.05));

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph of f(x)",
                "x",
                "f(x)",
                dataset
        );

        JFrame frame = new JFrame("Function Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(new ChartPanel(chart), java.awt.BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
*/
   
}
