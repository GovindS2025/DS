# Sliding Window Techniques for String Problems - Complete Documentation

## Table of Contents
1. [What is Sliding Window?](#what-is-sliding-window)
2. [How Does It Work?](#how-does-it-work)
3. [Types of Sliding Window](#types-of-sliding-window)
4. [Algorithm Implementations](#algorithm-implementations)
5. [Complexity Analysis](#complexity-analysis)
6. [Visual Examples](#visual-examples)
7. [Best Practices](#best-practices)
8. [Common Patterns](#common-patterns)
9. [Troubleshooting](#troubleshooting)

## What is Sliding Window?

The **Sliding Window** technique is a powerful algorithmic approach used to solve problems involving arrays or strings where you need to find a subarray or substring that satisfies certain conditions. Instead of checking every possible subarray/substring (which would be O(n²) or worse), the sliding window technique allows us to solve these problems in O(n) time complexity.

### Key Concepts:
- **Window**: A contiguous subarray or substring that we're currently examining
- **Left Pointer**: Marks the start of the current window
- **Right Pointer**: Marks the end of the current window
- **Window Shrinking**: Moving the left pointer to maintain window constraints
- **Window Expanding**: Moving the right pointer to explore new elements

## How Does It Work?

The sliding window technique follows this general pattern:

```
1. Initialize two pointers (left and right) at the beginning
2. Expand the window by moving the right pointer
3. When the window violates constraints, shrink it by moving the left pointer
4. Keep track of the optimal solution during the process
5. Repeat until the right pointer reaches the end
```

### Basic Template:
```java
public int slidingWindow(String s) {
    int left = 0;
    int result = 0;
    
    for (int right = 0; right < s.length(); right++) {
        // Add current element to window
        // ... process current element ...
        
        // Shrink window if constraints are violated
        while (/* window is invalid */) {
            // Remove left element from window
            // ... process removal ...
            left++;
        }
        
        // Update result
        result = Math.max(result, right - left + 1);
    }
    
    return result;
}
```

## Types of Sliding Window

### 1. Fixed Size Window
- Window size remains constant
- Both pointers move together
- Example: Maximum sum of subarray of size k

### 2. Variable Size Window
- Window size changes based on conditions
- One pointer moves while the other adjusts
- Example: Longest substring without repeating characters

### 3. Two Pointers Technique
- Similar to sliding window but with different constraints
- Often used for sorted arrays
- Example: Two sum in sorted array

## Algorithm Implementations

### 1. Longest Substring Without Repeating Characters

**Problem**: Find the length of the longest substring without repeating characters.

**How it works**:
1. Use a HashSet to track characters in current window
2. Expand window by moving right pointer
3. When duplicate found, shrink window from left until duplicate is removed
4. Track maximum window size

```java
public static int longestSubstringWithoutRepeating(String s) {
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
```

**Example Walkthrough**:
```
String: "abcabcbb"
Step 1: window = {a}, left=0, right=0, maxLength=1
Step 2: window = {a,b}, left=0, right=1, maxLength=2
Step 3: window = {a,b,c}, left=0, right=2, maxLength=3
Step 4: 'a' found again, shrink: window = {b,c}, left=1, right=3, maxLength=3
Step 5: window = {b,c,a}, left=1, right=4, maxLength=3
... and so on
```

### 2. Longest Substring with At Most K Distinct Characters

**Problem**: Find the length of the longest substring with at most k distinct characters.

**How it works**:
1. Use HashMap to count character frequencies
2. Expand window by moving right pointer
3. When more than k distinct characters, shrink window from left
4. Track maximum window size

```java
public static int longestSubstringWithKDistinct(String s, int k) {
    Map<Character, Integer> charCount = new HashMap<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
        
        // Shrink window if we have more than k distinct characters
        while (charCount.size() > k) {
            char leftChar = s.charAt(left);
            charCount.put(leftChar, charCount.get(leftChar) - 1);
            if (charCount.get(leftChar) == 0) {
                charCount.remove(leftChar);
            }
            left++;
        }
        
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

### 3. Minimum Window Substring

**Problem**: Find the minimum window in s that contains all characters in t.

**How it works**:
1. Count characters needed from string t
2. Use two maps: one for target counts, one for current window counts
3. Expand window until all required characters are found
4. Contract window from left while maintaining all required characters
5. Track minimum window size

```java
public static String minWindow(String s, String t) {
    // Count characters in t
    Map<Character, Integer> targetCount = new HashMap<>();
    for (char c : t.toCharArray()) {
        targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
    }
    
    int required = targetCount.size();
    int formed = 0;
    int left = 0;
    int minLeft = 0;
    int minLength = Integer.MAX_VALUE;
    
    Map<Character, Integer> windowCount = new HashMap<>();
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        windowCount.put(currentChar, windowCount.getOrDefault(currentChar, 0) + 1);
        
        // Check if current character's count matches target
        if (targetCount.containsKey(currentChar) && 
            windowCount.get(currentChar).intValue() == targetCount.get(currentChar).intValue()) {
            formed++;
        }
        
        // Try to contract window from left
        while (left <= right && formed == required) {
            char leftChar = s.charAt(left);
            
            // Update minimum window if current is smaller
            if (right - left + 1 < minLength) {
                minLength = right - left + 1;
                minLeft = left;
            }
            
            // Remove left character from window
            windowCount.put(leftChar, windowCount.get(leftChar) - 1);
            if (targetCount.containsKey(leftChar) && 
                windowCount.get(leftChar) < targetCount.get(leftChar)) {
                formed--;
            }
            
            left++;
        }
    }
    
    return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
}
```

## Complexity Analysis

### Time Complexity
- **Most sliding window problems**: O(n) where n is the length of the string/array
- **Why O(n)**: Each element is visited at most twice (once by right pointer, once by left pointer)
- **Some exceptions**: O(n²) for problems requiring nested loops for validation

### Space Complexity
- **Character frequency problems**: O(k) where k is the number of unique characters (at most 26 for lowercase English letters)
- **General problems**: O(min(m, n)) where m is the charset size and n is the string length

### Comparison with Other Approaches

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| Brute Force | O(n²) or O(n³) | O(1) | Small inputs, simple problems |
| Sliding Window | O(n) | O(k) | Substring/subarray problems |
| Two Pointers | O(n) | O(1) | Sorted arrays, specific patterns |

## Visual Examples

### Example 1: Longest Substring Without Repeating Characters

```
String: "abcabcbb"

Step 1: [a]bcabcbb
        ↑
        left=0, right=0, window={a}, maxLength=1

Step 2: [ab]cabcbb
        ↑
        left=0, right=1, window={a,b}, maxLength=2

Step 3: [abc]abcbb
        ↑
        left=0, right=2, window={a,b,c}, maxLength=3

Step 4: a[bc]abcbb
         ↑
         left=1, right=3, window={b,c}, maxLength=3
         (removed 'a' because 'a' at right=3)

Step 5: a[bca]bcbb
         ↑
         left=1, right=4, window={b,c,a}, maxLength=3

Step 6: ab[ca]bcbb
          ↑
          left=2, right=4, window={c,a}, maxLength=3
          (removed 'b' because 'b' at right=5)

And so on...
```

### Example 2: Longest Substring with At Most 2 Distinct Characters

```
String: "eceba", k=2

Step 1: [e]ceba
        ↑
        left=0, right=0, distinct={e}, count=1, maxLength=1

Step 2: [ec]eba
        ↑
        left=0, right=1, distinct={e,c}, count=2, maxLength=2

Step 3: [ece]ba
        ↑
        left=0, right=2, distinct={e,c}, count=2, maxLength=3

Step 4: e[ce]ba
         ↑
         left=1, right=3, distinct={c,e}, count=2, maxLength=3
         (removed first 'e' to make room for 'b')

Step 5: ec[eb]a
          ↑
          left=2, right=4, distinct={e,b}, count=2, maxLength=3
          (removed 'c' to make room for 'a')

Final result: 3 (substring "ece")
```

## Best Practices

### 1. Choose the Right Data Structure
- **HashSet**: For tracking unique elements
- **HashMap**: For counting frequencies
- **Array**: For fixed-size character sets (e.g., lowercase letters)
- **Deque**: For maintaining order (e.g., sliding window maximum)

### 2. Handle Edge Cases
```java
// Always check for null and empty inputs
if (s == null || s.isEmpty()) {
    return 0; // or appropriate default
}

// Handle invalid parameters
if (k <= 0) {
    return 0;
}
```

### 3. Optimize Space Usage
```java
// Use array instead of HashMap for lowercase letters
int[] charCount = new int[26]; // Instead of HashMap<Character, Integer>

// Use bit manipulation for boolean flags
int seen = 0; // Instead of boolean array
```

### 4. Avoid Common Pitfalls
- **Integer overflow**: Use `long` for large numbers
- **Character comparison**: Use `charAt()` instead of `charAt() == 'a'` for Unicode
- **Window validation**: Ensure window is valid before updating result

## Common Patterns

### Pattern 1: Expand and Contract
```java
for (int right = 0; right < n; right++) {
    // Expand window
    addToWindow(s[right]);
    
    // Contract window if needed
    while (windowIsInvalid()) {
        removeFromWindow(s[left]);
        left++;
    }
    
    // Update result
    updateResult();
}
```

### Pattern 2: Fixed Size Window
```java
// Initialize window
for (int i = 0; i < k; i++) {
    addToWindow(s[i]);
}

// Slide window
for (int i = k; i < n; i++) {
    removeFromWindow(s[i - k]);
    addToWindow(s[i]);
    updateResult();
}
```

### Pattern 3: Two Pointers
```java
int left = 0, right = n - 1;
while (left < right) {
    if (condition) {
        left++;
    } else {
        right--;
    }
    updateResult();
}
```

## Troubleshooting

### Common Issues and Solutions

#### 1. Time Limit Exceeded
**Problem**: Algorithm is too slow
**Solution**: 
- Ensure O(n) time complexity
- Avoid nested loops
- Use efficient data structures

#### 2. Wrong Answer
**Problem**: Algorithm returns incorrect result
**Solution**:
- Check window validation logic
- Verify pointer movement
- Test edge cases

#### 3. Memory Limit Exceeded
**Problem**: Using too much memory
**Solution**:
- Use arrays instead of HashMaps when possible
- Avoid storing unnecessary data
- Use primitive types instead of objects

#### 4. Off-by-One Errors
**Problem**: Incorrect window size calculation
**Solution**:
- Remember: window size = right - left + 1
- Check boundary conditions
- Test with small examples

### Debugging Tips

1. **Add logging**:
```java
System.out.println("left=" + left + ", right=" + right + 
                   ", window=" + window + ", result=" + result);
```

2. **Test with small examples**:
```java
// Test with "abc" instead of long strings
String test = "abc";
int result = longestSubstringWithoutRepeating(test);
```

3. **Use assertions**:
```java
assert left <= right : "Left pointer should not exceed right pointer";
assert window.size() <= k : "Window should not exceed k distinct characters";
```

## Advanced Techniques

### 1. Sliding Window with Deque
For problems requiring maintaining maximum/minimum in window:
```java
Deque<Integer> deque = new ArrayDeque<>();
// Maintain decreasing order for maximum
while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
    deque.pollLast();
}
```

### 2. Sliding Window with Binary Search
For optimization problems:
```java
// Binary search on answer
int left = 0, right = maxPossible;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (canAchieve(mid)) {
        right = mid;
    } else {
        left = mid + 1;
    }
}
```

### 3. Sliding Window with Rolling Hash
For pattern matching:
```java
// Rolling hash for string matching
long hash = 0;
long power = 1;
for (int i = 0; i < pattern.length(); i++) {
    hash = (hash * base + pattern.charAt(i)) % mod;
    power = (power * base) % mod;
}
```

## Conclusion

The sliding window technique is a fundamental algorithmic pattern that provides efficient solutions to many string and array problems. By understanding the core concepts and practicing with various problems, you can master this technique and apply it to solve complex problems efficiently.

### Key Takeaways:
1. **Identify the problem type**: Look for substring/subarray problems with constraints
2. **Choose the right data structure**: Based on what you need to track
3. **Follow the template**: Expand, validate, contract, update
4. **Handle edge cases**: Null inputs, empty strings, invalid parameters
5. **Optimize carefully**: Balance time and space complexity

### Next Steps:
1. Practice with the provided examples
2. Try variations of the problems
3. Implement additional sliding window problems
4. Explore advanced techniques like rolling hash and binary search integration

Remember: The sliding window technique is not just about the code—it's about understanding the problem structure and applying the right pattern to solve it efficiently.

