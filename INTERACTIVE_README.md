# 🎯 Interactive Sliding Window Algorithm Animations

Welcome to the most comprehensive collection of interactive sliding window algorithm visualizations! Each problem includes step-by-step animations, Mermaid diagrams, and detailed explanations.

## 🚀 Quick Navigation

| Problem | Difficulty | Animation | Mermaid | Code |
|---------|------------|-----------|---------|------|
| [Longest Substring Without Repeating Characters](#1-longest-substring-without-repeating-characters) | Medium | 🎬 | 📊 | 💻 |
| [Longest Substring with K Distinct Characters](#2-longest-substring-with-k-distinct-characters) | Medium | 🎬 | 📊 | 💻 |
| [Minimum Window Substring](#3-minimum-window-substring) | Hard | 🎬 | 📊 | 💻 |
| [Maximum Sum Subarray of Size K](#4-maximum-sum-subarray-of-size-k) | Easy | 🎬 | 📊 | 💻 |
| [Longest Repeating Character Replacement](#5-longest-repeating-character-replacement) | Medium | 🎬 | 📊 | 💻 |

---

## 1. Longest Substring Without Repeating Characters

**Problem**: Find the length of the longest substring without repeating characters.

### 🎬 Interactive Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](sliding_window_simple_animation.html)

### 📊 Algorithm Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Initialize left=0, right=0, window=Set]
    B --> C[Move right pointer]
    C --> D[Add character to window]
    D --> E{Character already in window?}
    E -->|No| F[Update maxLength]
    E -->|Yes| G[Shrink window from left]
    G --> H[Remove left character]
    H --> I[Move left pointer]
    I --> J{More characters?}
    F --> J
    J -->|Yes| C
    J -->|No| K[Return maxLength]
    K --> L[End]
    
    style A fill:#e1f5fe
    style L fill:#c8e6c9
    style E fill:#fff3e0
    style J fill:#fff3e0
```

### 📈 Step-by-Step Visualization
```mermaid
graph LR
    A[String: abcabcbb] --> B[Step 1: a]
    B --> C[Step 2: ab]
    C --> D[Step 3: abc]
    D --> E[Step 4: bc]
    E --> F[Step 5: bca]
    F --> G[Step 6: ca]
    G --> H[Result: 3]
    
    style A fill:#e3f2fd
    style H fill:#c8e6c9
```

### 💻 Java Implementation
```java
public int longestSubstringWithoutRepeating(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        
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

### 📊 Complexity Analysis
| Aspect | Complexity | Explanation |
|--------|------------|-------------|
| Time | O(n) | Each character visited at most twice |
| Space | O(min(m,n)) | m = charset size, n = string length |

---

## 2. Longest Substring with K Distinct Characters

**Problem**: Find the length of the longest substring with at most k distinct characters.

### 🎬 Interactive Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](longest_substring_k_distinct_animation.html)

### 📊 Algorithm Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Initialize left=0, right=0, charCount=Map]
    B --> C[Move right pointer]
    C --> D[Add character to window]
    D --> E[Update character count]
    E --> F{More than k distinct?}
    F -->|No| G[Update maxLength]
    F -->|Yes| H[Shrink window from left]
    H --> I[Remove left character]
    I --> J[Decrease character count]
    J --> K[Move left pointer]
    K --> L{More characters?}
    G --> L
    L -->|Yes| C
    L -->|No| M[Return maxLength]
    M --> N[End]
    
    style A fill:#e1f5fe
    style N fill:#c8e6c9
    style F fill:#fff3e0
    style L fill:#fff3e0
```

### 💻 Java Implementation
```java
public int longestSubstringWithKDistinct(String s, int k) {
    Map<Character, Integer> charCount = new HashMap<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
        
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

---

## 3. Minimum Window Substring

**Problem**: Find the minimum window in s that contains all characters in t.

### 🎬 Interactive Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](minimum_window_substring_animation.html)

### 📊 Algorithm Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Count characters in t]
    B --> C[Initialize left=0, right=0]
    C --> D[Move right pointer]
    D --> E[Add character to window]
    E --> F[Update window count]
    F --> G{All characters found?}
    G -->|No| H{More characters?}
    G -->|Yes| I[Try to shrink window]
    I --> J[Update minimum window]
    J --> K[Remove left character]
    K --> L[Move left pointer]
    L --> M{Window still valid?}
    M -->|Yes| I
    M -->|No| H
    H -->|Yes| D
    H -->|No| N[Return result]
    N --> O[End]
    
    style A fill:#e1f5fe
    style O fill:#c8e6c9
    style G fill:#fff3e0
    style M fill:#fff3e0
```

### 💻 Java Implementation
```java
public String minWindow(String s, String t) {
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
        
        if (targetCount.containsKey(currentChar) && 
            windowCount.get(currentChar).intValue() == targetCount.get(currentChar).intValue()) {
            formed++;
        }
        
        while (left <= right && formed == required) {
            char leftChar = s.charAt(left);
            
            if (right - left + 1 < minLength) {
                minLength = right - left + 1;
                minLeft = left;
            }
            
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

---

## 4. Maximum Sum Subarray of Size K

**Problem**: Find the maximum sum of any contiguous subarray of size k.

### 🎬 Interactive Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](max_sum_subarray_k_animation.html)

### 📊 Algorithm Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Initialize windowSum=0, maxSum=0]
    B --> C[Calculate sum of first k elements]
    C --> D[Set maxSum = windowSum]
    D --> E[Move window by 1 position]
    E --> F[Add new element, remove old element]
    F --> G[Update windowSum]
    G --> H[Update maxSum if needed]
    H --> I{More positions?}
    I -->|Yes| E
    I -->|No| J[Return maxSum]
    J --> K[End]
    
    style A fill:#e1f5fe
    style K fill:#c8e6c9
    style I fill:#fff3e0
```

### 💻 Java Implementation
```java
public int maxSumSubarray(int[] nums, int k) {
    int windowSum = 0;
    int maxSum = 0;
    
    // Calculate sum of first window
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    
    maxSum = windowSum;
    
    // Slide the window
    for (int i = k; i < nums.length; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
```

---

## 5. Longest Repeating Character Replacement

**Problem**: Find the length of the longest substring containing the same letter after performing at most k character replacements.

### 🎬 Interactive Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](longest_repeating_char_replacement_animation.html)

### 📊 Algorithm Flowchart
```mermaid
flowchart TD
    A[Start] --> B[Initialize left=0, right=0, charCount=Map]
    B --> C[Move right pointer]
    C --> D[Add character to window]
    D --> E[Update character count]
    E --> F[Find most frequent character]
    F --> G{Window size - maxFreq > k?}
    G -->|No| H[Update maxLength]
    G -->|Yes| I[Shrink window from left]
    I --> J[Remove left character]
    J --> K[Decrease character count]
    K --> L[Move left pointer]
    L --> M{More characters?}
    H --> M
    M -->|Yes| C
    M -->|No| N[Return maxLength]
    N --> O[End]
    
    style A fill:#e1f5fe
    style O fill:#c8e6c9
    style G fill:#fff3e0
    style M fill:#fff3e0
```

### 💻 Java Implementation
```java
public int characterReplacement(String s, int k) {
    Map<Character, Integer> charCount = new HashMap<>();
    int left = 0;
    int maxLength = 0;
    int maxFreq = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
        maxFreq = Math.max(maxFreq, charCount.get(currentChar));
        
        if (right - left + 1 - maxFreq > k) {
            char leftChar = s.charAt(left);
            charCount.put(leftChar, charCount.get(leftChar) - 1);
            left++;
        }
        
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

---

## 🎨 Animation Features

### ✨ Visual Elements
- **🎯 Pulsing Pointers**: Left (Red) and Right (Blue) with animations
- **🟢 Window Highlighting**: Characters in current window
- **📊 Real-time Stats**: Window size, max length, character counts
- **🎬 Step Controls**: Play, Pause, Next, Previous, Reset
- **📝 Detailed Explanations**: Each step explained

### 🚀 Interactive Controls
- **Auto-play**: Automatic step-by-step progression
- **Manual Control**: Step through at your own pace
- **Reset**: Start over anytime
- **Speed Control**: Adjustable animation speed

---

## 📚 Learning Resources

### 🎯 Algorithm Patterns
1. **Expand and Contract**: Most common sliding window pattern
2. **Fixed Size Window**: For problems with constant window size
3. **Two Pointers**: Similar technique for sorted arrays

### 📊 Complexity Analysis
| Pattern | Time | Space | When to Use |
|---------|------|-------|-------------|
| Expand & Contract | O(n) | O(k) | Variable window size |
| Fixed Size | O(n) | O(1) | Constant window size |
| Two Pointers | O(n) | O(1) | Sorted arrays |

### 🔧 Implementation Tips
- Use `HashSet` for unique elements
- Use `HashMap` for character counting
- Use `Deque` for maintaining order
- Handle edge cases (empty strings, invalid inputs)

---

## 🤝 Contributing

Want to add more problems or improve existing animations?

1. **Fork the repository**
2. **Create a new animation** following the existing pattern
3. **Add Mermaid diagrams** for the algorithm
4. **Update this README** with your new problem
5. **Submit a pull request**

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**🎯 Master the sliding window technique with interactive visualizations!**

*Each animation is designed to help you understand the algorithm step-by-step, making complex problems easy to visualize and learn.*
