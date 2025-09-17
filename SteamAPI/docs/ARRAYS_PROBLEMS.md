# Arrays Problems - LeetCode Blind 75

## 1. Two Sum

### Problem Statement
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

### Approaches

#### Approach 1: Brute Force
```java
public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            }
        }
    }
    return new int[0];
}
```

**Time Complexity:** O(n²) - Nested loops
**Space Complexity:** O(1) - No extra space

#### Approach 2: Hash Map (Optimal)
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[0];
}
```

**Time Complexity:** O(n) - Single pass
**Space Complexity:** O(n) - HashMap storage

### Visual Diagram
```
nums = [2, 7, 11, 15], target = 9

Step 1: i=0, nums[0]=2, complement=7
        HashMap: {}
        complement not found, add (2,0)
        HashMap: {2:0}

Step 2: i=1, nums[1]=7, complement=2
        HashMap: {2:0}
        complement found at index 0
        Return [0,1]
```

## 2. Best Time to Buy and Sell Stock

### Problem Statement
Find the maximum profit from buying and selling stock once.

### Approach: Track Minimum Price
```java
public int maxProfit(int[] prices) {
    int minPrice = prices[0];
    int maxProfit = 0;
    
    for (int i = 1; i < prices.length; i++) {
        minPrice = Math.min(minPrice, prices[i]);
        maxProfit = Math.max(maxProfit, prices[i] - minPrice);
    }
    
    return maxProfit;
}
```

**Time Complexity:** O(n) - Single pass
**Space Complexity:** O(1) - Constant space

### Visual Example
```
prices = [7,1,5,3,6,4]

Day 0: price=7, minPrice=7, maxProfit=0
Day 1: price=1, minPrice=1, maxProfit=0
Day 2: price=5, minPrice=1, maxProfit=4
Day 3: price=3, minPrice=1, maxProfit=4
Day 4: price=6, minPrice=1, maxProfit=5
Day 5: price=4, minPrice=1, maxProfit=5

Result: 5 (buy at 1, sell at 6)
```

## 3. Contains Duplicate

### Problem Statement
Return true if any value appears at least twice in the array.

### Approach 1: HashSet
```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (seen.contains(num)) {
            return true;
        }
        seen.add(num);
    }
    return false;
}
```

**Time Complexity:** O(n) - Single pass
**Space Complexity:** O(n) - HashSet storage

### Approach 2: Sorting
```java
public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i-1]) {
            return true;
        }
    }
    return false;
}
```

**Time Complexity:** O(n log n) - Sorting
**Space Complexity:** O(1) - In-place sorting

## 4. Product of Array Except Self

### Problem Statement
Return array where each element is product of all other elements. Cannot use division.

### Approach: Two Passes
```java
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    
    // First pass: left products
    result[0] = 1;
    for (int i = 1; i < n; i++) {
        result[i] = result[i-1] * nums[i-1];
    }
    
    // Second pass: multiply by right products
    int rightProduct = 1;
    for (int i = n-1; i >= 0; i--) {
        result[i] = result[i] * rightProduct;
        rightProduct *= nums[i];
    }
    
    return result;
}
```

**Time Complexity:** O(n) - Two passes
**Space Complexity:** O(1) - Output array doesn't count

### Visual Example
```
nums = [1,2,3,4]

After first pass (left products):
result = [1, 1, 2, 6]

After second pass (multiply by right products):
result = [24, 12, 8, 6]
```

## 5. Maximum Subarray (Kadane's Algorithm)

### Problem Statement
Find contiguous subarray with maximum sum.

### Approach: Kadane's Algorithm
```java
public int maxSubArray(int[] nums) {
    int maxSoFar = nums[0];
    int maxEndingHere = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
        maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    
    return maxSoFar;
}
```

**Time Complexity:** O(n) - Single pass
**Space Complexity:** O(1) - Constant space

### Visual Example
```
nums = [-2,1,-3,4,-1,2,1,-5,4]

i=0: maxEndingHere=-2, maxSoFar=-2
i=1: maxEndingHere=1, maxSoFar=1
i=2: maxEndingHere=-2, maxSoFar=1
i=3: maxEndingHere=4, maxSoFar=4
i=4: maxEndingHere=3, maxSoFar=4
i=5: maxEndingHere=5, maxSoFar=5
i=6: maxEndingHere=6, maxSoFar=6
i=7: maxEndingHere=1, maxSoFar=6
i=8: maxEndingHere=5, maxSoFar=6

Result: 6 (subarray [4,-1,2,1])
```

## Key Patterns for Array Problems

### 1. Two Pointers
- Use when you need to find pairs or triplets
- One pointer at start, one at end
- Move pointers based on condition

### 2. Sliding Window
- Use for subarray/substring problems
- Expand window until condition met
- Contract window when condition violated

### 3. Hash Map/Set
- Use for lookup operations
- Trade space for time
- O(1) average lookup time

### 4. Prefix Sum
- Use for range sum queries
- Precompute cumulative sums
- Range sum = prefix[right] - prefix[left-1]

### 5. Sorting
- Use when order matters
- Often enables two-pointer technique
- Trade time for simpler logic

## Common Edge Cases

1. **Empty array**: Check length before processing
2. **Single element**: Handle separately
3. **All same elements**: Consider duplicates
4. **Negative numbers**: Watch for overflow
5. **Large numbers**: Use long if needed
6. **Zero values**: Handle division by zero

## Time Complexity Cheat Sheet

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Brute Force | O(n²) | O(1) |
| Hash Map | O(n) | O(n) |
| Sorting | O(n log n) | O(1) |
| Two Pointers | O(n) | O(1) |
| Sliding Window | O(n) | O(k) |

## Space Complexity Cheat Sheet

| Data Structure | Space Complexity | Notes |
|----------------|------------------|-------|
| Array | O(n) | Input size |
| Hash Map | O(n) | Worst case all unique |
| Stack | O(n) | Worst case all elements |
| Variables | O(1) | Constant space |
