import static java.lang.Math.*;

public class Function {
    public static double getFunction(double x, Algo num, double[] in) {
        switch (num) {
            case LINE -> {
                return in[1] * x + in[0];
            }
            case LINE2 -> {
                return in[2] * pow(x, 2) + in[1] * x + in[0];
            }
            case LINE3 -> {
                return in[3] * pow(x, 3) + in[2] * pow(x, 2) + in[1] * x + in[0];
            }
            case EXP -> {
                return in[0] * Math.pow(Math.E, in[1] * x);
            }
            case LOG -> {
                return in[1] * Math.log(x) + in[0];
            }
            case POW -> {
                return in[0] * Math.pow(x, in[1]);
            }
        }
        return 0;
    }

    public static void drawFunction(Algo num, double[] in) {
        switch (num) {
            case LINE -> {
                System.out.printf("%.5f * x + %.5f\n", in[1], in[0]);
            }
            case LINE2 -> {
                System.out.printf("%.5f * x ^ 2 + %.5f * x + %.5f\n", in[2], in[1], in[0]);
            }
            case LINE3 -> {
                System.out.printf("%.5f * x ^ 3 + %.5f * x ^ 2 + %.5f * x + %.5f\n", in[3], in[2], in[1], in[0]);
            }
            case EXP -> {
                if (!Double.isNaN(in[0])) {
                    System.out.printf("%.5f * e ^ (%.5f * x)\n", in[0], in[1]);
                } else {
                    System.out.println("Нет такой функции");
                }
            }
            case LOG -> {
                System.out.printf("%.5f * ln(x) + %.5f\n", in[1], in[0]);
            }
            case POW -> {
                System.out.printf("%.5f * x ^ %.5f\n", in[0], in[1]);
            }
        }
    }
    static double[][] columnSwap(double[][] matrix, int x, int y) {
        for (int i = 0; i < matrix.length; i++) {
            double t = matrix[i][x];

            matrix[i][x] = matrix[i][y];
            matrix[i][y] = t;
        }

        return matrix;
    }

    static double[][] diagonalizing(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            int cur = i;

            for (int j = 0; j < matrix.length; j++) {
                if (abs(matrix[i][cur]) < abs(matrix[i][j])) {
                    cur = j;
                }
            }
            if (cur != i) {
                matrix = columnSwap(matrix, cur, i);
            }
        }

        return matrix;
    }

    public static double[] iteration(double[][] matrix, double[] cof, double epsilon) {
        for (int i = 0; i < matrix.length; i++) {
            double t = matrix[i][i];

            cof[i] /= t;
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] /= -t;
            }

            matrix[i][i] = 0;
        }

        double[][] ans = new double[2000][];
        int cur = 0;

        ans[cur++] = cof;

        while (cur < 2000) {
            ans[cur++] = cof;

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    ans[cur - 1][i] += matrix[i][j] * ans[cur - 2][j];
                }
            }

            double delta = Double.MIN_VALUE;

            for (int i = 0; i < matrix.length; i++) {
                delta = max(delta, abs(ans[cur - 1][i] - ans[cur - 2][i]));
            }

            if (delta < epsilon) {
                break;
            }
        }

        return ans[cur - 1];
    }
}
