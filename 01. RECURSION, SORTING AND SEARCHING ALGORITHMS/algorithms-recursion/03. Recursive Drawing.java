import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());

        drawPicture(number);
    }

    private static void drawPicture(int number) {
        if (number <= 0) {
            return;
        }

        System.out.println(repeatString("*", number));

        drawPicture(number -  1);

        System.out.println(repeatString("#", number));
    }

    private static String repeatString(String s, int count) {
        if (count <= 0) {
            return "";
        }

        return s + repeatString(s, count - 1);
    }
}
