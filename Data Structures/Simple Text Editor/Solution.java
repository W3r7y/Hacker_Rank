import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in =new Scanner(System.in);
        String s = "";

        Stack<String>  stack= new Stack<>();


        int n = in.nextInt();

        for(int i=0; i<n; i++){
            int type = in.nextInt();
            switch(type){
                case 1:
                    String str = in.next();
                    stack.push(s);
                    s = s.concat(str);
                    break;

                case 2:
                    int k = in.nextInt();
                    stack.push(s);
                    s = s.substring(0, s.length() - k);
                    break;

                case 3:
                    int num = in.nextInt();
                    System.out.println(s.charAt(num-1));
                    break;

                case 4:
                    if(!stack.isEmpty()){
                        s = stack.pop();
                    }
                    break;
            }
        }

        in.close();

    }
}

