package com.example;

import java.util.*;

/**
 * Sliding Window Techniques for String Problems
 * 
 * This class demonstrates various sliding window algorithms for solving
 * string problems with constraints, optimized for Java 17.
 */
public class SlidingWindowStrings {
    
    /**
     * Problem 1: Longest Substring Without Repeating Characters
     * 
     * Given a string s, find the length of the longest substring without repeating characters.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(min(m, n)) where m is the size of the charset
     */
    public static int longestSubstringWithoutRepeating(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // Shrink window from left until no duplicates
            while (window.contains(currentChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            
            window.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Problem 2: Longest Substring with At Most K Distinct Characters
     * 
     * Given a string s and an integer k, return the length of the longest substring
     * that contains at most k distinct characters.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int longestSubstringWithKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return 0;
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
            
            // Shrink window if we have more than k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Problem 3: Minimum Window Substring
     * 
     * Given two strings s and t, return the minimum window substring of s such that
     * every character in t (including duplicates) is included in the window.
     * 
     * Time Complexity: O(|s| + |t|)
     * Space Complexity: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) {
            return "";
        }
        
        // Count characters in t
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        int required = targetCount.size();
        int formed = 0;
        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        
        Map<Character, Integer> windowCount = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            windowCount.put(currentChar, windowCount.getOrDefault(currentChar, 0) + 1);
            
            // Check if current character's count matches target
            if (targetCount.containsKey(currentChar) && 
                windowCount.get(currentChar).intValue() == targetCount.get(currentChar).intValue()) {
                formed++;
            }
            
            // Try to contract window from left
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);
                
                // Update minimum window if current is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                // Remove left character from window
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (targetCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < targetCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * Problem 4: Longest Substring with At Least K Repeating Characters
     * 
     * Given a string s and an integer k, return the length of the longest substring
     * of s such that the frequency of each character in this substring is greater than or equal to k.
     * 
     * Time Complexity: O(n^2) in worst case, O(n) on average
     * Space Complexity: O(1) since there are at most 26 characters
     */
    public static int longestSubstringWithKRepeating(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return 0;
        }
        
        int maxLength = 0;
        
        // Try different numbers of unique characters (1 to 26)
        for (int uniqueTarget = 1; uniqueTarget <= 26; uniqueTarget++) {
            Map<Character, Integer> charCount = new HashMap<>();
            int left = 0;
            int right = 0;
            int uniqueCount = 0;
            int validCount = 0; // characters with count >= k
            
            while (right < s.length()) {
                char rightChar = s.charAt(right);
                charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
                
                if (charCount.get(rightChar) == 1) {
                    uniqueCount++;
                }
                if (charCount.get(rightChar) == k) {
                    validCount++;
                }
                
                // Shrink window if we have too many unique characters
                while (uniqueCount > uniqueTarget) {
                    char leftChar = s.charAt(left);
                    if (charCount.get(leftChar) == k) {
                        validCount--;
                    }
                    charCount.put(leftChar, charCount.get(leftChar) - 1);
                    if (charCount.get(leftChar) == 0) {
                        charCount.remove(leftChar);
                        uniqueCount--;
                    }
                    left++;
                }
                
                // Update max length if all characters in window are valid
                if (uniqueCount == validCount) {
                    maxLength = Math.max(maxLength, right - left + 1);
                }
                
                right++;
            }
        }
        
        return maxLength;
    }
    
    /**
     * Problem 5: Find All Anagrams in a String
     * 
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
     * 
     * Time Complexity: O(|s| + |p|)
     * Space Complexity: O(1) since there are at most 26 characters
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }
        
        // Count characters in p
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        int[] windowCount = new int[26];
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            windowCount[s.charAt(right) - 'a']++;
            
            // If window size equals p's length, check if it's an anagram
            if (right - left + 1 == p.length()) {
                if (Arrays.equals(pCount, windowCount)) {
                    result.add(left);
                }
                
                // Slide window
                windowCount[s.charAt(left) - 'a']--;
                left++;
            }
        }
        
        return result;
    }
    
    /**
     * Utility method to print results with explanations
     */
    public static void printResult(String methodName, String input, Object result) {
        System.out.println("Method: " + methodName);
        System.out.println("Input: " + input);
        System.out.println("Result: " + result);
        System.out.println("---");
    }
    
    /**
     * Main method to demonstrate all sliding window techniques
     */
    public static void main(String[] args) {
        System.out.println("=== Sliding Window Techniques for Strings ===\n");
        
        // Test cases
        String testString1 = "abcabcbb";
        String testString2 = "eceba";
        String testString3 = "ADOBECODEBANC";
        String testString4 = "aaabb";
        String testString5 = "cbaebabacd";
        
        // Problem 1: Longest Substring Without Repeating Characters
        printResult("longestSubstringWithoutRepeating", 
                   testString1, 
                   longestSubstringWithoutRepeating(testString1));
        
        // Problem 2: Longest Substring with At Most K Distinct Characters
        printResult("longestSubstringWithKDistinct", 
                   testString2 + " (k=2)", 
                   longestSubstringWithKDistinct(testString2, 2));
        
        // Problem 3: Minimum Window Substring
        printResult("minWindow", 
                   testString3 + " (target: ABC)", 
                   minWindow(testString3, "ABC"));
        
        // Problem 4: Longest Substring with At Least K Repeating Characters
        printResult("longestSubstringWithKRepeating", 
                   testString4 + " (k=3)", 
                   longestSubstringWithKRepeating(testString4, 3));
        
        // Problem 5: Find All Anagrams
        printResult("findAnagrams", 
                   testString5 + " (pattern: abc)", 
                   findAnagrams(testString5, "abc"));
        
        // Additional test cases
        System.out.println("\n=== Additional Test Cases ===");
        
        // Edge cases
        printResult("longestSubstringWithoutRepeating", 
                   "Empty string", 
                   longestSubstringWithoutRepeating(""));
        
        printResult("longestSubstringWithoutRepeating", 
                   "Single character", 
                   longestSubstringWithoutRepeating("a"));
        
        printResult("longestSubstringWithoutRepeating", 
                   "All same characters", 
                   longestSubstringWithoutRepeating("bbbbb"));
        
        // Performance demonstration
        System.out.println("\n=== Performance Test ===");
        String longString = "abcdefghijklmnopqrstuvwxyz".repeat(1000);
        long startTime = System.currentTimeMillis();
        int result = longestSubstringWithoutRepeating(longString);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Long string test (26,000 characters):");
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
