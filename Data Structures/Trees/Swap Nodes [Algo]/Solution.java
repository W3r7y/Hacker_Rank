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

class Result {

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */

    static class Node {
        int value;
        int level;
        Node leftChild;
        Node rightChild;

        public Node(int value, int level, Node leftChild, Node rightChild) {
            this.value = value;
            this.level = level;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    static void inorderTraversal(Node currentNode, List<Integer> result) {
        if (currentNode == null) {
            return;
        }

        inorderTraversal(currentNode.leftChild, result);
        result.add(currentNode.value);
        inorderTraversal(currentNode.rightChild, result);
    }

    static void swapNodesAtDepth(Node currentNode, int currentDepth, int k) {
        if (currentNode == null) {
            return;
        }

        swapNodesAtDepth(currentNode.leftChild, currentDepth + 1, k);
        swapNodesAtDepth(currentNode.rightChild, currentDepth + 1, k);

        if (currentDepth >= k && currentDepth % k == 0) {
            Node tempNode = currentNode.leftChild;
            currentNode.leftChild = currentNode.rightChild;
            currentNode.rightChild = tempNode;
        }
    }

    static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        int numNodes = indexes.size();
        int numQueries = queries.size();
        List<List<Integer>> resultList = new ArrayList<>();

        Node root = new Node(1, 1, null, null);
        Node currentNode = root;

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(currentNode);

        int nodeCount = 0;
        while (nodeCount < numNodes) {
            currentNode = nodeQueue.poll();
            int leftChildIndex = indexes.get(nodeCount).get(0);
            int rightChildIndex = indexes.get(nodeCount).get(1);

            if(leftChildIndex == -1){
                currentNode.leftChild = null;
            }
            else{
                currentNode.leftChild = new Node(leftChildIndex, currentNode.level + 1, null, null);
            }

            if(rightChildIndex == -1){
                currentNode.rightChild = null;
            }
            else{
                currentNode.rightChild =new Node(rightChildIndex, currentNode.level + 1, null, null);
            }

            if (currentNode.leftChild != null &&
                    currentNode.leftChild.value != -1) {

                nodeQueue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null &&
                    currentNode.rightChild.value != -1) {

                nodeQueue.offer(currentNode.rightChild);
            }

            nodeCount++;
        }

        for (int i = 0; i < numQueries; i++) {
            swapNodesAtDepth(root, 1, queries.get(i));
            List<Integer> traversalResult = new ArrayList<>();
            inorderTraversal(root, traversalResult);
            resultList.add(traversalResult);
        }

        return resultList;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
