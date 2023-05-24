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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        Integer[] arr = new Integer[q.size()];
        q.toArray(arr);

        boolean chaotic = false;

        for(int i = 0; i < arr.length; i++){
            if(Math.abs(arr[i] - 2) > i+1){
                chaotic = true;
            }
        }
        if(chaotic){
            System.out.println("Too chaotic");
        }
        else{
            int moves =0;

            for (int i = 0; i < arr.length; i++) {
                for (int j = i-1; j >= arr[i] - 2 && j >= 0; j--) {
                    if (arr[j] > arr[i]){
                        moves++;
                    }
                }
            }
            System.out.println(moves);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
