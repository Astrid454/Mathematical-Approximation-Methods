import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do
        {
            System.out.println("Insert the number corresponding to the method you want to use:");
            System.out.println();
            System.out.println("1. Bisection Newton Secant Method (Nonlinear equations)");
            System.out.println("2. Fixed Point Method  (Nonlinear equations)");
            System.out.println("3. Newton Cotes (Numerical Integration)");
            System.out.println("4. Iterative Linear Systems");
            System.out.println("5. Direct Linear Systems");
            System.out.println("6. Thomas Algorithm");
            System.out.println("0. Exit");
            System.out.println();

            choice = scanner.nextInt();

            switch(choice)
            {
                case 0: return;
                case 1: {
                    Bisection_Newton_Secant nm = new Bisection_Newton_Secant("ex6");
                    double[] limits = nm.limitsInt();
                    double a = limits[0], b = limits[1], x0n = limits[2], x0s = limits[3], x1s = limits[4];
                    double e = 1e-10;
            
                    // flot function
                    //plotFunction(nm, a, b);
            
                    // Bisection Method
                    double[] bisResult = nm.bisectionMethod(a, b, e);
                    System.out.printf("Bisection: Root = %.5f, Steps = %d\n", bisResult[0], (int) bisResult[1]);
            
                    // Newton Method
                    double[] newtResult = nm.newtonMethod(x0n, e);
                    System.out.printf("Newton: Root = %.5f, Steps = %d\n", newtResult[0], (int) newtResult[1]);
            
                    // Secant Method
                    double[] secResult = nm.secantMethod(x0s, x1s, e);
                    System.out.printf("Secant: Root = %.5f, Steps = %d\n", secResult[0], (int) secResult[1]);
                }
                break;
                case 2: {
                    FixedPointMethod solver = new FixedPointMethod("ex3", "g4");
                    solver.solveFixedPointMethod();
                }
                break;
                case 3: {
                    Newton_Cotes solver = new Newton_Cotes("ex1", 60);

                    System.out.println("NUMERICAL METHODS FOR NUMERICAL INTEGRATION\n");
                    System.out.println("To introduce a new example modify: LimitsInt, ftitle, and f\n");
            
                    double[] limits = solver.LimitsInt();
                    double a = limits[0];
                    double b = limits[1];
                    double exact = limits[2];
            
                    System.out.printf("The exact value of the integral is %.5f\n\n", exact);
                     // Trapezoidal Rule
                    System.out.println("TRAPEZOIDAL RULE");
                    double T = solver.TrapRule(a, b);
                    System.out.printf("Approximation of the integral, using %d nodes, is %.5f\n\n", solver.getN() + 1, T);

                    // Midpoint Rule
                    System.out.println("MIDPOINT RULE");
                    double M = solver.MidpointRule(a, b);
                    System.out.printf("Approximation of the integral, using %d nodes, is %.5f\n\n", solver.getN() + 1, M);

                    // Simpson Rule
                    System.out.println("SIMPSON RULE");
                    double S = solver.SimpsonRule(a, b);
                    System.out.printf("Approximation of the integral, using %d nodes, is %.5f\n\n", solver.getN() + 1, S);

                    // Simpson 3/8 Rule
                    System.out.println("SIMPSON 3/8 RULE");
                    double S38 = solver.Simpson3_8Rule(a, b);
                    System.out.printf("Approximation of the integral, using %d nodes, is %.5f\n\n", solver.getN() + 1, S38);
                }
                break;
                case 4: {
                    Iterative_Linear_Systems system = new Iterative_Linear_Systems("ex2");

                    System.out.println("The matrix of the system:");
                    for (double[] row : system.getA()) {
                        System.out.println(Arrays.toString(row));
                    }
            
                    System.out.println("The right-hand side (b): " + Arrays.toString(system.getB()));
                    System.out.println("The exact solution: " + Arrays.toString(system.getExact()));
                    System.out.println("The initial values: " + Arrays.toString(system.getXo()));
            
                    // Jacobi Method
                    System.out.println("JACOBI METHOD");
                    long start = System.nanoTime();
                    Iterative_Linear_Systems.Result jacobiResult = system.jacobiMethod();
                    long end = System.nanoTime();
                    System.out.println("Approx. Solution (Jacobi): " + Arrays.toString(jacobiResult.getSolution()));
                    System.out.println("Steps: " + jacobiResult.getSteps());
                    System.out.printf("Execution time: %.7f seconds%n", (end - start) / 1e9);
            
                    // Gauss-Seidel Method
                    System.out.println("GAUSS-SEIDEL METHOD");
                    start = System.nanoTime();
                    Iterative_Linear_Systems.Result gaussResult = system.gaussSeidelMethod();
                    end = System.nanoTime();
                    System.out.println("Approx. Solution (Gauss-Seidel): " + Arrays.toString(gaussResult.getSolution()));
                    System.out.println("Steps: " + gaussResult.getSteps());
                    System.out.printf("Execution time: %.7f seconds%n", (end - start) / 1e9);
                }
                break;
                case 5: {
                    Direct_Linear_Systems dls = new Direct_Linear_Systems();

                    double[][] A = dls.getDataA();
                    double[] b = dls.getDatab();
                    double[] exact = dls.getExact();

                    System.out.println("The matrix of the system A:");
                    System.out.println(Arrays.deepToString(A));

                    System.out.println("The right-hand side vector b:");
                    System.out.println(Arrays.toString(b));

                    System.out.println("The exact solution:");
                    System.out.println(Arrays.toString(exact));

                    // example of solving the system using Gaussian Elimination with pivoting
                    double[] solutionWithoutPivoting = dls.gaussianEliminationWithPivoting(A, b);
                    System.out.println("Approximate solution using Gaussian Elimination without Pivoting:");
                    System.out.println(Arrays.toString(solutionWithoutPivoting));

                }
                break;
                case 6: {
                    Thomas_Algorithm solver = new Thomas_Algorithm();

                    System.out.println("Matricea sistemului este de forma:");
                    int n = 7;
                    double[][] T7 = new double[n][n];
                    for (int i = 0; i < n; i++) {
                        T7[i][i] = 5;
                        if (i > 0) T7[i][i - 1] = -1;
                        if (i < n - 1) T7[i][i + 1] = -1;
                    }
                    for (double[] row : T7) {
                        System.out.println(Arrays.toString(row));
                    }
            
                    n = 100;
                    double[] exact = new double[n];
                    Arrays.fill(exact, 1);
            
                    double[] a = new double[n];
                    double[] b = new double[n];
                    double[] c = new double[n];
                    double[] r = new double[n];
            
                    Arrays.fill(a, 5);
                    Arrays.fill(b, -1);
                    Arrays.fill(c, -1);
                    b[0] = 0;
                    c[n - 1] = 0;
            
                    r[0] = 4;
                    Arrays.fill(r, 1, n - 1, 3);
                    r[n - 1] = 4;
            
                    long startThomas = System.nanoTime();
                    double[] xThomas = solver.solveThomas(a, b, c, r);
                    long endThomas = System.nanoTime();
                    System.out.printf("Timpul de executie pentru algoritmul Thomas: %.10f secunde\n", (endThomas - startThomas) / 1e9);

                    double[][] T = new double[n][n];
                    for (int i = 0; i < n; i++) {
                        T[i][i] = 5;
                        if (i > 0) T[i][i - 1] = -1;
                        if (i < n - 1) T[i][i + 1] = -1;
                    }
            
                    long startGauss = System.nanoTime();
                    double[] xGauss = solver.solveGauss(T, r);
                    long endGauss = System.nanoTime();
                    System.out.printf("Timpul de executie pentru metoda eliminarii lui Gauss: %.10f secunde\n", (endGauss - startGauss) / 1e9);
                }
                break;
                default: throw new IllegalArgumentException("Invalid option. Try again.");
            }
            System.out.println();

        }while(choice != 0);

        scanner.close();
    }
}



