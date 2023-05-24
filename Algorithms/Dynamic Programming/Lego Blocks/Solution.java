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

    public static int legoBlocks(int n, int m) {

        // Most of the parameters are long to prevent integer overflow. Otherwise some cases fails.

        long[] combinationsForRow = new long[1001]; // because n, m <= 1000
        final int MOD = 1000000007;
        // index represents the width, value represents number of possible combinations.
        combinationsForRow[0] = 1;  // no blocks - 1 combination
        combinationsForRow[1] = 1;  // 1 block type 1;
        combinationsForRow[2] = 2;  // 2 blocks of type 1, or 1 block of type 2;
        combinationsForRow[3] = 4;
        combinationsForRow[4] = 8;

        // Calculation of all variations
        for (int i = 5; i < 1001; i++) {
            combinationsForRow[i] = (combinationsForRow[i - 1] + combinationsForRow[i - 2]
                    + combinationsForRow[i - 3] + combinationsForRow[i - 4]) % MOD;
        }

        long[] s = new long[1001]; // Store the number of valid wall formations
        long[] p = new long[1001]; // Store the power of combinationsForRow[i] raised to n

        s[0] = 1; // Base case: Empty wall has one valid formation
        s[1] = 1; // Base case: Wall with one column has one valid formation

        p[0] = 1; // Base case: combinationsForRow[0]^n = 1
        for (int i = 1; i <= m; i++) {
            p[i] = moduloPower(combinationsForRow[i], n, MOD); // Calculate combinationsForRow[i] raised to n modulo MOD
        }

        for (int i = 2; i <= m; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                // Sum the valid formations from previous columns
                sum +=(s[j] * p[i - j]) % MOD;
                sum %= MOD;
            }

            s[i] = (p[i] - sum + MOD) % MOD; // Calculate the valid formations for column i
        }

        return (int)s[m]; // Return the number of valid wall formations for the given dimensions
    }

    public static long moduloPower(long a, int n, long mod) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        long result = 1;
        long base = a % mod;

        while (n > 0) {
            if (n % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            n /= 2;
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = legoBlocks(n, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
