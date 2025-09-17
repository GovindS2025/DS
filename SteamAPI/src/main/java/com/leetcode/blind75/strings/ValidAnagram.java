package com.leetcode.blind75.strings;

import java.util.*;

/**
 * LeetCode 242: Valid Anagram
 * 
 * Problem: Check if two strings are anagrams (same characters, different order).
 * 
 * Approach 1: Sort both strings and compare
 * Approach 2: Count character frequencies
 * 
 * Time Complexity: O(n log n) for sorting, O(n) for counting
 * Space Complexity: O(1) for sorting, O(1) for counting (26 characters max)
 */
public class ValidAnagram {
    
    // Approach 1: Sorting
    public static boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) return false;
        
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        
        return Arrays.equals(sChars, tChars);
    }
    
    // Approach 2: Character Counting (Optimal)
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] count = new int[26];
        
        // Count characters in s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Subtract characters in t
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Visual Example: s="anagram", t="nagaram"
     * 
     * Character counting approach:
     * Initial count: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 
     * After counting s="anagram":
     * count: [3,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0]
     *         a b c d e f g h i j k l m n o p q r s t u v w x y z
     * 
     * After subtracting t="nagaram":
     * count: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 
     * All counts are 0, so they are anagrams.
     */
    
    public static void main(String[] args) {
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";
        
        System.out.println("Test 1: s=\"" + s1 + "\", t=\"" + t1 + "\"");
        System.out.println("Is Anagram: " + isAnagram(s1, t1));
        System.out.println("Expected: true");
        
        System.out.println("\nTest 2: s=\"" + s2 + "\", t=\"" + t2 + "\"");
        System.out.println("Is Anagram: " + isAnagram(s2, t2));
        System.out.println("Expected: false");
    }
}
