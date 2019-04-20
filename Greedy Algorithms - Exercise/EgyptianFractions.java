import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EgyptianFractions {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] fraction = bufferedReader.readLine().split("\\/");
        double numerator = Double.parseDouble(fraction[0]);
        double denominator = Double.parseDouble(fraction[1]);

        double sum = 0;
        double number = numerator / denominator;
        if (numerator > denominator) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }
        int currentDenominator = 2;
        StringBuilder sb = new StringBuilder();
        double curNum = 1.0 / currentDenominator;
        while (number - sum >= 0.0000001) {
            if (curNum + sum > number) {
                currentDenominator++;
            } else if (curNum + sum <= number) {
                sb.append("1/" + currentDenominator + " + ");
                sum += curNum;
                currentDenominator++;
            } else {
                break;
            }
            curNum = 1.0 / currentDenominator;
        }
        sb.delete(sb.length() - 3, sb.length());
        System.out.println(fraction[0] + "/" + fraction[1] + " = " + sb);
    }
}
