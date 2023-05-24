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
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
        // Write your code here
        String armyClock = s ;
        String preLast = s.substring((s.length() - 2), s.length()-1);

        if(preLast.equals("A")){
            if(s.substring(0,2).equals("12")){
                armyClock = "00" + s.substring(2, s.length()-2);
            }
            else{
                armyClock = s.substring(0, s.length()-2);
            }
        }
        else{   //equals P
            if(armyClock.substring(0,2).equals("12")){
                armyClock = s.substring(0, s.length()-2);
            }
            else{
                String hours = s.substring(0, 2);
                int armyHours = Integer.parseInt(hours);
                armyHours += 12;
                armyHours = armyHours % 24;
                if(armyHours == 0){
                    hours = "00";
                }
                else{
                    hours = Integer.toString(armyHours);
                }
                armyClock = hours.concat(s.substring(2, s.length()-2));
            }
        }

        return armyClock;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
