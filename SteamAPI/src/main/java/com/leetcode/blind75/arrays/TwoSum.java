package com.leetcode.blind75.arrays;

import java.util.*;

/**
 * LeetCode 1: Two Sum
 * 
 * Problem Statement:
 * Given an array of integers nums and an integer target, return indices of the two numbers
 * such that they add up to target. You may assume that each input would have exactly one
 * solution, and you may not use the same element twice. You can return the answer in any order.
 * 
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * 
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * 
 * Constraints:
 * - 2 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 * - Only one valid answer exists.
 */
public class TwoSum {
    
    /**
     * Approach 1: Brute Force
     * 
     * Algorithm:
     * 1. For each element at index i, check all elements after it
     * 2. If nums[i] + nums[j] == target, return [i, j]
     * 3. Continue until solution is found
     * 
     * Time Complexity: O(n²) - We iterate through the array twice
     * Space Complexity: O(1) - Only using constant extra space
     * 
     * Why O(n²)? Because for each element (n), we check all remaining elements (n-1, n-2, ...)
     * Total operations: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 = O(n²)
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0]; // No solution found
    }
    
    /**
     * Approach 2: Hash Map (Optimal)
     * 
     * Algorithm:
     * 1. Create a HashMap to store value -> index mapping
     * 2. For each element, calculate complement = target - current_element
     * 3. If complement exists in map, return [map.get(complement), current_index]
     * 4. Otherwise, store current element and its index in map
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(n) - HashMap can store up to n elements
     * 
     * Why O(n)? We visit each element exactly once, and HashMap operations are O(1) on average
     * Why O(n) space? In worst case, we might store all n elements in the HashMap
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            
            numToIndex.put(nums[i], i);
        }
        
        return new int[0]; // No solution found
    }
    
    /**
     * Approach 3: Two Pointers (Alternative - requires sorted array)
     * 
     * Note: This approach requires the array to be sorted first, which changes the indices.
     * It's included for educational purposes but not suitable for this problem as we need original indices.
     * 
     * Algorithm:
     * 1. Sort the array (but this changes indices, so we need to store original indices)
     * 2. Use two pointers: left at start, right at end
     * 3. If sum < target, move left pointer right
     * 4. If sum > target, move right pointer left
     * 5. If sum == target, return indices
     * 
     * Time Complexity: O(n log n) - Due to sorting
     * Space Complexity: O(n) - For storing original indices
     */
    public static int[] twoSumTwoPointers(int[] nums, int target) {
        // Create array of pairs (value, original_index)
        int[][] indexedNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            indexedNums[i] = new int[]{nums[i], i};
        }
        
        // Sort by value
        Arrays.sort(indexedNums, (a, b) -> Integer.compare(a[0], b[0]));
        
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int sum = indexedNums[left][0] + indexedNums[right][0];
            
            if (sum == target) {
                return new int[]{indexedNums[left][1], indexedNums[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[0]; // No solution found
    }
    
    /**
     * Visual representation of the Hash Map approach
     * 
     * Example: nums = [2,7,11,15], target = 9
     * 
     * Step 1: i=0, nums[0]=2, complement=9-2=7
     *         HashMap: {}
     *         complement 7 not found, add (2,0) to map
     *         HashMap: {2:0}
     * 
     * Step 2: i=1, nums[1]=7, complement=9-7=2
     *         HashMap: {2:0}
     *         complement 2 found at index 0
     *         Return [0,1]
     * 
     * Visual Diagram:
     * 
     * nums: [2, 7, 11, 15]
     *        ↑   ↑
     *        i=0 i=1
     * 
     * target = 9
     * 
     * HashMap after Step 1:
     * Key: 2, Value: 0
     * 
     * When i=1:
     * complement = 9 - 7 = 2
     * 2 exists in HashMap at index 0
     * Solution: [0, 1]
     */
    
    /**
     * Test cases and examples
     */
    public static void main(String[] args) {
        // Test Case 1: Basic example
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("Test 1 - nums: " + Arrays.toString(nums1) + ", target: " + target1);
        System.out.println("Result: " + Arrays.toString(result1));
        System.out.println("Expected: [0, 1]");
        System.out.println();
        
        // Test Case 2: Different order
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("Test 2 - nums: " + Arrays.toString(nums2) + ", target: " + target2);
        System.out.println("Result: " + Arrays.toString(result2));
        System.out.println("Expected: [1, 2]");
        System.out.println();
        
        // Test Case 3: Same elements
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = twoSum(nums3, target3);
        System.out.println("Test 3 - nums: " + Arrays.toString(nums3) + ", target: " + target3);
        System.out.println("Result: " + Arrays.toString(result3));
        System.out.println("Expected: [0, 1]");
        System.out.println();
        
        // Test Case 4: Negative numbers
        int[] nums4 = {-1, -2, -3, -4, -5};
        int target4 = -8;
        int[] result4 = twoSum(nums4, target4);
        System.out.println("Test 4 - nums: " + Arrays.toString(nums4) + ", target: " + target4);
        System.out.println("Result: " + Arrays.toString(result4));
        System.out.println("Expected: [2, 4]");
        System.out.println();
        
        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        int[] largeArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = i;
        }
        int largeTarget = 1998; // 999 + 999
        
        long startTime = System.currentTimeMillis();
        int[] resultBrute = twoSumBruteForce(largeArray, largeTarget);
        long endTime = System.currentTimeMillis();
        System.out.println("Brute Force: " + (endTime - startTime) + "ms");
        
        startTime = System.currentTimeMillis();
        int[] resultOptimal = twoSum(largeArray, largeTarget);
        endTime = System.currentTimeMillis();
        System.out.println("Hash Map: " + (endTime - startTime) + "ms");
    }
    
    /**
     * Edge Cases to Consider:
     * 
     * 1. Array with only 2 elements
     * 2. Array with duplicate elements
     * 3. Array with negative numbers
     * 4. Array with zeros
     * 5. Target is zero
     * 6. Large numbers (overflow considerations)
     * 7. Empty array (though constraints say length >= 2)
     * 
     * Follow-up Questions:
     * 1. What if there are multiple solutions?
     * 2. What if we need to find all pairs?
     * 3. What if the array is sorted?
     * 4. What if we need to find three numbers that sum to target?
     * 5. What if we need to find the closest sum to target?
     */
}
