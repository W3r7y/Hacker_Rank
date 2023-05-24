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
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int positives = 0;
        int negatives = 0;
        int zeroes = 0;

        for(int num : arr){
            if(num == 0){
                zeroes++;
                continue;
            }
            if(num > 0){
                positives++;
                continue;
            }
            else{
                negatives++;
            }
        }

        double positiveRate = (double)positives / arr.size();
        double negativeRate = (double)negatives / arr.size();
        double zeroRate = (double)zeroes / arr.size();

        System.out.println(String.format("%.6f", positiveRate));
        System.out.println(String.format("%.6f", negativeRate));
        System.out.println(String.format("%.6f", zeroRate));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        plusMinus(arr);

        bufferedReader.close();
    }
}
