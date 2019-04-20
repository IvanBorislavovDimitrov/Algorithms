
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsack {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        double capacity = Double.parseDouble(bufferedReader.readLine().split(" ")[1]);
        double itemsCount = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);
        List<Pair<Double, Double>> pricesItems = new ArrayList<>();

        for (int i = 0; i < itemsCount; i++) {
            String[] priceWeight = bufferedReader.readLine().split(" -> ");
            pricesItems.add(new Pair<>(Double.parseDouble(priceWeight[0]), Double.parseDouble(priceWeight[1])));
        }
        pricesItems.sort((p1, p2) -> Double.compare(p2.getKey() / p2.getValue(), p1.getKey() / p1.getValue()));

        double currentCapacity = 0;
        double price = 0;
        for (Pair<Double, Double> priceItem : pricesItems) {
            if (currentCapacity + priceItem.getValue() <= capacity) {
                System.out.println(String.format("Take 100%% of item with price %.2f and weight %.2f", priceItem.getKey(), priceItem.getValue()));
                price += priceItem.getKey();
                currentCapacity += priceItem.getValue();
            } else {
                double diff = capacity - currentCapacity;
                double percent = diff / priceItem.getValue() * 100;
                double newPrice = percent * priceItem.getKey() / 100;
                System.out.println(String.format("Take %.2f%% of item with price %.2f and weight %.2f", percent, priceItem.getKey(), priceItem.getValue()));
                price += newPrice;
                break;
            }
        }

        System.out.println(String.format("Total price: %.2f", price));
    }
}
