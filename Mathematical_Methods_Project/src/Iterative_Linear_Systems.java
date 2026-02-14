
public class Iterative_Linear_Systems {

    private double[][] A;
    private double[] b;
    private double[] exact;
    private double[] xo;
    private static final double E = 1e-10; // constant used in stop condition

    public double[][] getA() {
        return A;
    }
    public double[] getB() {
        return b;
    }

    public double[] getExact() {
        return exact;
    }

    public double[] getXo() {
        return xo;
    }

    public Iterative_Linear_Systems(String typex) {
        initializeDataSystem(typex);
    }

    // initialize the data system
    private void initializeDataSystem(String typex) {
        switch (typex) {
            case "ex1":
                A = new double[][] {
                        {62, 24, 1, 8, 15},
                        {23, 50, 7, 14, 16},
                        {4, 6, 58, 20, 22},
                        {10, 12, 19, 66, 3},
                        {11, 18, 25, 2, 54}
                };
                b = new double[]{110, 110, 110, 110, 110};
                exact = new double[]{1, 1, 1, 1, 1};
                xo = new double[]{0, 0, 0, 0, 0};
                break;
            case "ex2":
                A = new double[][] {
                        {5, 7, 6, 5},
                        {7, 10, 8, 7},
                        {6, 8, 10, 9},
                        {5, 7, 9, 10}
                };
                b = new double[]{23, 32, 33, 31};
                exact = new double[]{1, 1, 1, 1};
                xo = new double[]{0, 0, 0, 0};
                break;
            default:
                throw new IllegalArgumentException("Invalid typex");
        }
    }

    // Jacobi Method
    public Result jacobiMethod() {
        double[] xn = new double[xo.length];
        double d = Double.MAX_VALUE;
        int steps = 0;

        while (d > E && steps <= 500) {
            for (int i = 0; i < A.length; i++) {
                double S = 0;
                for (int j = 0; j < A.length; j++) {
                    if (j != i) {
                        S += A[i][j] * xo[j];
                    }
                }
                xn[i] = (b[i] - S) / A[i][i];
            }
            d = computeNorm(xn, xo);
            System.arraycopy(xn, 0, xo, 0, xo.length);
            steps++;
        }
        if (steps > 500) {
            System.out.println("The method fails to converge in 500 steps");
        }
        return new Result(xn, steps);
    }

    // Gauss-Seidel Method
    public Result gaussSeidelMethod() {
        double[] xn = new double[xo.length];
        double d = Double.MAX_VALUE;
        int steps = 0;

        while (d > E && steps <= 500) {
            for (int i = 0; i < A.length; i++) {
                double S1 = 0, S2 = 0;
                for (int j = 0; j < i; j++) {
                    S1 += A[i][j] * xn[j];
                }
                for (int j = i + 1; j < A.length; j++) {
                    S2 += A[i][j] * xo[j];
                }
                xn[i] = (b[i] - S1 - S2) / A[i][i];
            }
            d = computeNorm(xn, xo);
            System.arraycopy(xn, 0, xo, 0, xo.length);
            steps++;
        }
        if (steps > 500) {
            System.out.println("The method fails to converge in 500 steps");
        }
        return new Result(xn, steps);
    }

    // compute norm
    private double computeNorm(double[] xn, double[] xo) {
        double max = 0;
        for (int i = 0; i < xn.length; i++) {
            max = Math.max(max, Math.abs(xn[i] - xo[i]));
        }
        return max;
    }

    // class for results
    public static class Result {
        double[] solution;
        int steps;

        Result(double[] solution, int steps) {
            this.solution = solution;
            this.steps = steps;
        }

        public double[] getSolution() {
            return solution;
        }

        public int getSteps() {
            return steps;
        }
    }
}

