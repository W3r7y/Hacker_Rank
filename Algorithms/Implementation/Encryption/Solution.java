import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'encryption' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String encryption(String s) {
        // Write your code here
        int length=s.length();

        double rows=Math.floor(Math.sqrt(length));
        double cols=Math.ceil(Math.sqrt(length));

        int len = 0;
        if(rows > cols){
            len = (int) rows;
        }
        else{
            len = (int) cols;
        }

        String encryptedStr = "";

        for(int i=0; i<len; i++) {
            for(int j=i; j<s.length(); j=(int)(j+cols)) {
                char c = s.charAt(j);
                encryptedStr += c;
            }
            encryptedStr+=" ";
        }
        return encryptedStr;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
