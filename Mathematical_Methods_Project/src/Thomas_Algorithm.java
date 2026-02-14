
public class Thomas_Algorithm {

    public double[] solveThomas(double[] a, double[] b, double[] c, double[] r) {
        int n = a.length;
        double[] d = new double[n];
        double[] y = new double[n];
        double[] x = new double[n];

        d[0] = c[0] / a[0];
        y[0] = r[0] / a[0];

        for (int i = 1; i < n; i++) {
            double denom = a[i] - b[i] * d[i - 1];
            d[i] = c[i] / denom;
            y[i] = (r[i] - b[i] * y[i - 1]) / denom;
        }

        x[n - 1] = y[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = y[i] - d[i] * x[i + 1];
        }

        return x;
    }

    public double[] solveGauss(double[][] A, double[] b) {
        int n = b.length;
        double[][] augmentedMatrix = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][n] = b[i];
        }

        // Gaussian Elimination with partial pivoting
        for (int i = 0; i < n - 1; i++) {
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > Math.abs(augmentedMatrix[maxRow][i])) {
                    maxRow = j;
                }
            }

            double[] temp = augmentedMatrix[maxRow];
            augmentedMatrix[maxRow] = augmentedMatrix[i];
            augmentedMatrix[i] = temp;

            for (int j = i + 1; j < n; j++) {
                double multiplier = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                for (int k = i; k <= n; k++) {
                    augmentedMatrix[j][k] -= multiplier * augmentedMatrix[i][k];
                }
            }
        }

        // Back Substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = augmentedMatrix[i][n];
            for (int j = i + 1; j < n; j++) {
                x[i] -= augmentedMatrix[i][j] * x[j];
            }
            x[i] /= augmentedMatrix[i][i];
        }

        return x;
    }
  

}
