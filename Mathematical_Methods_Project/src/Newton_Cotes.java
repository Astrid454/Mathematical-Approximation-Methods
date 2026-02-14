public class Newton_Cotes {
    private static String typex;
    private int n;

    public int getN() {
        return n;
    }

    public Newton_Cotes(String typex, int n) {
        this.typex = typex;
        this.n = n;
    }
    // function f(x)
    public double f(double x) {
        switch (typex) {
            case "ex1": return Math.cos(x);
            case "ex2": return Math.pow(x, 2) + 3 * x;
            case "ex3": return x * Math.exp(-x) * Math.cos(2 * x);
            default: throw new IllegalArgumentException("Unknown function type!");
        }
    }

    // function description
    public String ftitle() {
        switch (typex) {
            case "ex1":
                return "$\\int^{\\frac{\\pi}{2}}_0 \\cos(x)dx = 1$";
            case "ex2":
                return "$\\int^{1}_0 (x^2 + 3x)dx = \\frac{11}{6}$";
            case "ex3":
                return "$\\int^{2\\pi}_0 x e^{-x} \\cos(x) dx$";
            default:
                throw new IllegalArgumentException("Unknown typex: " + typex);
        }
    }
     // integral limits
     public double[] LimitsInt() {
        switch (typex) {
            case "ex1":
                return new double[]{0, Math.PI / 2, 1};
            case "ex2":
                return new double[]{0, 1, 11.0 / 6.0};
            case "ex3":
                return new double[]{0, 2 * Math.PI, (3 * (Math.exp(-2 * Math.PI) - 1) - 10 * Math.PI * Math.exp(-2 * Math.PI)) / 25};
            default:
                throw new IllegalArgumentException("Unknown typex: " + typex);
        }
    }

    // Trapezoidal Rule
    public double TrapRule(double a, double b) {
        double h = (b - a) / n;
        double T = 0.5 * (f(a) + f(b));
        for (int i = 1; i < n; i++) {
            T += f(a + i * h);
        }
        return T * h;
    }

    // Midpoint Rule
    public double MidpointRule(double a, double b){
        double h = (b - a) / n;
        double M = 0;
        for(int i = 0; i < n; i++) {
            M += f(a + (2 * i - 1) * h / 2);
        }
        return M * h;
    }

    // Simpson Rule
    public double SimpsonRule(double a, double b){
        if(n % 2 != 0)throw new IllegalArgumentException("!!! Give an even value for the number n !!");
        double h = (b - a) / n;
        double S1 = 0, S2 = 0;
        for (int i = 1; i <= n/2 - 1; i++) {
           S1 += f(a + 2 * i * h);
           S2 += f(a + (2 * i - 1) * h);
        }
        S2 += f(a + (n - 1) * h);
        return (f(a) + 2 * S1 + 4 * S2 + f(b)) * h / 3;
    }

    // Simpson 3/8 Rule
    public double Simpson3_8Rule(double a, double b){
        if(n % 3 != 0)throw new IllegalArgumentException("!!! Give n as a multiple of 3 !!!");
        double h = (b - a) / n;
        double S1 = 0, S2 = 0;
        for (int i = 1; i < n; i++){
            if(i % 3 == 0)
                S1 = S1 + f(a + i * h);
            else
                S2 = S2 + f(a + i * h);
        }
        return (f(a) + 2 * S1 + 3 * S2 + f(b)) * 3 * h / 8;
    }
}

