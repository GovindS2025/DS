package com.leetcode.blind75.strings;

import java.util.*;

/**
 * LeetCode 20: Valid Parentheses
 * 
 * Problem: Check if string has valid parentheses (matching brackets).
 * 
 * Approach: Use stack to track opening brackets
 * 
 * Time Complexity: O(n) - Single pass through string
 * Space Complexity: O(n) - Stack can store up to n characters
 */
public class ValidParentheses {
    
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if (!isMatching(top, c)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    
    /**
     * Visual Example: s = "()[]{}"
     * 
     * Step 1: '(' -> push to stack: ['(']
     * Step 2: ')' -> pop '(', matches -> stack: []
     * Step 3: '[' -> push to stack: ['[']
     * Step 4: ']' -> pop '[', matches -> stack: []
     * Step 5: '{' -> push to stack: ['{']
     * Step 6: '}' -> pop '{', matches -> stack: []
     * 
     * Stack is empty, so valid.
     */
    
    public static void main(String[] args) {
        String[] testCases = {"()", "()[]{}", "(]", "([)]", "{[]}"};
        
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Valid: " + isValid(test));
            System.out.println();
        }
    }
}
