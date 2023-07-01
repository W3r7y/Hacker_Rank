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
     * Complete the 'cutTheSticks' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> cutTheSticks(List<Integer> arr) {
        // Write your code here
        List<Integer> answer = new ArrayList<>();

        while(!arr.isEmpty()){
            int min = arr.get(0);
            for(int i=0; i < arr.size(); i++){
                if(arr.get(i) < min){
                    min = arr.get(i);
                }
            }

            for(int j = 0; j < arr.size(); j++){
                arr.set(j, arr.get(j) - min);
            }

            //System.out.println(arr);
            answer.add(arr.size());

            for(int k=0; k < arr.size(); k++){
                if(arr.get(k) == 0){
                    arr.remove(k);
                    k--;
                }
            }

        }

        return answer;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.cutTheSticks(arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
