public class FixedPointMethod {
    private double a, b, x0, e;
    private String typex;
    private String typeg;
    
    public FixedPointMethod(String typex, String typeg) {
        this.typex = typex;
        this.typeg = typeg;
        this.e = Math.pow(10, -10); 
        setLimitsAndInitialValues();
    }
    
    // sets limits and initial values for the interval and x0 based on typex
    private void setLimitsAndInitialValues() {
        switch (typex) {
            case "ex1":
                this.a = 1;
                this.b = 2;
                this.x0 = 2;
                break;
            case "ex2":
                this.a = 3 / 4.0;
                this.b = 2;
                this.x0 = 1.5;
                break;
            case "ex3":
                this.a = 0.5;
                this.b = 2;
                this.x0 = 1.5;
                break;
        }
    }
    
    // method to solve the fixed point iteration
    public void solveFixedPointMethod() {
        System.out.println("FIXED POINT METHOD FOR NONLINEAR EQUATIONS");
        System.out.println("\nTo introduce a new example modify: LimitsInt, ftitle, f\n");

        long startTime = System.nanoTime();
        double mfp = fixedPointMethod(x0, e);
        long endTime = System.nanoTime();
        double timefp = (endTime - startTime) / 1e6; // convert to milliseconds

        System.out.printf("The approx. sol. is %.5f\n", mfp);
        System.out.printf("The execution time for Fixed Point Method is %.7f ms\n", timefp);
        System.out.println("-------------------------------------------\n");
    }

    // Fixed Point Method
    private double fixedPointMethod(double x0, double e) {
        double x1 = g(x0);
        int k = 1;
        double d = Math.abs(x1 - x0);

        if (f(x1) == 0) {
            return x1;
        } else if (d < e) {
            return x1;
        } else {
            while (d > e && k <= 100) {
                x1 = g(x0);
                d = Math.abs(x1 - x0);
                k++;
                x0 = x1;
            }
            if (k > 100) {
                System.out.println("The method fails to converge in 100 steps");
            }
            return x1;
        }
    }

    // function g(x) based on typex and typeg
    private double g(double x) {
        switch (typex) {
            case "ex1":
                switch (typeg) {
                    case "g1":
                        return x - Math.pow(x, 3) - 4 * Math.pow(x, 2) + 10;
                    case "g2":
                        return Math.sqrt((10 / x) - 4 * x);
                    case "g3":
                        return 0.5 * Math.sqrt(10 - Math.pow(x, 3));
                    case "g4":
                        return Math.sqrt(10 / (4 + x));
                    case "g5":
                        return x - (Math.pow(x, 3) + 4 * Math.pow(x, 2) - 10) / (3 * Math.pow(x, 2) + 8 * x);
                }
                break;
            case "ex2":
                switch (typeg) {
                    case "g1":
                        return (Math.pow(x, 3) + 1) / 2;
                    case "g2":
                        return 2 / x - 1 / Math.pow(x, 2);
                    case "g3":
                        return Math.sqrt(2 - 1 / x);
                    case "g4":
                        return Math.cbrt(1 - 2 * x);
                }
                break;
            case "ex3":
                switch (typeg) {
                    case "g1":
                        return Math.pow(3 + x - 2 * Math.pow(x, 2), 1.0 / 4);
                    case "g2":
                        return Math.sqrt((x + 3 - Math.pow(x, 4)) / 2);
                    case "g3":
                        return Math.sqrt((x + 3) / (Math.pow(x, 2) + 2));
                    case "g4":
                        return (3 * Math.pow(x, 4) + 2 * Math.pow(x, 2) + 3) / (4 * Math.pow(x, 3) + 4 * x - 1);
                }
                break;
        }
        return 0; 
    }

    // function f(x) based on typex
    private double f(double x) {
        switch (typex) {
            case "ex1":
                return Math.pow(x, 3) + 4 * Math.pow(x, 2) - 10;
            case "ex2":
                return Math.pow(x, 3) - 2 * x + 1;
            case "ex3":
                return Math.pow(x, 4) + 2 * Math.pow(x, 2) - x - 3;
        }
        return 0; 
    }

    // function to return a string that describes the function f(x)
    public String ftitle() {
        switch (typex) {
            case "ex1":
                return "$f(x) = x^3 + 4x^2 - 10$";
            case "ex2":
                return "$f(x) = x^3 - 2x + 1$";
            case "ex3":
                return "$f(x) = x^4 + 2x^2 - x - 3$";
        }
        return "";
    }

}


