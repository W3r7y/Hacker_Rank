import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] c) {

        Arrays.sort(c);
        int numOfFlowers = c.length;
        int minimumCost = 0;

        int loops = 0;
        if(c.length % k == 0){
            loops = c.length / k;
        }
        else{
            loops = (int)(c.length/k) +1;
        }

        int curLoop = 1;
        int count = 0;

        while(loops != 0 && numOfFlowers > 0){
            minimumCost +=  curLoop * c[numOfFlowers-1];
            numOfFlowers --;
            count++;
            if(count % k == 0){
                loops--;
                curLoop++;
            }
        }

        return minimumCost;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
