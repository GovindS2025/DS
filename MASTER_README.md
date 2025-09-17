# 🎯 Master Sliding Window Algorithm Collection

**The most comprehensive collection of interactive sliding window algorithm visualizations!**

[![GitHub Pages](https://img.shields.io/badge/🌐_Live_Demo-GitHub_Pages-blue?style=for-the-badge)](https://yourusername.github.io/yourrepository/)
[![Interactive Animations](https://img.shields.io/badge/🎬_Animations-Interactive-green?style=for-the-badge)](#interactive-animations)
[![Mermaid Diagrams](https://img.shields.io/badge/📊_Diagrams-Mermaid-orange?style=for-the-badge)](#mermaid-diagrams)

---

## 🚀 Quick Start

### Option 1: View All Animations
[![View All Animations](https://img.shields.io/badge/🎬_View_All_Animations-Interactive-blue?style=for-the-badge&logo=html5)](index.html)

### Option 2: Individual Problem Animations
| Problem | Animation | Mermaid | Difficulty |
|---------|-----------|---------|------------|
| [Longest Substring Without Repeating Characters](#1-longest-substring-without-repeating-characters) | [🎬 View](sliding_window_simple_animation.html) | [📊 Diagram](#mermaid-1) | Medium |
| [Longest Substring with K Distinct Characters](#2-longest-substring-with-k-distinct-characters) | [🎬 View](longest_substring_k_distinct_animation.html) | [📊 Diagram](#mermaid-2) | Medium |
| [Minimum Window Substring](#3-minimum-window-substring) | [🎬 View](minimum_window_substring_animation.html) | [📊 Diagram](#mermaid-3) | Hard |
| [Maximum Sum Subarray of Size K](#4-maximum-sum-subarray-of-size-k) | [🎬 View](max_sum_subarray_k_animation.html) | [📊 Diagram](#mermaid-4) | Easy |
| [Longest Repeating Character Replacement](#5-longest-repeating-character-replacement) | [🎬 View](longest_repeating_char_replacement_animation.html) | [📊 Diagram](#mermaid-5) | Medium |

---

## 🎬 Interactive Animations

### 1. Longest Substring Without Repeating Characters

**Problem**: Find the length of the longest substring without repeating characters.

#### 🎯 Live Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](sliding_window_simple_animation.html)

#### 📊 Algorithm Flowchart
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

#### 💻 Java Implementation
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

#### 📈 Step-by-Step Example
```
Input: "abcabcbb"

Step 1: [a]bcabcbb     (window: {a}, maxLength: 1)
Step 2: [ab]cabcbb     (window: {a,b}, maxLength: 2)
Step 3: [abc]abcbb     (window: {a,b,c}, maxLength: 3)
Step 4: a[bc]abcbb     (window: {b,c}, maxLength: 3)
Step 5: a[bca]bcbb     (window: {b,c,a}, maxLength: 3)
Step 6: ab[ca]bcbb     (window: {c,a}, maxLength: 3)
...

Result: 3 (substring "abc")
```

---

### 2. Longest Substring with K Distinct Characters

**Problem**: Find the length of the longest substring with at most k distinct characters.

#### 🎯 Live Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](longest_substring_k_distinct_animation.html)

#### 📊 Algorithm Flowchart
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

#### 💻 Java Implementation
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

### 3. Minimum Window Substring

**Problem**: Find the minimum window in s that contains all characters in t.

#### 🎯 Live Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](minimum_window_substring_animation.html)

#### 📊 Algorithm Flowchart
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

---

### 4. Maximum Sum Subarray of Size K

**Problem**: Find the maximum sum of any contiguous subarray of size k.

#### 🎯 Live Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](max_sum_subarray_k_animation.html)

#### 📊 Algorithm Flowchart
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

---

### 5. Longest Repeating Character Replacement

**Problem**: Find the length of the longest substring containing the same letter after performing at most k character replacements.

#### 🎯 Live Animation
[![View Animation](https://img.shields.io/badge/🎬_View_Animation-Interactive-blue?style=for-the-badge)](longest_repeating_char_replacement_animation.html)

#### 📊 Algorithm Flowchart
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

---

## 🎨 Animation Features

### ✨ Visual Elements
- **🎯 Pulsing Pointers**: Left (Red) and Right (Blue) with smooth animations
- **🟢 Window Highlighting**: Characters in current window with glow effects
- **📊 Real-time Stats**: Window size, max length, character counts
- **🎬 Step Controls**: Play, Pause, Next, Previous, Reset
- **📝 Detailed Explanations**: Each step explained with context

### 🚀 Interactive Controls
- **Auto-play**: Automatic step-by-step progression
- **Manual Control**: Step through at your own pace
- **Reset**: Start over anytime
- **Speed Control**: Adjustable animation speed

### 🎯 Color Coding
- **🟢 Green**: Characters in the current window
- **🔴 Red**: Left pointer (L) with pulsing animation
- **🔵 Blue**: Right pointer (R) with pulsing animation
- **⚪ White**: Characters outside the window

---

## 📊 Mermaid Diagrams

### Algorithm Comparison
```mermaid
graph TD
    A[Sliding Window Problems] --> B[Variable Size Window]
    A --> C[Fixed Size Window]
    A --> D[Two Pointers]
    
    B --> E[Longest Substring Without Repeating]
    B --> F[Longest Substring with K Distinct]
    B --> G[Minimum Window Substring]
    B --> H[Longest Repeating Character Replacement]
    
    C --> I[Maximum Sum Subarray of Size K]
    C --> J[Average of Subarrays of Size K]
    
    D --> K[Two Sum in Sorted Array]
    D --> L[Container With Most Water]
    
    style A fill:#e1f5fe
    style B fill:#f3e5f5
    style C fill:#e8f5e8
    style D fill:#fff3e0
```

### Complexity Analysis
```mermaid
graph LR
    A[Sliding Window] --> B[Time: O(n)]
    A --> C[Space: O(k)]
    
    B --> D[Each element visited at most twice]
    C --> E[k = number of unique elements]
    
    style A fill:#e1f5fe
    style B fill:#c8e6c9
    style C fill:#fff3e0
```

---

## 🛠️ How to Use

### For GitHub README
1. **Copy the markdown code** from this file
2. **Paste into your README.md**
3. **Mermaid diagrams render automatically** in GitHub
4. **Link to HTML animations** for interactive experience

### For GitHub Pages
1. **Enable GitHub Pages** in repository settings
2. **Upload HTML files** to your repository
3. **Access animations** at `https://yourusername.github.io/yourrepository/`

### For Local Development
1. **Clone the repository**
2. **Open HTML files** in your browser
3. **Customize animations** as needed

---

## 🎯 Learning Path

### Beginner
1. **Maximum Sum Subarray of Size K** - Fixed size window
2. **Longest Substring Without Repeating Characters** - Variable size window

### Intermediate
3. **Longest Substring with K Distinct Characters** - Character counting
4. **Longest Repeating Character Replacement** - Advanced character counting

### Advanced
5. **Minimum Window Substring** - Complex constraint satisfaction

---

## 🤝 Contributing

Want to add more problems or improve existing animations?

1. **Fork the repository**
2. **Use the animation generator** to create new animations
3. **Add Mermaid diagrams** for new problems
4. **Update this README** with your additions
5. **Submit a pull request**

---

## 📚 Resources

### Algorithm Patterns
- **Expand and Contract**: Most common sliding window pattern
- **Fixed Size Window**: For problems with constant window size
- **Two Pointers**: Similar technique for sorted arrays

### Data Structures
- **HashSet**: For tracking unique elements
- **HashMap**: For counting frequencies
- **Deque**: For maintaining order

### Complexity Guidelines
- **Time**: Usually O(n) - each element visited at most twice
- **Space**: Usually O(k) - where k is the number of unique elements

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

**🎯 Master the sliding window technique with interactive visualizations!**

*Each animation is designed to help you understand the algorithm step-by-step, making complex problems easy to visualize and learn.*

---

## 🔗 Quick Links

- [🎬 All Animations](index.html)
- [📚 Complete Documentation](SLIDING_WINDOW_DOCUMENTATION.md)
- [🛠️ Animation Generator](animation_generator_template.html)
- [📊 Mermaid Diagrams](sliding_window_mermaid_diagram.md)
