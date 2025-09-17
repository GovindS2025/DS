package com.leetcode.blind75.strings;

import java.util.*;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 * 
 * Problem: Find length of longest substring without repeating characters.
 * 
 * Approach: Sliding window with HashSet
 * 
 * Time Complexity: O(n) - Each character visited at most twice
 * Space Complexity: O(min(m,n)) - m is charset size, n is string length
 */
public class LongestSubstringWithoutRepeatingCharacters {
    
    public static int lengthOfLongestSubstring(String s) {
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
     * Visual Example: s = "abcabcbb"
     * 
     * Step 1: [a]bcabcbb, window={a}, maxLength=1
     * Step 2: [ab]cabcbb, window={a,b}, maxLength=2
     * Step 3: [abc]abcbb, window={a,b,c}, maxLength=3
     * Step 4: a[bc]abcbb, window={b,c}, maxLength=3 (removed 'a')
     * Step 5: a[bca]bcbb, window={b,c,a}, maxLength=3
     * Step 6: ab[ca]bcbb, window={c,a}, maxLength=3 (removed 'b')
     * Step 7: abc[ab]cbb, window={a,b}, maxLength=3 (removed 'c')
     * Step 8: abca[b]cbb, window={b}, maxLength=3 (removed 'a')
     * 
     * Result: 3
     */
    
    public static void main(String[] args) {
        String[] testCases = {"abcabcbb", "bbbbb", "pwwkew", ""};
        
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Length: " + lengthOfLongestSubstring(test));
            System.out.println();
        }
    }
}
