import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutPut {
    public static void write(ArrayList<Point> in, double[][] ans) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/output/methods.txt", false)){
            Algo[] val = Algo.values();

            for (int i = 0; i < val.length; i++) {
                fileWriter.write((i + 1) + ") " + val[i] + "\n");

                fileWriter.write(String.format("|%-15s|", "№"));
                for (int j = 0; j < in.size(); j++) {
                    fileWriter.write(String.format("%-15d|", (j + 1)));
                }

                fileWriter.write("\n");

                fileWriter.write(String.format("|%-15s|", "X"));
                for (Point point : in) {
                    fileWriter.write(String.format("%-15.5f|", point.getX()));
                }

                fileWriter.write("\n");

                fileWriter.write(String.format("|%-15s|", "Y"));
                for (Point point : in) {
                    fileWriter.write(String.format("%-15.5f|", point.getY()));
                }

                fileWriter.write("\n");

                fileWriter.write(String.format("|%-15s|", "F(X)"));
                for (Point point : in) {
                    fileWriter.write(String.format("%-15.5f|", Function.getFunction(point.getX(), val[i], ans[i])));
                }

                fileWriter.write("\n");

                fileWriter.write(String.format("|%-15s|", "E"));
                for (Point point : in) {
                    fileWriter.write(String.format("%-15.5f|", Function.getFunction(point.getX(), val[i], ans[i]) - point.getY()));
                }

                fileWriter.write("\n\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Не удалось сохранить");
        }
    }
}
