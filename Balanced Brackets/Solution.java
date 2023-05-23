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
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
        // Write your code here
        char arr[] = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();

        //not even number of brackers
        if(s.length() % 2 != 0){
            return "NO";
        }

        for(int i = 0; i< arr.length; i++){
            char cur = arr[i];
            if(cur == '(' || cur == '[' || cur == '{'){
                stack.push(cur);
            }
            else if(!stack.isEmpty()){
                char popChar = stack.pop();

                switch(cur){
                    case ')':
                        if(popChar != '('){
                            return "NO";
                        }
                        else{
                            break;
                        }

                    case ']':
                        if(popChar != '['){
                            return "NO";
                        }
                        else{
                            break;
                        }

                    case '}':
                        if(popChar != '{'){
                            return "NO";
                        }
                        else{
                            break;
                        }
                }
            }
            else{   //Stack is empty
                return "NO";
            }
        }

        if(stack.isEmpty()){
            return "YES";
        }
        else{
            return "NO";
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = isBalanced(s);

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
