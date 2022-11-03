package lab.sumOfCoins;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);

        System.out.println("Number of coins to take: " + usedCoins.values()
                .stream()
                .reduce(0, (a, b) -> a + b));
        usedCoins
                .entrySet()
                .stream()
                .filter(c -> c.getValue() > 0)
                .sorted((c1, c2) -> c2.getKey().compareTo(c1.getKey()))
                .forEach((key) -> System.out.println(String.format("%d coin(s) with value %d", key.getValue(), key.getKey())));    }

    public static Map<Integer, Integer> chooseCoins(int[] coinsArr, int sum) {
        List<Integer> coins = Arrays.stream(coinsArr).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Map<Integer, Integer> finalCoins = new HashMap<>();
        coins.forEach(c -> finalCoins.put(c, 0));
        for (Integer coin : coins) {
            while (sum - coin >= 0) {
                finalCoins.put(coin, finalCoins.get(coin) + 1);
                sum -= coin;
            }
        }

        if (sum != 0) {
            throw new IllegalArgumentException("Error");
        }

        return finalCoins;
    }
}
