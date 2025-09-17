package com.leetcode.blind75;

import com.leetcode.blind75.arrays.ArraySolutionsStream;
import com.leetcode.blind75.strings.StringSolutionsStream;
import java.util.*;
import java.util.stream.*;

/**
 * Main runner class for LeetCode Blind 75 problems using Functional Programming
 * 
 * This class demonstrates all solutions using Java 8+ Stream API and functional
 * programming principles. Each solution is implemented using functional approaches
 * for better readability and maintainability.
 * 
 * @author LeetCode Blind 75 Solutions
 * @version 1.0
 */
public class FunctionalLeetCodeRunner {
    
    public static void main(String[] args) {
        System.out.println("🚀 LeetCode Blind 75 - Functional Programming Solutions");
        System.out.println("========================================================");
        System.out.println("Using Java 8+ Stream API and Functional Programming Principles\n");
        
        // Run all problem categories
        runArrayProblems();
        runStringProblems();
        
        // Performance analysis
        runPerformanceAnalysis();
        
        // Summary
        printSummary();
    }
    
    /**
     * Demonstrates all Array problems using functional programming
     */
    private static void runArrayProblems() {
        System.out.println("📊 ARRAY PROBLEMS - Functional Solutions");
        System.out.println("========================================\n");
        
        // Test data
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {7, 1, 5, 3, 6, 4};
        int[] nums3 = {1, 2, 3, 1};
        int[] nums4 = {1, 2, 3, 4};
        int[] nums5 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums6 = {2, 3, -2, 4};
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        int[] threeSumNums = {-1, 0, 1, 2, -1, -4};
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] missing = {3, 0, 1};
        
        // 1. Two Sum
        System.out.println("1️⃣ Two Sum (Functional Approach)");
        System.out.println("Problem: Find two numbers that add up to target");
        System.out.println("Approach: Stream API with HashMap for O(n) time complexity");
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: 9");
        System.out.println("Output: " + Arrays.toString(ArraySolutionsStream.twoSum(nums1, 9)));
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 2. Best Time to Buy and Sell Stock
        System.out.println("2️⃣ Best Time to Buy and Sell Stock (Functional Approach)");
        System.out.println("Problem: Find maximum profit from buying and selling once");
        System.out.println("Approach: Stream with reduce operation");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + ArraySolutionsStream.maxProfit(nums2));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 3. Contains Duplicate
        System.out.println("3️⃣ Contains Duplicate (Functional Approach)");
        System.out.println("Problem: Check if array has duplicate elements");
        System.out.println("Approach: Stream with distinct count comparison");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + ArraySolutionsStream.containsDuplicate(nums3));
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 4. Product of Array Except Self
        System.out.println("4️⃣ Product of Array Except Self (Functional Approach)");
        System.out.println("Problem: Return array where each element is product of all others");
        System.out.println("Approach: Two passes using Stream API");
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Output: " + Arrays.toString(ArraySolutionsStream.productExceptSelf(nums4)));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 5. Maximum Subarray (Kadane's Algorithm)
        System.out.println("5️⃣ Maximum Subarray (Functional Approach)");
        System.out.println("Problem: Find contiguous subarray with maximum sum");
        System.out.println("Approach: Stream with reduce operation");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output: " + ArraySolutionsStream.maxSubArray(nums5));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 6. Maximum Product Subarray
        System.out.println("6️⃣ Maximum Product Subarray (Functional Approach)");
        System.out.println("Problem: Find contiguous subarray with maximum product");
        System.out.println("Approach: Stream with reduce operation tracking min and max");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output: " + ArraySolutionsStream.maxProduct(nums5));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 7. Find Minimum in Rotated Sorted Array
        System.out.println("7️⃣ Find Minimum in Rotated Sorted Array (Functional Approach)");
        System.out.println("Problem: Find minimum element in rotated sorted array");
        System.out.println("Approach: Stream with binary search logic");
        System.out.println("Input: " + Arrays.toString(rotated));
        System.out.println("Output: " + ArraySolutionsStream.findMin(rotated));
        System.out.println("Time: O(log n), Space: O(1)");
        System.out.println();
        
        // 8. Search in Rotated Sorted Array
        System.out.println("8️⃣ Search in Rotated Sorted Array (Functional Approach)");
        System.out.println("Problem: Search target in rotated sorted array");
        System.out.println("Approach: Stream with binary search logic");
        System.out.println("Input: " + Arrays.toString(rotated) + ", Target: 0");
        System.out.println("Output: " + ArraySolutionsStream.search(rotated, 0));
        System.out.println("Time: O(log n), Space: O(1)");
        System.out.println();
        
        // 9. 3Sum
        System.out.println("9️⃣ 3Sum (Functional Approach)");
        System.out.println("Problem: Find all unique triplets that sum to zero");
        System.out.println("Approach: Stream with two pointers");
        System.out.println("Input: " + Arrays.toString(threeSumNums));
        System.out.println("Output: " + ArraySolutionsStream.threeSum(threeSumNums));
        System.out.println("Time: O(n²), Space: O(1)");
        System.out.println();
        
        // 10. Container With Most Water
        System.out.println("🔟 Container With Most Water (Functional Approach)");
        System.out.println("Problem: Find two lines that together with x-axis forms container with most water");
        System.out.println("Approach: Stream with two pointers");
        System.out.println("Input: " + Arrays.toString(height));
        System.out.println("Output: " + ArraySolutionsStream.maxArea(height));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 11. Sum of Two Integers
        System.out.println("1️⃣1️⃣ Sum of Two Integers (Functional Approach)");
        System.out.println("Problem: Calculate sum of two integers without using + operator");
        System.out.println("Approach: Bit manipulation with Stream");
        System.out.println("Input: a=1, b=2");
        System.out.println("Output: " + ArraySolutionsStream.getSum(1, 2));
        System.out.println("Time: O(1), Space: O(1)");
        System.out.println();
        
        // 12. Number of 1 Bits
        System.out.println("1️⃣2️⃣ Number of 1 Bits (Functional Approach)");
        System.out.println("Problem: Count number of 1 bits in integer");
        System.out.println("Approach: Stream with bit manipulation");
        System.out.println("Input: 11 (binary: 1011)");
        System.out.println("Output: " + ArraySolutionsStream.hammingWeight(11));
        System.out.println("Time: O(1), Space: O(1)");
        System.out.println();
        
        // 13. Counting Bits
        System.out.println("1️⃣3️⃣ Counting Bits (Functional Approach)");
        System.out.println("Problem: Count 1 bits for each number from 0 to n");
        System.out.println("Approach: Stream with dynamic programming");
        System.out.println("Input: n=5");
        System.out.println("Output: " + Arrays.toString(ArraySolutionsStream.countBits(5)));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 14. Missing Number
        System.out.println("1️⃣4️⃣ Missing Number (Functional Approach)");
        System.out.println("Problem: Find missing number in array containing n distinct numbers from 0 to n");
        System.out.println("Approach: Stream with mathematical formula");
        System.out.println("Input: " + Arrays.toString(missing));
        System.out.println("Output: " + ArraySolutionsStream.missingNumber(missing));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 15. Reverse Bits
        System.out.println("1️⃣5️⃣ Reverse Bits (Functional Approach)");
        System.out.println("Problem: Reverse bits of 32-bit unsigned integer");
        System.out.println("Approach: Stream with bit manipulation");
        System.out.println("Input: 43261596 (binary: 00000010100101000001111010011100)");
        System.out.println("Output: " + ArraySolutionsStream.reverseBits(43261596));
        System.out.println("Time: O(1), Space: O(1)");
        System.out.println();
    }
    
    /**
     * Demonstrates all String problems using functional programming
     */
    private static void runStringProblems() {
        System.out.println("🔤 STRING PROBLEMS - Functional Solutions");
        System.out.println("==========================================\n");
        
        // Test data
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "()[]{}";
        String s3 = "A man, a plan, a canal: Panama";
        String s4 = "abcabcbb";
        String s5 = "babad";
        String s6 = "aaa";
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {"flower","flow","flight"};
        
        // 1. Valid Anagram
        System.out.println("1️⃣ Valid Anagram (Functional Approach)");
        System.out.println("Problem: Check if two strings are anagrams");
        System.out.println("Approach: Stream with character counting");
        System.out.println("Input: s=\"" + s1 + "\", t=\"" + t1 + "\"");
        System.out.println("Output: " + StringSolutionsStream.isAnagram(s1, t1));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 2. Valid Parentheses
        System.out.println("2️⃣ Valid Parentheses (Functional Approach)");
        System.out.println("Problem: Check if string has valid parentheses");
        System.out.println("Approach: Stream with stack simulation");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + StringSolutionsStream.isValid(s2));
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 3. Valid Palindrome
        System.out.println("3️⃣ Valid Palindrome (Functional Approach)");
        System.out.println("Problem: Check if string is palindrome after removing non-alphanumeric characters");
        System.out.println("Approach: Stream with filtering and comparison");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + StringSolutionsStream.isPalindrome(s3));
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 4. Longest Substring Without Repeating Characters
        System.out.println("4️⃣ Longest Substring Without Repeating Characters (Functional Approach)");
        System.out.println("Problem: Find length of longest substring without repeating characters");
        System.out.println("Approach: Stream with sliding window");
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Output: " + StringSolutionsStream.lengthOfLongestSubstring(s4));
        System.out.println("Time: O(n), Space: O(min(m, n))");
        System.out.println();
        
        // 5. Longest Palindromic Substring
        System.out.println("5️⃣ Longest Palindromic Substring (Functional Approach)");
        System.out.println("Problem: Find the longest palindromic substring");
        System.out.println("Approach: Stream with expand around centers");
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("Output: \"" + StringSolutionsStream.longestPalindrome(s5) + "\"");
        System.out.println("Time: O(n²), Space: O(1)");
        System.out.println();
        
        // 6. Palindromic Substrings
        System.out.println("6️⃣ Palindromic Substrings (Functional Approach)");
        System.out.println("Problem: Count number of palindromic substrings");
        System.out.println("Approach: Stream with expand around centers");
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("Output: " + StringSolutionsStream.countSubstrings(s6));
        System.out.println("Time: O(n²), Space: O(1)");
        System.out.println();
        
        // 7. Group Anagrams
        System.out.println("7️⃣ Group Anagrams (Functional Approach)");
        System.out.println("Problem: Group strings that are anagrams of each other");
        System.out.println("Approach: Stream with grouping by sorted characters");
        System.out.println("Input: " + Arrays.toString(strs1));
        System.out.println("Output: " + StringSolutionsStream.groupAnagrams(strs1));
        System.out.println("Time: O(n * m log m), Space: O(n * m)");
        System.out.println();
        
        // 8. Longest Common Prefix
        System.out.println("8️⃣ Longest Common Prefix (Functional Approach)");
        System.out.println("Problem: Find longest common prefix among strings");
        System.out.println("Approach: Stream with character comparison");
        System.out.println("Input: " + Arrays.toString(strs2));
        System.out.println("Output: \"" + StringSolutionsStream.longestCommonPrefix(strs2) + "\"");
        System.out.println("Time: O(n * m), Space: O(1)");
        System.out.println();
        
        // 9. String Compression
        System.out.println("9️⃣ String Compression (Functional Approach)");
        System.out.println("Problem: Compress string by replacing repeated characters with count");
        System.out.println("Approach: Stream with grouping and counting");
        System.out.println("Input: \"aabcccccaaa\"");
        System.out.println("Output: \"" + StringSolutionsStream.compress("aabcccccaaa") + "\"");
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 10. Reverse Words
        System.out.println("🔟 Reverse Words (Functional Approach)");
        System.out.println("Problem: Reverse the order of words in a string");
        System.out.println("Approach: Stream with splitting and reversing");
        System.out.println("Input: \"the sky is blue\"");
        System.out.println("Output: \"" + StringSolutionsStream.reverseWords("the sky is blue") + "\"");
        System.out.println("Time: O(n), Space: O(n)");
        System.out.println();
        
        // 11. Valid Number
        System.out.println("1️⃣1️⃣ Valid Number (Functional Approach)");
        System.out.println("Problem: Check if string is a valid number");
        System.out.println("Approach: Stream with regex validation");
        System.out.println("Input: \"2e10\"");
        System.out.println("Output: " + StringSolutionsStream.isNumber("2e10"));
        System.out.println("Time: O(n), Space: O(1)");
        System.out.println();
        
        // 12. Minimum Window Substring
        System.out.println("1️⃣2️⃣ Minimum Window Substring (Functional Approach)");
        System.out.println("Problem: Find minimum window in s that contains all characters in t");
        System.out.println("Approach: Stream with sliding window");
        System.out.println("Input: s=\"ADOBECODEBANC\", t=\"ABC\"");
        System.out.println("Output: \"" + StringSolutionsStream.minWindow("ADOBECODEBANC", "ABC") + "\"");
        System.out.println("Time: O(|s| + |t|), Space: O(|s| + |t|)");
        System.out.println();
        
        // 13. Find All Anagrams
        System.out.println("1️⃣3️⃣ Find All Anagrams (Functional Approach)");
        System.out.println("Problem: Find all start indices of p's anagrams in s");
        System.out.println("Approach: Stream with sliding window");
        System.out.println("Input: s=\"cbaebabacd\", p=\"abc\"");
        System.out.println("Output: " + StringSolutionsStream.findAnagrams("cbaebabacd", "abc"));
        System.out.println("Time: O(|s| + |p|), Space: O(1)");
        System.out.println();
    }
    
    /**
     * Runs performance analysis on functional solutions
     */
    private static void runPerformanceAnalysis() {
        System.out.println("⚡ PERFORMANCE ANALYSIS");
        System.out.println("=======================\n");
        
        // Test data
        int[] largeArray = IntStream.range(0, 10000).toArray();
        String largeString = "abcdefghijklmnopqrstuvwxyz".repeat(1000);
        
        // Array performance tests
        System.out.println("Array Performance Tests:");
        System.out.println("Array size: " + largeArray.length);
        
        // Two Sum performance
        long startTime = System.nanoTime();
        ArraySolutionsStream.twoSum(largeArray, 19998);
        long endTime = System.nanoTime();
        System.out.println("Two Sum (Functional): " + (endTime - startTime) + " ns");
        
        // Contains Duplicate performance
        startTime = System.nanoTime();
        ArraySolutionsStream.containsDuplicate(largeArray);
        endTime = System.nanoTime();
        System.out.println("Contains Duplicate (Functional): " + (endTime - startTime) + " ns");
        
        // String performance tests
        System.out.println("\nString Performance Tests:");
        System.out.println("String length: " + largeString.length());
        
        // Longest Substring performance
        startTime = System.nanoTime();
        StringSolutionsStream.lengthOfLongestSubstring(largeString);
        endTime = System.nanoTime();
        System.out.println("Longest Substring (Functional): " + (endTime - startTime) + " ns");
        
        // Valid Palindrome performance
        startTime = System.nanoTime();
        StringSolutionsStream.isPalindrome(largeString);
        endTime = System.nanoTime();
        System.out.println("Valid Palindrome (Functional): " + (endTime - startTime) + " ns");
        
        System.out.println();
    }
    
    /**
     * Prints summary of all solutions
     */
    private static void printSummary() {
        System.out.println("📈 SUMMARY");
        System.out.println("==========\n");
        
        System.out.println("✅ Completed Functional Solutions:");
        System.out.println("   • Arrays: 15/15 problems");
        System.out.println("   • Strings: 13/13 problems");
        System.out.println("   • Linked Lists: 0/6 problems");
        System.out.println("   • Trees: 0/15 problems");
        System.out.println("   • Dynamic Programming: 0/15 problems");
        System.out.println("   • Graph: 0/6 problems");
        System.out.println("   • Interval: 0/3 problems");
        System.out.println("   • Matrix: 0/2 problems");
        System.out.println("   • Math: 0/5 problems");
        System.out.println();
        
        System.out.println("🎯 Functional Programming Patterns Used:");
        System.out.println("   • Stream API: For data processing pipelines");
        System.out.println("   • Lambda Expressions: For concise function definitions");
        System.out.println("   • Method References: For referencing existing methods");
        System.out.println("   • Optional: For handling null values safely");
        System.out.println("   • Collectors: For accumulating stream elements");
        System.out.println("   • Functional Interfaces: Predicate, Function, Consumer, Supplier");
        System.out.println();
        
        System.out.println("⏱️ Time Complexity Patterns:");
        System.out.println("   • O(1): Constant time operations");
        System.out.println("   • O(log n): Binary search operations");
        System.out.println("   • O(n): Single pass through data");
        System.out.println("   • O(n log n): Sorting operations");
        System.out.println("   • O(n²): Nested operations");
        System.out.println();
        
        System.out.println("💾 Space Complexity Patterns:");
        System.out.println("   • O(1): Constant extra space");
        System.out.println("   • O(n): Linear extra space");
        System.out.println("   • O(log n): Recursive call stack");
        System.out.println();
        
        System.out.println("🚀 Benefits of Functional Programming:");
        System.out.println("   • Readability: Code is more declarative and easier to understand");
        System.out.println("   • Maintainability: Less boilerplate code and fewer bugs");
        System.out.println("   • Composability: Functions can be easily combined and reused");
        System.out.println("   • Parallelism: Easy to parallelize operations when needed");
        System.out.println("   • Immutability: Safer code with fewer side effects");
        System.out.println();
        
        System.out.println("🔗 Reference:");
        System.out.println("   YouTube: https://www.youtube.com/watch?v=PieZjz2Pyhw&t=4205s");
        System.out.println("   LeetCode: https://leetcode.com/");
        System.out.println("   Java Stream API: https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html");
        System.out.println();
        
        System.out.println("🎉 Happy Functional Programming!");
        System.out.println("Remember: The best solution is the one that balances readability, maintainability, and performance!");
    }
}
