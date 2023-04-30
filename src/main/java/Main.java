import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int numOfPoint, input, save;
            double x, y;
            ArrayList<Point> points = new ArrayList<>();
            StringTokenizer st;

            System.out.println("Это четвертая лабораторная работа по Вычислительной математике");
            System.out.println("""
                    Введите:
                    0 - для выхода
                    1 - для ввода данных из консоли
                    2 - для ввода данных из файла""");

            input = inputInt(in);

            switch (input) {
                case (1) -> {
                    while (true) {
                        System.out.println("Введите количество точек:");
                        numOfPoint = inputInt(in);
                        if (numOfPoint < 8 || numOfPoint > 12) {
                            System.out.println("Некорректный ввод. Введите в промежутке [8; 12]");
                            continue;
                        }
                        break;
                    }
                    points.clear();
                    for (int i = 0; i < numOfPoint; i++) {
                        while (true) {
                            System.out.print("Введите x-" + (i + 1) + ":");
                            x = inputDouble(in);
                            if (x == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        while (true) {
                            System.out.print("Введите y-" + (i + 1) + ":");
                            y = inputDouble(in);
                            if (y == Double.MIN_VALUE) {
                                System.out.println("Некорректный ввод");
                                continue;
                            }
                            break;
                        }

                        points.add(new Point(x, y));
                    }

                    points.sort(Comparator.comparingDouble(Point::getX));

                    while (true) {
                        System.out.println("0 - сохранить в файл\n" +
                                "1 - вывести ответ в консоль");
                        save = inputInt(in);
                        if (!(save == 1 || save == 0)) {
                            System.out.println("Некорректный ввод");
                            continue;
                        }
                        break;
                    }
                    for (var q : points) {
                        System.out.println(q);
                    }
                }
                case (2) -> {
                    String name = "";
                    System.out.println("Введите имя файла:");
                    while (name.equals("")) {
                        name = in.nextLine();
                    }
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/input/" + name))) {
                        try {
                            numOfPoint = Integer.parseInt(bufferedReader.readLine());
                        } catch (Exception e) {
                            System.out.println("Ошибка при чтении количества точек");
                            continue;
                        }
                        if (numOfPoint < 8 || numOfPoint > 12) {
                            System.out.println("Некорректный ввод. Введите в промежутке [8; 12]");
                            continue;
                        }
                        points.clear();
                        for (int i = 0; i < numOfPoint; i++) {
                            try {
                                st = new StringTokenizer(bufferedReader.readLine());
                                x = Double.parseDouble(st.nextToken());
                            } catch (Exception e) {
                                System.out.println("Ошибка при чтении x-" + (i + 1));
                                continue;
                            }

                            try {
                                y = Double.parseDouble(st.nextToken());
                            } catch (Exception e) {
                                System.out.println("Ошибка при чтении y-" + (i + 1));
                                continue;
                            }

                            points.add(new Point(x, y));
                        }

                        points.sort(Comparator.comparingDouble(Point::getX));

                        try {
                            save = Integer.parseInt(bufferedReader.readLine());
                        } catch (Exception e) {
                            System.out.println("Ошибка при чтении режима вывода");
                            continue;
                        }
                        if (!(save == 1 || save == 0)) {
                            System.out.println("Некорректный ввод режима вывода");
                            continue;
                        }
                        for (var q : points) {
                            System.out.println(q);
                        }
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            System.out.println("Не удалось закрыть файл");
                        }

                        Methods.solve(points);
                    } catch (IOException e) {
                        System.out.println("Не удалось открыть файл");
                        continue;
                    }
                }
                case (0) -> {
                    System.out.println("Конец работы");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод");
            }
        }
    }

    public static int inputInt(Scanner in) {
        int num;
        try {
            num = in.nextInt();
        } catch (Exception e) {
            if (!in.hasNextLine()) {
                System.out.println("Конец работы");
                System.exit(0);
            } else {
                System.out.println("Плохая строка");
            }
            in.nextLine();
            return -1;
        }
        return num;
    }

    public static double inputDouble(Scanner in) {
        double num;
        try {
            num = in.nextDouble();
        } catch (Exception e) {
            if (!in.hasNextLine()) {
                System.out.println("Конец работы");
                System.exit(0);
            } else {
                System.out.println("Плохая строка");
            }
            in.nextLine();
            return Double.MIN_VALUE;
        }
        return num;
    }
}
