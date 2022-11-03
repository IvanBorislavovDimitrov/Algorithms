package lab.setsToCover;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(set);
        }

        List<int[]> chosenSets = chooseSets(sets, universe);
        System.out.println(String.format("Sets to take: (%d)", chosenSets.size()));
        for (int[] neededSet : chosenSets) {
            System.out.println(Arrays.stream(neededSet).boxed().collect(Collectors.toList()).toString().replace("[", "{ ")
            .replace("]", " }"));
        }
    }

    public static List<int[]> chooseSets(List<int[]> setsArr, int[] universeArr) {
        Set<Integer> universe = Arrays.stream(universeArr).boxed().collect(Collectors.toSet());
        List<LinkedHashSet<Integer>> sets = setsArr.stream()
                .map(setArr -> Arrays.stream(setArr).boxed().sorted(Integer::compareTo).collect(Collectors.toCollection(LinkedHashSet::new)))
                .sorted((s1, s2) -> Integer.compare(s2.size(), s1.size()))
                .collect(Collectors.toList());
        List<LinkedHashSet<Integer>> neededSets = new ArrayList<>();
        for (LinkedHashSet<Integer> set : sets) {
            if (universe.containsAll(set) && !neededSets
                    .stream()
                    .flatMap(Set::stream)
                    .collect(Collectors.toList()).containsAll(set)) {
                neededSets.add(set);
            }
        }

        return neededSets.stream()
                .map(s -> s.stream().mapToInt(Integer::intValue).toArray())
                .collect(Collectors.toList());
    }
}
