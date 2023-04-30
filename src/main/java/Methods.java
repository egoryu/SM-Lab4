import java.util.ArrayList;

import static java.lang.Math.*;

public class Methods {
    public static void solve(ArrayList<Point> in) {
        double[][] ans = new double[6][];

        double[][] f1 = {{in.size(), SX(in)},
                {SX(in), SX2(in)}};
        double[] c1 = {SY(in), SYX(in)};
        ans[0] = Function.iteration(f1, c1, 0.001);

        double[][] f2 = {{in.size(), SX(in), SX2(in)},
                {SX(in), SX2(in), SX3(in)},
                {SX2(in), SX3(in), SX4(in)}};
        double[] c2 = {SY(in), SYX(in), SYX2(in)};
        ans[1] = Function.iteration(f2, c2, 0.001);

        double[][] f3 = {{in.size(), SX(in), SX2(in), SX3(in)},
                {SX(in), SX2(in), SX3(in), SX4(in)},
                {SX2(in), SX3(in), SX4(in), SX5(in)},
                {SX3(in), SX4(in), SX5(in), SX6(in)}};
        double[] c3 = {SY(in), SYX(in), SYX2(in), SYX3(in)};
        ans[2] = Function.iteration(f3, c3, 0.001);

        double[][] f4 = {{in.size(), SX(in)},
                {SX(in), SX2(in)}};
        double[] c4 = {SLnY(in), SLnYX(in)};
        ans[3] = Function.iteration(f4, c4, 0.001);
        ans[3][0] = pow(Math.E, ans[3][0]);

        double[][] f5 = {{in.size(), SLnX(in)},
                {SLnX(in), SLnX2(in)}};
        double[] c5 = {SY(in), SYLnX(in)};
        ans[4] = Function.iteration(f5, c5, 0.001);

        double[][] f6 = {{in.size(), SLnX(in)},
                {SLnX(in), SLnX2(in)}};
        double[] c6 = {SLnY(in), SLnYLnX(in)};
        ans[5] = Function.iteration(f6, c6, 0.001);
        ans[5][0] = pow(Math.E, ans[5][0]);

        double min = Double.MAX_VALUE;
        int id = 0;
        Algo[] val = Algo.values();

        System.out.printf("\nКоэффициент корреляции Пирсона: %.5f\n\n", Piers(in));
        System.out.println("Среднеквадратическое отклонения функций:");
        for (int i = 0; i < ans.length; i++) {
            double tmp = MO(in, val[i], ans[i]);

            if (min > tmp) {
                min = tmp;
                id = i;
            }

            System.out.println(val[i] + " : " + tmp);
        }

        System.out.println("\nЛучший выбор это " + val[id] + "\n");

        System.out.println("Функции:");
        for (int i = 0; i < ans.length; i++) {
            System.out.print(val[i] + " : ");
            Function.drawFunction(val[i], ans[i]);
        }

        System.out.println("");

        OutPut.write(in, ans);
        Chart.draw(in, ans);
    }

    static double MO(ArrayList<Point> in, Algo num, double[] c) {
        double ans = 0;

        for (Point point : in) {
            ans += pow(Function.getFunction(point.getX(), num, c) - point.getY(), 2);
        }

        ans /= in.size();
        ans = sqrt(ans);

        return ans;
    }

    static double Piers(ArrayList<Point> in) {
        double ans = 0, a = 0, b = 0;
        double sx = SX(in) / in.size(), sy = SY(in) / in.size();

        for (Point point: in) {
            ans += (point.getX() - sx) * (point.getY() - sy);
        }

        for (Point point: in) {
            a += pow(point.getX() - sx, 2);
        }

        for (Point point: in) {
            b += pow(point.getY() - sy, 2);
        }

        return ans / sqrt(a * b);
    }

    static double SX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += q.getX();
        }

        return ans;
    }

    static double SX2(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 2);
        }

        return ans;
    }

    static double SX3(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 3);
        }

        return ans;
    }

    static double SX4(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 4);
        }

        return ans;
    }

    static double SX5(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 5);
        }

        return ans;
    }

    static double SX6(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 6);
        }

        return ans;
    }

    static double SY(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += q.getY();
        }

        return ans;
    }

    static double SYX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += q.getX() * q.getY();
        }

        return ans;
    }

    static double SYX2(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 2) * q.getY();
        }

        return ans;
    }

    static double SYX3(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(q.getX(), 3) * q.getY();
        }

        return ans;
    }

    static double SLnX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += log(q.getX());
        }

        return ans;
    }

    static double SLnX2(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += pow(log(q.getX()), 2);
        }

        return ans;
    }

    static double SLnY(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += log(q.getY());
        }

        return ans;
    }

    static double SLnYLnX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += log(q.getY()) * log(q.getX());
        }

        return ans;
    }

    static double SLnYX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += log(q.getY()) * q.getX();
        }

        return ans;
    }

    static double SYLnX(ArrayList<Point> in) {
        double ans = 0;

        for (var q: in) {
            ans += q.getY() * log(q.getX());
        }

        return ans;
    }
}
