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
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
        // Write your code here
        List<Integer> result = new ArrayList<>();

        if(arr == null || arr.isEmpty()){
            return result;
        }

        if(arr.size() == 1){
            result.add(arr.get(0));
            result.add(arr.get(0));
            return result;
        }

        int maxSubArr = arr.get(0);
        int maxBeforeNow = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) > maxBeforeNow + arr.get(i)) {
                maxBeforeNow = arr.get(i);
            }
            else{
                maxBeforeNow = maxBeforeNow + arr.get(i);
            }
            if (maxSubArr < maxBeforeNow) {
                maxSubArr = maxBeforeNow;
            }
        }

        int maxSeq = Integer.MIN_VALUE;
        for(int i=0; i < arr.size(); i++){
            if(arr.get(i) > 0){
                maxSeq += arr.get(i);
            }
            if(maxSeq < arr.get(i)){
                maxSeq = arr.get(i);
            }
        }

        result.add(maxSubArr);
        result.add(maxSeq);

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result.maxSubarray(arr);

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
