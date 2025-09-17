package com.leetcode.blind75;

import com.leetcode.blind75.arrays.*;
import com.leetcode.blind75.strings.*;

/**
 * Main runner class for LeetCode Blind 75 problems
 * 
 * This class demonstrates all solutions with examples and explanations.
 * Based on the comprehensive guide referenced from:
 * https://www.youtube.com/watch?v=PieZjz2Pyhw&t=4205s
 */
public class LeetCodeBlind75Runner {
    
    public static void main(String[] args) {
        System.out.println("=== LeetCode Blind 75 Problems - Complete Solutions ===\n");
        
        // Arrays Problems
        runArrayProblems();
        
        // Strings Problems
        runStringProblems();
        
        // Summary
        printSummary();
    }
    
    private static void runArrayProblems() {
        System.out.println("📊 ARRAYS PROBLEMS");
        System.out.println("==================\n");
        
        // 1. Two Sum
        System.out.println("1. Two Sum");
        System.out.println("Problem: Find two numbers that add up to target");
        System.out.println("Approach: Hash Map for O(n) time complexity");
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = TwoSum.twoSum(nums1, target1);
        System.out.println("Input: " + java.util.Arrays.toString(nums1) + ", target: " + target1);
        System.out.println("Output: " + java.util.Arrays.toString(result1));
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 2. Best Time to Buy and Sell Stock
        System.out.println("2. Best Time to Buy and Sell Stock");
        System.out.println("Problem: Find maximum profit from buying and selling once");
        System.out.println("Approach: Track minimum price and maximum profit");
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = BestTimeToBuyAndSellStock.maxProfit(prices);
        System.out.println("Input: " + java.util.Arrays.toString(prices));
        System.out.println("Output: " + profit);
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 3. Contains Duplicate
        System.out.println("3. Contains Duplicate");
        System.out.println("Problem: Check if array has duplicate elements");
        System.out.println("Approach: HashSet for O(n) time complexity");
        int[] nums3 = {1, 2, 3, 1};
        boolean hasDuplicate = ContainsDuplicate.containsDuplicate(nums3);
        System.out.println("Input: " + java.util.Arrays.toString(nums3));
        System.out.println("Output: " + hasDuplicate);
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 4. Product of Array Except Self
        System.out.println("4. Product of Array Except Self");
        System.out.println("Problem: Return array where each element is product of all others");
        System.out.println("Approach: Two passes - left products then right products");
        int[] nums4 = {1, 2, 3, 4};
        int[] result4 = ProductOfArrayExceptSelf.productExceptSelf(nums4);
        System.out.println("Input: " + java.util.Arrays.toString(nums4));
        System.out.println("Output: " + java.util.Arrays.toString(result4));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 5. Maximum Subarray
        System.out.println("5. Maximum Subarray (Kadane's Algorithm)");
        System.out.println("Problem: Find contiguous subarray with maximum sum");
        System.out.println("Approach: Kadane's algorithm - track current and maximum sum");
        int[] nums5 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = MaximumSubarray.maxSubArray(nums5);
        System.out.println("Input: " + java.util.Arrays.toString(nums5));
        System.out.println("Output: " + maxSum);
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
    }
    
    private static void runStringProblems() {
        System.out.println("🔤 STRINGS PROBLEMS");
        System.out.println("===================\n");
        
        // 1. Valid Anagram
        System.out.println("1. Valid Anagram");
        System.out.println("Problem: Check if two strings are anagrams");
        System.out.println("Approach: Character counting with array");
        String s1 = "anagram", t1 = "nagaram";
        boolean isAnagram = ValidAnagram.isAnagram(s1, t1);
        System.out.println("Input: s=\"" + s1 + "\", t=\"" + t1 + "\"");
        System.out.println("Output: " + isAnagram);
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 2. Valid Parentheses
        System.out.println("2. Valid Parentheses");
        System.out.println("Problem: Check if string has valid parentheses");
        System.out.println("Approach: Stack to track opening brackets");
        String parentheses = "()[]{}";
        boolean isValid = ValidParentheses.isValid(parentheses);
        System.out.println("Input: \"" + parentheses + "\"");
        System.out.println("Output: " + isValid);
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 3. Longest Substring Without Repeating Characters
        System.out.println("3. Longest Substring Without Repeating Characters");
        System.out.println("Problem: Find length of longest substring without duplicates");
        System.out.println("Approach: Sliding window with HashSet");
        String s3 = "abcabcbb";
        int maxLength = LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s3);
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + maxLength);
        System.out.println("Time: O(n), Space: O(min(m,n))");
        System.out.println();
    }
    
    private static void printSummary() {
        System.out.println("📈 SUMMARY");
        System.out.println("==========\n");
        
        System.out.println("✅ Completed Problems:");
        System.out.println("   • Arrays: 5/15 problems");
        System.out.println("   • Strings: 3/8 problems");
        System.out.println("   • Linked Lists: 0/6 problems");
        System.out.println("   • Trees: 0/15 problems");
        System.out.println("   • Dynamic Programming: 0/15 problems");
        System.out.println("   • Graph: 0/6 problems");
        System.out.println("   • Interval: 0/3 problems");
        System.out.println("   • Matrix: 0/2 problems");
        System.out.println("   • Math: 0/5 problems");
        System.out.println();
        
        System.out.println("🎯 Key Patterns Learned:");
        System.out.println("   • Two Pointers: For pairs and triplets");
        System.out.println("   • Sliding Window: For subarray/substring problems");
        System.out.println("   • Hash Map/Set: For lookup operations");
        System.out.println("   • Stack: For matching brackets");
        System.out.println("   • Kadane's Algorithm: For maximum subarray");
        System.out.println();
        
        System.out.println("⏱️ Time Complexity Patterns:");
        System.out.println("   • O(1): Constant time operations");
        System.out.println("   • O(n): Single pass through data");
        System.out.println("   • O(n log n): Sorting operations");
        System.out.println("   • O(n²): Nested loops");
        System.out.println();
        
        System.out.println("💾 Space Complexity Patterns:");
        System.out.println("   • O(1): Constant extra space");
        System.out.println("   • O(n): Linear extra space");
        System.out.println("   • O(log n): Recursive call stack");
        System.out.println();
        
        System.out.println("🔗 Reference:");
        System.out.println("   YouTube: https://www.youtube.com/watch?v=PieZjz2Pyhw&t=4205s");
        System.out.println("   LeetCode: https://leetcode.com/");
        System.out.println();
        
        System.out.println("🚀 Next Steps:");
        System.out.println("   1. Complete remaining array problems");
        System.out.println("   2. Implement string problems");
        System.out.println("   3. Practice linked list problems");
        System.out.println("   4. Master tree traversal algorithms");
        System.out.println("   5. Learn dynamic programming patterns");
        System.out.println();
        
        System.out.println("Happy Coding! 🎉");
    }
}
