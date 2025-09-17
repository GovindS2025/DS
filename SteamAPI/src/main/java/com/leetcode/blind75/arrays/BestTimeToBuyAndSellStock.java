package com.leetcode.blind75.arrays;

/**
 * LeetCode 121: Best Time to Buy and Sell Stock
 * 
 * Problem: Find the maximum profit from buying and selling stock once.
 * 
 * Approach: Keep track of minimum price seen so far and maximum profit.
 * 
 * Time Complexity: O(n) - Single pass through array
 * Space Complexity: O(1) - Only using constant extra space
 * 
 * Why O(n)? We visit each price exactly once.
 * Why O(1)? We only store minPrice and maxProfit variables.
 */
public class BestTimeToBuyAndSellStock {
    
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // Update minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);
            
            // Calculate profit if we sell today
            int currentProfit = prices[i] - minPrice;
            
            // Update maximum profit
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        
        return maxProfit;
    }
    
    /**
     * Visual Example: prices = [7,1,5,3,6,4]
     * 
     * Day 0: price=7, minPrice=7, maxProfit=0
     * Day 1: price=1, minPrice=1, maxProfit=0
     * Day 2: price=5, minPrice=1, maxProfit=4
     * Day 3: price=3, minPrice=1, maxProfit=4
     * Day 4: price=6, minPrice=1, maxProfit=5
     * Day 5: price=4, minPrice=1, maxProfit=5
     * 
     * Result: 5 (buy at 1, sell at 6)
     */
    
    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,4};
        System.out.println("Prices: " + java.util.Arrays.toString(prices1));
        System.out.println("Max Profit: " + maxProfit(prices1));
        System.out.println("Expected: 5");
    }
}
