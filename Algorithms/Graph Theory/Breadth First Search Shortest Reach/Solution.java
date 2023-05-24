import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class Solution {

    /*
     * Complete the 'bfs' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. 2D_INTEGER_ARRAY edges
     *  4. INTEGER s
     */

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        boolean[] visited = new boolean[n]; // array to track visited nodes
        int[] distances = new int[n];

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s-1] = true;

        queue.add(s);

        while (!queue.isEmpty()) {
            // Dequeue a node from the queue
            int node = queue.poll();

            // Get all adjacent nodes of the dequeued node
            // If an adjacent node has not been visited, mark it as visited and enqueue it
            for(List<Integer> edge : edges){
                if(edge.contains(node)){
                    if(edge.get(0) == node){
                        if(visited[edge.get(1) - 1] == false){
                            queue.add(edge.get(1));
                            visited[edge.get(1) - 1] = true;
                            distances[edge.get(1) - 1] = distances[node-1] + 6;
                        }
                    }
                    if(edge.get(1) == node){
                        if(visited[edge.get(0) - 1] == false){
                            queue.add(edge.get(0));
                            visited[edge.get(0) - 1] = true;
                            distances[edge.get(0) - 1] = distances[node-1] + 6;
                        }
                    }
                }
            }
        }
        for(int i=0; i<distances.length; i++){
            if(distances[i] == 0){
                distances[i] = -1 ;
            }
        }
        List<Integer> distList = new ArrayList<>();
        for(int i=0; i<distances.length; i++){
            if(i == s - 1){
                continue;
            }
            else{
                distList.add(distances[i]);
            }
        }

        return distList;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = Result.bfs(n, m, edges, s);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
