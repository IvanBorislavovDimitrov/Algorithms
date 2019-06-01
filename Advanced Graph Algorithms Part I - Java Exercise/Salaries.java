import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Salaries {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        int employeesCount = Integer.parseInt(bufferedReader.readLine());
        graph = new ArrayList[employeesCount];
        visited = new boolean[employeesCount];
        salaries = new long[employeesCount];

        for (int i = 0; i < employeesCount; i++) {
            if (graph[i] == null) {
                graph[i] = new ArrayList<>();
            }
            String children = bufferedReader.readLine();
            for (int j = 0; j < employeesCount; j++) {
                if (children.charAt(j) == 'Y') {
                    graph[i].add(j);
                }
            }
        }

        for (int i = 0; i < employeesCount; i++) {
            calculateSalaries(i);
        }
        System.out.println(Arrays.stream(salaries).sum());
    }

    private static void calculateSalaries(int vertex) {
        if (salaries[vertex] != 0 || visited[vertex]) {
            return;
        }
        if (graph[vertex].size() == 0) {
            salaries[vertex] = 1;
            return;
        }
        visited[vertex] = true;
        long sum = 0;
        for (Integer child : graph[vertex]) {
            if (salaries[child] == 0) {
                calculateSalaries(child);
            }
            sum += salaries[child];
        }
        salaries[vertex] = sum;
    }
}
