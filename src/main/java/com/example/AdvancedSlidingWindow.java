package com.example;

import java.util.*;

/**
 * Advanced Sliding Window Patterns and Optimizations
 * 
 * This class demonstrates more complex sliding window techniques
 * and optimizations for Java 17.
 */
public class AdvancedSlidingWindow {
    
    /**
     * Problem: Longest Substring with Exactly K Distinct Characters
     * 
     * Find the length of the longest substring that has exactly k distinct characters.
     * This is a variation of the "at most k" problem.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int longestSubstringWithExactlyKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return 0;
        }
        
        return longestSubstringWithAtMostKDistinct(s, k) - 
               longestSubstringWithAtMostKDistinct(s, k - 1);
    }
    
    private static int longestSubstringWithAtMostKDistinct(String s, int k) {
        if (k <= 0) return 0;
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
            
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
     * Problem: Permutation in String
     * 
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1.
     * 
     * Time Complexity: O(|s1| + |s2|)
     * Space Complexity: O(1) since there are at most 26 characters
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];
        
        // Count characters in s1
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }
        
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            windowCount[s2.charAt(right) - 'a']++;
            
            // If window size equals s1's length, check if it's a permutation
            if (right - left + 1 == s1.length()) {
                if (Arrays.equals(s1Count, windowCount)) {
                    return true;
                }
                
                // Slide window
                windowCount[s2.charAt(left) - 'a']--;
                left++;
            }
        }
        
        return false;
    }
    
    /**
     * Problem: Substring with Concatenation of All Words
     * 
     * You are given a string s and an array of strings words. All the strings of words
     * are of the same length. Return all starting indices of substring(s) in s that is
     * a concatenation of each word in words exactly once, in any order.
     * 
     * Time Complexity: O(n * m * k) where n = s.length(), m = words.length, k = word.length
     * Space Complexity: O(m)
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) {
            return result;
        }
        
        int wordLength = words[0].length();
        int totalWords = words.length;
        int totalLength = wordLength * totalWords;
        
        if (s.length() < totalLength) {
            return result;
        }
        
        // Count frequency of each word
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        // Try each possible starting position
        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            
            // Check if substring starting at i is a valid concatenation
            while (j < totalWords) {
                int start = i + j * wordLength;
                String word = s.substring(start, start + wordLength);
                
                if (!wordCount.containsKey(word)) {
                    break;
                }
                
                seen.put(word, seen.getOrDefault(word, 0) + 1);
                
                if (seen.get(word) > wordCount.get(word)) {
                    break;
                }
                
                j++;
            }
            
            if (j == totalWords) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * Problem: Longest Repeating Character Replacement
     * 
     * You are given a string s and an integer k. You can choose any character of the string
     * and change it to any other uppercase English letter. You can perform this operation
     * at most k times. Return the length of the longest substring containing the same letter.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) since there are at most 26 characters
     */
    public static int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        int[] charCount = new int[26];
        int left = 0;
        int maxCount = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            charCount[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, charCount[s.charAt(right) - 'A']);
            
            // If current window is invalid, shrink it
            if (right - left + 1 - maxCount > k) {
                charCount[s.charAt(left) - 'A']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Problem: Sliding Window Maximum
     * 
     * You are given an array of integers nums, there is a sliding window of size k
     * which is moving from the very left of the array to the very right. You can only
     * see the k numbers in the window. Each time the sliding window moves right by one position.
     * Return the max sliding window.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // Remove elements outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove elements smaller than current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            // Add to result when window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * Problem: Longest Subarray with Sum Less Than or Equal to Target
     * 
     * Given an array of positive integers nums and an integer target, return the length
     * of the longest subarray whose sum is less than or equal to target.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int longestSubarrayWithSumLessThanOrEqual(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        
        int left = 0;
        int sum = 0;
        int maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            // Shrink window if sum exceeds target
            while (sum > target) {
                sum -= nums[left];
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Utility method to print array results
     */
    public static void printArrayResult(String methodName, String input, int[] result) {
        System.out.println("Method: " + methodName);
        System.out.println("Input: " + input);
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("---");
    }
    
    /**
     * Utility method to print list results
     */
    public static void printListResult(String methodName, String input, List<?> result) {
        System.out.println("Method: " + methodName);
        System.out.println("Input: " + input);
        System.out.println("Result: " + result);
        System.out.println("---");
    }
    
    /**
     * Main method to demonstrate advanced sliding window techniques
     */
    public static void main(String[] args) {
        System.out.println("=== Advanced Sliding Window Techniques ===\n");
        
        // Test cases for advanced problems
        String testString1 = "eceba";
        // String testString2 = "ab"; // Unused variable removed
        String testString3 = "barfoothefoobarman";
        String testString4 = "AABABBA";
        int[] testArray1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] testArray2 = {1, 2, 3, 4, 5};
        
        // Problem 1: Longest Substring with Exactly K Distinct Characters
        System.out.println("1. Longest Substring with Exactly K Distinct Characters");
        printResult("longestSubstringWithExactlyKDistinct", 
                   testString1 + " (k=2)", 
                   longestSubstringWithExactlyKDistinct(testString1, 2));
        
        // Problem 2: Permutation in String
        System.out.println("2. Permutation in String");
        printResult("checkInclusion", 
                   "ab in eidbaooo", 
                   checkInclusion("ab", "eidbaooo"));
        
        // Problem 3: Substring with Concatenation of All Words
        System.out.println("3. Substring with Concatenation of All Words");
        String[] words = {"foo", "bar"};
        printListResult("findSubstring", 
                       testString3 + " with words [foo, bar]", 
                       findSubstring(testString3, words));
        
        // Problem 4: Longest Repeating Character Replacement
        System.out.println("4. Longest Repeating Character Replacement");
        printResult("characterReplacement", 
                   testString4 + " (k=1)", 
                   characterReplacement(testString4, 1));
        
        // Problem 5: Sliding Window Maximum
        System.out.println("5. Sliding Window Maximum");
        printArrayResult("maxSlidingWindow", 
                        Arrays.toString(testArray1) + " (k=3)", 
                        maxSlidingWindow(testArray1, 3));
        
        // Problem 6: Longest Subarray with Sum Less Than or Equal to Target
        System.out.println("6. Longest Subarray with Sum Less Than or Equal to Target");
        printResult("longestSubarrayWithSumLessThanOrEqual", 
                   Arrays.toString(testArray2) + " (target=10)", 
                   longestSubarrayWithSumLessThanOrEqual(testArray2, 10));
        
        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        performanceTest();
    }
    
    private static void printResult(String methodName, String input, Object result) {
        System.out.println("Method: " + methodName);
        System.out.println("Input: " + input);
        System.out.println("Result: " + result);
        System.out.println("---");
    }
    
    private static void performanceTest() {
        // Generate large test data
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String largeString = sb.toString();
        
        // Test performance
        long startTime = System.currentTimeMillis();
        int result1 = longestSubstringWithExactlyKDistinct(largeString, 5);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Performance test on 10,000 character string:");
        System.out.println("Result: " + result1);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }
}
