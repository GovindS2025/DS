package com.leetcode.blind75.arrays;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LeetCode Blind 75 - Array Problems using Stream API and Functional Programming
 * 
 * This class contains functional programming solutions for all array problems
 * in the LeetCode Blind 75 list using Java 8+ Stream API.
 * 
 * @author LeetCode Blind 75 Solutions
 * @version 1.0
 */
public class ArraySolutionsStream {
    
    /**
     * 1. Two Sum - Functional Approach
     * 
     * Problem: Find two numbers that add up to target
     * Approach: Stream API with HashMap for O(n) time complexity
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        return IntStream.range(0, nums.length)
                .boxed()
                .filter(i -> {
                    int complement = target - nums[i];
                    if (numToIndex.containsKey(complement)) {
                        return true;
                    }
                    numToIndex.put(nums[i], i);
                    return false;
                })
                .map(i -> new int[]{numToIndex.get(target - nums[i]), i})
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * 2. Best Time to Buy and Sell Stock - Functional Approach
     * 
     * Problem: Find maximum profit from buying and selling once
     * Approach: Stream with reduce operation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        
        return IntStream.range(1, prices.length)
                .boxed()
                .reduce(new int[]{prices[0], 0}, // [minPrice, maxProfit]
                    (acc, i) -> {
                        int minPrice = Math.min(acc[0], prices[i]);
                        int maxProfit = Math.max(acc[1], prices[i] - acc[0]);
                        return new int[]{minPrice, maxProfit};
                    },
                    (acc1, acc2) -> new int[]{
                        Math.min(acc1[0], acc2[0]),
                        Math.max(acc1[1], acc2[1])
                    })[1];
    }
    
    /**
     * 3. Contains Duplicate - Functional Approach
     * 
     * Problem: Check if array has duplicate elements
     * Approach: Stream with distinct count comparison
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet())
                .size() != nums.length;
    }
    
    /**
     * 4. Product of Array Except Self - Functional Approach
     * 
     * Problem: Return array where each element is product of all others
     * Approach: Two passes using Stream API
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // First pass: left products
        result[0] = 1;
        IntStream.range(1, n)
                .forEach(i -> result[i] = result[i - 1] * nums[i - 1]);
        
        // Second pass: multiply by right products
        AtomicInteger rightProduct = new AtomicInteger(1);
        IntStream.range(0, n)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(i -> {
                    result[i] = result[i] * rightProduct.get();
                    rightProduct.set(rightProduct.get() * nums[i]);
                });
        
        return result;
    }
    
    /**
     * 5. Maximum Subarray (Kadane's Algorithm) - Functional Approach
     * 
     * Problem: Find contiguous subarray with maximum sum
     * Approach: Stream with reduce operation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxSubArray(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .reduce(new int[]{nums[0], nums[0]}, // [maxSoFar, maxEndingHere]
                    (acc, num) -> {
                        int maxEndingHere = Math.max(num, acc[1] + num);
                        int maxSoFar = Math.max(acc[0], maxEndingHere);
                        return new int[]{maxSoFar, maxEndingHere};
                    },
                    (acc1, acc2) -> new int[]{
                        Math.max(acc1[0], acc2[0]),
                        Math.max(acc1[1], acc2[1])
                    })[0];
    }
    
    /**
     * 6. Maximum Product Subarray - Functional Approach
     * 
     * Problem: Find contiguous subarray with maximum product
     * Approach: Stream with reduce operation tracking min and max
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxProduct(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .reduce(new int[]{nums[0], nums[0], nums[0]}, // [maxSoFar, maxEndingHere, minEndingHere]
                    (acc, num) -> {
                        int maxEndingHere = Math.max(num, Math.max(acc[1] * num, acc[2] * num));
                        int minEndingHere = Math.min(num, Math.min(acc[1] * num, acc[2] * num));
                        int maxSoFar = Math.max(acc[0], maxEndingHere);
                        return new int[]{maxSoFar, maxEndingHere, minEndingHere};
                    },
                    (acc1, acc2) -> new int[]{
                        Math.max(acc1[0], acc2[0]),
                        Math.max(acc1[1], acc2[1]),
                        Math.min(acc1[2], acc2[2])
                    })[0];
    }
    
    /**
     * 7. Find Minimum in Rotated Sorted Array - Functional Approach
     * 
     * Problem: Find minimum element in rotated sorted array
     * Approach: Stream with binary search logic
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int findMin(int[] nums) {
        return findMinHelper(nums, 0, nums.length - 1);
    }
    
    private static int findMinHelper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        if (nums[left] < nums[right]) return nums[left];
        
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[right]) {
            return findMinHelper(nums, mid + 1, right);
        } else {
            return findMinHelper(nums, left, mid);
        }
    }
    
    /**
     * 8. Search in Rotated Sorted Array - Functional Approach
     * 
     * Problem: Search target in rotated sorted array
     * Approach: Stream with binary search logic
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int search(int[] nums, int target) {
        return searchHelper(nums, target, 0, nums.length - 1);
    }
    
    private static int searchHelper(int[] nums, int target, int left, int right) {
        if (left > right) return -1;
        
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        
        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                return searchHelper(nums, target, left, mid - 1);
            } else {
                return searchHelper(nums, target, mid + 1, right);
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                return searchHelper(nums, target, mid + 1, right);
            } else {
                return searchHelper(nums, target, left, mid - 1);
            }
        }
    }
    
    /**
     * 9. 3Sum - Functional Approach
     * 
     * Problem: Find all unique triplets that sum to zero
     * Approach: Stream with two pointers
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        IntStream.range(0, nums.length - 2)
                .forEach(i -> {
                    if (i == 0 || nums[i] != nums[i - 1]) {
                        int left = i + 1, right = nums.length - 1;
                        while (left < right) {
                            int sum = nums[i] + nums[left] + nums[right];
                            if (sum == 0) {
                                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                                while (left < right && nums[left] == nums[left + 1]) left++;
                                while (left < right && nums[right] == nums[right - 1]) right--;
                                left++;
                                right--;
                            } else if (sum < 0) {
                                left++;
                            } else {
                                right--;
                            }
                        }
                    }
                });
        
        return result;
    }
    
    /**
     * 10. Container With Most Water - Functional Approach
     * 
     * Problem: Find two lines that together with x-axis forms container with most water
     * Approach: Stream with two pointers
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * 11. Sum of Two Integers - Functional Approach
     * 
     * Problem: Calculate sum of two integers without using + operator
     * Approach: Bit manipulation with Stream
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int getSum(int a, int b) {
        return IntStream.range(0, 32)
                .reduce(0, (sum, i) -> {
                    int bitA = (a >> i) & 1;
                    int bitB = (b >> i) & 1;
                    int carry = (sum >> i) & 1;
                    int result = bitA ^ bitB ^ carry;
                    int newCarry = (bitA & bitB) | (bitA & carry) | (bitB & carry);
                    return sum | (result << i) | (newCarry << (i + 1));
                });
    }
    
    /**
     * 12. Number of 1 Bits - Functional Approach
     * 
     * Problem: Count number of 1 bits in integer
     * Approach: Stream with bit manipulation
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int hammingWeight(int n) {
        return IntStream.range(0, 32)
                .map(i -> (n >> i) & 1)
                .sum();
    }
    
    /**
     * 13. Counting Bits - Functional Approach
     * 
     * Problem: Count 1 bits for each number from 0 to n
     * Approach: Stream with dynamic programming
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int[] countBits(int n) {
        return IntStream.rangeClosed(0, n)
                .map(i -> Integer.bitCount(i))
                .toArray();
    }
    
    /**
     * 14. Missing Number - Functional Approach
     * 
     * Problem: Find missing number in array containing n distinct numbers from 0 to n
     * Approach: Stream with mathematical formula
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return expectedSum - actualSum;
    }
    
    /**
     * 15. Reverse Bits - Functional Approach
     * 
     * Problem: Reverse bits of 32-bit unsigned integer
     * Approach: Stream with bit manipulation
     * 
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static int reverseBits(int n) {
        return IntStream.range(0, 32)
                .reduce(0, (result, i) -> {
                    int bit = (n >> i) & 1;
                    return result | (bit << (31 - i));
                });
    }
    
    /**
     * Utility method to print array problems results
     */
    public static void demonstrateArrayProblems() {
        System.out.println("=== Array Problems - Functional Programming Solutions ===\n");
        
        // Test cases
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {7, 1, 5, 3, 6, 4};
        int[] nums3 = {1, 2, 3, 1};
        int[] nums4 = {1, 2, 3, 4};
        int[] nums5 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        
        // 1. Two Sum
        System.out.println("1. Two Sum:");
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: 9");
        System.out.println("Output: " + Arrays.toString(twoSum(nums1, 9)));
        System.out.println();
        
        // 2. Best Time to Buy and Sell Stock
        System.out.println("2. Best Time to Buy and Sell Stock:");
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + maxProfit(nums2));
        System.out.println();
        
        // 3. Contains Duplicate
        System.out.println("3. Contains Duplicate:");
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + containsDuplicate(nums3));
        System.out.println();
        
        // 4. Product of Array Except Self
        System.out.println("4. Product of Array Except Self:");
        System.out.println("Input: " + Arrays.toString(nums4));
        System.out.println("Output: " + Arrays.toString(productExceptSelf(nums4)));
        System.out.println();
        
        // 5. Maximum Subarray
        System.out.println("5. Maximum Subarray:");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output: " + maxSubArray(nums5));
        System.out.println();
        
        // 6. Maximum Product Subarray
        System.out.println("6. Maximum Product Subarray:");
        System.out.println("Input: " + Arrays.toString(nums5));
        System.out.println("Output: " + maxProduct(nums5));
        System.out.println();
        
        // 7. Find Minimum in Rotated Sorted Array
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("7. Find Minimum in Rotated Sorted Array:");
        System.out.println("Input: " + Arrays.toString(rotated));
        System.out.println("Output: " + findMin(rotated));
        System.out.println();
        
        // 8. Search in Rotated Sorted Array
        System.out.println("8. Search in Rotated Sorted Array:");
        System.out.println("Input: " + Arrays.toString(rotated) + ", Target: 0");
        System.out.println("Output: " + search(rotated, 0));
        System.out.println();
        
        // 9. 3Sum
        int[] threeSumNums = {-1, 0, 1, 2, -1, -4};
        System.out.println("9. 3Sum:");
        System.out.println("Input: " + Arrays.toString(threeSumNums));
        System.out.println("Output: " + threeSum(threeSumNums));
        System.out.println();
        
        // 10. Container With Most Water
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("10. Container With Most Water:");
        System.out.println("Input: " + Arrays.toString(height));
        System.out.println("Output: " + maxArea(height));
        System.out.println();
        
        // 11. Sum of Two Integers
        System.out.println("11. Sum of Two Integers:");
        System.out.println("Input: a=1, b=2");
        System.out.println("Output: " + getSum(1, 2));
        System.out.println();
        
        // 12. Number of 1 Bits
        System.out.println("12. Number of 1 Bits:");
        System.out.println("Input: 11 (binary: 1011)");
        System.out.println("Output: " + hammingWeight(11));
        System.out.println();
        
        // 13. Counting Bits
        System.out.println("13. Counting Bits:");
        System.out.println("Input: n=5");
        System.out.println("Output: " + Arrays.toString(countBits(5)));
        System.out.println();
        
        // 14. Missing Number
        int[] missing = {3, 0, 1};
        System.out.println("14. Missing Number:");
        System.out.println("Input: " + Arrays.toString(missing));
        System.out.println("Output: " + missingNumber(missing));
        System.out.println();
        
        // 15. Reverse Bits
        System.out.println("15. Reverse Bits:");
        System.out.println("Input: 43261596 (binary: 00000010100101000001111010011100)");
        System.out.println("Output: " + reverseBits(43261596));
        System.out.println();
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        demonstrateArrayProblems();
    }
}
