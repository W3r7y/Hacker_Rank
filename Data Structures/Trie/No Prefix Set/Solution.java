import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.*;

class Trie {
    private static final int ALPHABET_SIZE = 'j' - 'a' + 1;

    //  Represents a node in the Trie data structure.
    class TrieNode {
        TrieNode[] children;
        char key;
        int wordCount = 0;
        int prefixCount = 0;

        TrieNode(char key) {
            this.key = key;
            this.children = new TrieNode[ALPHABET_SIZE];
        }

        TrieNode getChild(char key) {
            return children[getIndex(key)];
        }

        void setChild(char key, TrieNode node) {
            children[getIndex(key)] = node;
        }

        int getIndex(char ch) {
            return ch - 'a';
        }

        boolean hasChild(char key) {
            return getChild(key) != null;
        }
    }

    private TrieNode root = new TrieNode('*');

    public boolean insertWord(String word) {
        return insertWord(word, root);
    }

    private boolean insertWord(String word, TrieNode parent) {
        parent.prefixCount++;

        // If the word length is 0 and the parent node has already been marked as a word,
        // it means the current word is a prefix of an existing word in the Trie.
        if (word.length() >= 0 && parent.wordCount > 0) {
            return false;
        }

        // If the word has been fully processed, mark the parent node as a word and return true.
        if (word.length() == 0) {
            // If the parent node has more than one word as a prefix, it violates the prefix property.
            if (parent.prefixCount > 1) {
                return false;
            }

            parent.wordCount++;
            return true;
        }

        char ch = word.charAt(0);
        TrieNode next = parent.getChild(ch);
        if (next == null) {
            next = new TrieNode(ch);
            parent.setChild(ch, next);
        }
        return insertWord(word.substring(1), next);
    }
}

class Result {
    /*
     * Complete the 'checkPrefix' function below.
     *
     * The function accepts a list of words as a parameter.
     */
    public static void checkPrefix(List<String> words) {
        boolean isGoodSet = true;
        Trie trie = new Trie();

        for (String word : words) {
            isGoodSet = trie.insertWord(word);
            if (!isGoodSet) {
                System.out.println("BAD SET");
                System.out.println(word);
                return;
            }
        }

        System.out.println("GOOD SET");
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = bufferedReader.readLine();
            words.add(word);
        }

        Result.checkPrefix(words);

        bufferedReader.close();
    }
}
