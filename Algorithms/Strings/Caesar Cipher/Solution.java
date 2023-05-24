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
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
        // Write your code here
        final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String encryptedString = "";

        for (int i = 0; i < s. length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetter(c)){
                if(Character.isLowerCase(c)){
                    int position = lowerCase.indexOf(c);
                    char encryptedChar = lowerCase.charAt((position + k)% 26);
                    encryptedString += encryptedChar;
                }
                else{
                    int position = upperCase.indexOf(c);
                    char encryptedChar = upperCase.charAt((position + k)% 26);
                    encryptedString += encryptedChar;
                }
            }else{
                encryptedString += c;
            }
        }

        return encryptedString;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
