package com.leetcode.blind75.arrays;

/**
 * LeetCode 238: Product of Array Except Self
 * 
 * Problem: Return array where each element is product of all other elements.
 * Cannot use division and must be O(n) time.
 * 
 * Approach: Two passes - left products then right products
 * 
 * Time Complexity: O(n) - Two passes through array
 * Space Complexity: O(1) - Output array doesn't count as extra space
 */
public class ProductOfArrayExceptSelf {
    
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: calculate left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Second pass: multiply by right products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
    
    /**
     * Visual Example: nums = [1,2,3,4]
     * 
     * After first pass (left products):
     * result = [1, 1, 2, 6]
     * 
     * After second pass (multiply by right products):
     * i=3: result[3] = 6 * 1 = 6, rightProduct = 4
     * i=2: result[2] = 2 * 4 = 8, rightProduct = 12
     * i=1: result[1] = 1 * 12 = 12, rightProduct = 24
     * i=0: result[0] = 1 * 24 = 24, rightProduct = 24
     * 
     * Final result: [24, 12, 8, 6]
     */
    
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] result = productExceptSelf(nums);
        System.out.println("Input: " + java.util.Arrays.toString(nums));
        System.out.println("Output: " + java.util.Arrays.toString(result));
        System.out.println("Expected: [24, 12, 8, 6]");
    }
}
