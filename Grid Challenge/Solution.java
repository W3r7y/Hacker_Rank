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
     * Complete the 'gridChallenge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static String gridChallenge(List<String> grid) {
        // Write your code here
        List<String> sortedGrid = new ArrayList<String>();

        int length = 0;

        for(String row: grid){
            char rowOfChars[] = row.toCharArray();
            length = rowOfChars.length;
            Arrays.sort(rowOfChars);
            String sortedRow = new String(rowOfChars);
            sortedGrid.add(sortedRow);
        }


        for(int i = 0; i < length ; i++){
            for(int j=1, k=0 ; j < sortedGrid.size(); j++, k++){
                char firstRow[] = sortedGrid.get(k).toCharArray();
                char secondRow[] = sortedGrid.get(j).toCharArray();
                if(firstRow[i] > secondRow[i]){
                    return "NO";
                }
            }
        }

        return "YES";
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                String result = gridChallenge(grid);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
