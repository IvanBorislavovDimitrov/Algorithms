package AlgorithmsExam20Aug2017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TravellingPoliceman {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int remainingCarFuel = Integer.parseInt(bufferedReader.readLine());
        List<Street> streets = new ArrayList<>();
        String line;
        while (!"End".equals(line = bufferedReader.readLine())) {
            String[] streetParts = line.split(", ");
            String streetName = streetParts[0];
            int carDamage = Integer.parseInt(streetParts[1]);
            int pokemonCount = Integer.parseInt(streetParts[2]);
            int length = Integer.parseInt(streetParts[3]);
            Street street = new Street(streetName, carDamage, pokemonCount, length);
            streets.add(street);
        }

        int[][] values = new int[streets.size() + 1][remainingCarFuel + 1];
        boolean[][] streetsIncluded = new boolean[streets.size() + 1][remainingCarFuel + 1];

        for (int streetIndex = 0; streetIndex < streets.size(); streetIndex++) {
            Street street = streets.get(streetIndex);
            int rowIndex = streetIndex + 1;

            for (int capacity = 0; capacity <= remainingCarFuel; capacity++) {

                int excluding = values[rowIndex - 1][capacity];
                int including = 0;

                if (street.length <= capacity) {
                    including = street.pokemonCount * 10 - street.carDamage + values[rowIndex - 1][capacity - street.length];
                }

                if (including > excluding) {
                    values[rowIndex][capacity] = including;
                    streetsIncluded[rowIndex][capacity] = true;
                } else {
                    values[rowIndex][capacity] = excluding;
                }
            }
        }

        List<Street> solutionItems = new ArrayList<>();

        int capacity = remainingCarFuel;

        for (int itemIndex = streets.size() - 1; itemIndex >= 0; itemIndex--) {
            if (streetsIncluded[itemIndex + 1][capacity]) {
                Street currentItem = streets.get(itemIndex);
                solutionItems.add(currentItem);
                capacity -= currentItem.length;
            }
        }
        List<String> collect = solutionItems.stream()
                .map(x -> x.streetName)
                .collect(Collectors.toList());
        Collections.reverse(collect);
        System.out.println(String.join(" -> ", collect));
        System.out.println("Total pokemons caught -> " + solutionItems.stream()
                .mapToInt(x -> x.pokemonCount).sum());
        System.out.println("Total car damage -> " + solutionItems.stream()
                .mapToInt(x -> x.carDamage)
                .sum());
        System.out.println("Fuel Left -> " + capacity);

    }
}

class Street {
    String streetName;
    int carDamage;
    int pokemonCount;
    int length;

    public Street() {
    }

    public Street(String streetName, int carDamage, int pokemonCount, int length) {
        this.streetName = streetName;
        this.carDamage = carDamage;
        this.pokemonCount = pokemonCount;
        this.length = length;
    }
}
