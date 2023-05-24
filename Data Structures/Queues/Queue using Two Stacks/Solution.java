import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();

            if (type == 1) {
                int value = sc.nextInt();
                queue.enqueue(value);
            }
            else if (type == 2) {
                queue.dequeue();
            }
            else if (type == 3) {
                System.out.println(queue.peek());
            }
        }

        sc.close();
    }
}

class TwoStackQueue<T> {
    Stack<T> stack = new Stack<T>();
    Stack<T> reversedStack = new Stack<T>();

    void enqueue(T item) {
        stack.push(item);
    }

    T dequeue() {
        upsideDown();
        return reversedStack.pop();
    }

    T peek() {
        upsideDown();
        return reversedStack.peek();
    }

    //Checks if reversed stack is empty, and if it is, and moves all the elements in    reversed order from stack to reversed stack
    void upsideDown() {
        if (reversedStack.empty()) {
            while (!stack.empty()) {
                reversedStack.push(stack.pop());
            }
        }
    }
}
