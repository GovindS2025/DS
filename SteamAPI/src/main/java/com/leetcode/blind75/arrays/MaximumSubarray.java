package com.leetcode.blind75.arrays;

/**
 * LeetCode 53: Maximum Subarray (Kadane's Algorithm)
 * 
 * Problem: Find contiguous subarray with maximum sum.
 * 
 * Approach: Kadane's Algorithm - keep track of current sum and maximum sum
 * 
 * Time Complexity: O(n) - Single pass through array
 * Space Complexity: O(1) - Only using constant extra space
 */
public class MaximumSubarray {
    
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend existing subarray or start new one
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * Visual Example: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 
     * i=0: maxEndingHere=-2, maxSoFar=-2
     * i=1: maxEndingHere=max(1, -2+1)=1, maxSoFar=max(-2,1)=1
     * i=2: maxEndingHere=max(-3, 1-3)=-2, maxSoFar=max(1,-2)=1
     * i=3: maxEndingHere=max(4, -2+4)=4, maxSoFar=max(1,4)=4
     * i=4: maxEndingHere=max(-1, 4-1)=3, maxSoFar=max(4,3)=4
     * i=5: maxEndingHere=max(2, 3+2)=5, maxSoFar=max(4,5)=5
     * i=6: maxEndingHere=max(1, 5+1)=6, maxSoFar=max(5,6)=6
     * i=7: maxEndingHere=max(-5, 6-5)=1, maxSoFar=max(6,1)=6
     * i=8: maxEndingHere=max(4, 1+4)=5, maxSoFar=max(6,5)=6
     * 
     * Result: 6 (subarray [4,-1,2,1])
     */
    
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Max Subarray Sum: " + maxSubArray(nums));
        System.out.println("Expected: 6");
    }
}
