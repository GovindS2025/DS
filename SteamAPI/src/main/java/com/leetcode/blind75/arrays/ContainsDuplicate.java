package com.leetcode.blind75.arrays;

import java.util.*;

/**
 * LeetCode 217: Contains Duplicate
 * 
 * Problem: Return true if any value appears at least twice in the array.
 * 
 * Approach 1: HashSet - Add elements, return true if already exists
 * Approach 2: Sorting - Sort array, check adjacent elements
 * Approach 3: Brute Force - Check all pairs (O(n²))
 * 
 * Time Complexity: O(n) - HashSet approach
 * Space Complexity: O(n) - HashSet can store all elements
 */
public class ContainsDuplicate {
    
    // Approach 1: HashSet (Optimal)
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    // Approach 2: Sorting
    public static boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,1};
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Contains Duplicate: " + containsDuplicate(nums1));
        System.out.println("Expected: true");
    }
}
