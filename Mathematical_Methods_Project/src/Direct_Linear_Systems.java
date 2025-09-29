
public class Direct_Linear_Systems {

    private String typex = "ex1";

    public double[][] getDataA() {
        switch (typex) {
            case "ex1":
                return new double[][]{
                    {2, 0, 0, 0},
                    {2, 3, 0, 0},
                    {-1, 2, 3, 0},
                    {4, 3, 2, 1}
                };
            case "ex2":
                return new double[][]{
                    {1, 2, 3, 4},
                    {0, 2, 3, 5},
                    {0, 0, 2, -4},
                    {0, 0, 0, 3}
                };
            case "ex3":
                return new double[][]{
                    {62, 24, 1, 8, 15},
                    {23, 50, 7, 14, 16},
                    {4, 6, 58, 20, 22},
                    {10, 12, 19, 66, 3},
                    {11, 18, 25, 2, 54}
                };
            case "ex4":
                return new double[][]{
                    {5, 7, 6, 5},
                    {7, 10, 8, 7},
                    {6, 8, 10, 9},
                    {5, 7, 9, 10}
                };
            case "ex5":
                double eps = Math.ulp(1.0);
                return new double[][]{
                    {eps, 1, 2},
                    {1, 2, 1},
                    {2, 2, 3}
                };
            case "ex6":
                eps = Math.ulp(1.0) / 4;
                return new double[][]{
                    {eps, 1, 2},
                    {1, 2, 1},
                    {2, 2, 3}
                };
            default:
                throw new IllegalArgumentException("Unknown typex: " + typex);
        }
    }

    public double[] getDatab() {
        switch (typex) {
            case "ex1":
                return new double[]{2, 5, 4, 10};
            case "ex2":
                return new double[]{10, 10, -2, 3};
            case "ex3":
                return new double[]{110, 110, 110, 110, 110};
            case "ex4":
                return new double[]{23, 32, 33, 31};
            case "ex5":
                double eps = Math.ulp(1.0);
                return new double[]{eps + 3, 4, 7};
            case "ex6":
                eps = Math.ulp(1.0) / 4;
                return new double[]{eps + 3, 4, 7};
            default:
                throw new IllegalArgumentException("Unknown typex: " + typex);
        }
    }

    public double[] getExact() {
        switch (typex) {
            case "ex1":
            case "ex2":
            case "ex3":
            case "ex4":
            case "ex5":
            case "ex6":
                return new double[]{1, 1, 1, 1};
            default:
                throw new IllegalArgumentException("Unknown typex: " + typex);
        }
    }

    public double[] forwardSubstitution(double[][] L, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        x[0] = b[0] / L[0][0];

        for (int i = 1; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / L[i][i];
        }

        return x;
    }

    public double[] backwardSubstitution(double[][] U, double[] b) {
        int n = b.length;
        double[] x = new double[n];
    
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / U[i][i];
        }
    
        return x;
    }

    public double[] gaussianEliminationWithPivoting(double[][] A, double[] b) {
        int n = b.length;
    
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = A[j][i] / A[i][i];
    
                for (int k = i; k < n; k++) {
                    A[j][k] -= factor * A[i][k];
                }
    
                b[j] -= factor * b[i];
            }
        }
    
        if (Math.abs(A[n - 1][n - 1]) < 1e-10) {
            throw new ArithmeticException("Matrix is singular; unique solution does not exist.");
        }
    
        return backwardSubstitution(A, b);
    }
    
    
}
