
import java.util.*;

public class CycleDetection {
    static final int WHITE = 0, GREY = 1, BLACK = 2;

    // DFS function
    public static boolean dfs(int node, List<List<Integer>> graph, int[] color) {
        color[node] = GREY;

        for (int nei : graph.get(node)) {
            if (color[nei] == GREY) return true; // cycle found
            if (color[nei] == WHITE && dfs(nei, graph, color)) return true;
        }

        color[node] = BLACK;
        return false;
    }

    // Cycle detection driver
    public static boolean hasCycle(List<List<Integer>> graph, int n) {
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            if (color[i] == WHITE && dfs(i, graph, color)) {
                return true;
            }
        }
        return false;
    }

    // Main method (example: GitHub workflow graph)
    public static void main(String[] args) {
        int n = 4; // build, test, integration, deploy

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // build → test
        graph.get(0).add(1);
        // test → integration
        graph.get(1).add(2);
        // integration → build (cycle)
        graph.get(2).add(0);
        // deploy → build
        graph.get(3).add(0);

        boolean result = hasCycle(graph, n);

        if (result)
            System.out.println("Cycle detected in workflow ❌");
        else
            System.out.println("No cycle found ✔");
    }
}

