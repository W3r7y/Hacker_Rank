public class Solution {

    /* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/

    //Helping function

    boolean checkIfLegal(Node root, int max, int min){
        if(root == null){
            return true;
        }

        if(root.data >= max || root.data <= min){
            return false;
        }

        else return checkIfLegal(root.left, root.data, min) &&
                checkIfLegal(root.right, max, root.data);
    }

    boolean checkBST(Node root) {

        return checkIfLegal(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
}
