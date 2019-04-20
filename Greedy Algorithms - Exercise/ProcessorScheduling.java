import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ProcessorScheduling {

    private static class Task {
        int value;
        int index;

        public Task(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return value + " => " + index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tasksCount = Integer.parseInt(bufferedReader.readLine().split("\\s+")[1]);
        Map<Integer, Task> tasks = new LinkedHashMap<>();
        for (int i = 0; i < tasksCount; i++) {
            String[] valueDeadline = bufferedReader.readLine().split(" - ");
            tasks.put(Integer.parseInt(valueDeadline[0]), new Task(Integer.parseInt(valueDeadline[1]), i + 1));
        }
        List<Pair<Integer, Task>> orderedTasks = new ArrayList<>();
        AtomicInteger day = new AtomicInteger(1);
        while (!tasks.isEmpty()) {
            Map.Entry<Integer, Task> currentBest = getCurrentTop(tasks, day.get());

            orderedTasks.add(new Pair<>(currentBest.getKey(), currentBest.getValue()));
            tasks.remove(currentBest.getKey());
            tasks = tasks.entrySet()
                    .stream()
                    .filter(x -> x.getValue().value > day.get())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            day.addAndGet(1);
        }

        orderedTasks.sort((t1, t2) -> {
            if (t1.getValue().value == t2.getValue().value) {
                return Integer.compare(t2.getKey(), t1.getKey());
            }

            return Integer.compare(t1.getValue().value, t2.getValue().value);
        });

        System.out.println("Optimal schedule: " + getSolution(orderedTasks));
        System.out.println("Total value: " + getTotalValue(orderedTasks));
    }

    private static Integer getTotalValue(List<Pair<Integer, Task>> orderedTasks) {
        return orderedTasks.stream()
                .map(Pair::getKey)
                .reduce(0, (x1, x2) -> x1 + x2);
    }

    private static String getSolution(List<Pair<Integer, Task>> orderedTasks) {
        return orderedTasks.stream()
                .map(x -> x.getValue().index)
                .map(String::valueOf)
                .collect(Collectors.joining(" -> "));
    }

    private static Map.Entry<Integer, Task> getCurrentTop(Map<Integer, Task> map, int day) {
        return map.entrySet().stream()
                .filter(x -> x.getValue().value == day)
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .orElse(map.entrySet().stream()
                        .max(Comparator.comparingInt(Map.Entry::getKey))
                        .orElse(null));
    }
}
